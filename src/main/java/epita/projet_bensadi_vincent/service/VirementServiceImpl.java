package epita.projet_bensadi_vincent.service;

import epita.projet_bensadi_vincent.dto.VirementResultDto;
import epita.projet_bensadi_vincent.entity.Compte;
import epita.projet_bensadi_vincent.exception.ResourceNotFoundException;
import epita.projet_bensadi_vincent.repository.CompteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Transactional
public class VirementServiceImpl implements VirementService {

    private final CompteRepository compteRepository;

    public VirementServiceImpl(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @Override
    public VirementResultDto effectuerVirement(String compteSourceId, String compteDestId, BigDecimal montant) {
        // Validation : les comptes doivent être différents
        if (compteSourceId.equals(compteDestId)) {
            throw new IllegalArgumentException("Le compte source et le compte destination doivent être différents");
        }

        // Validation : le montant doit être positif
        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }

        // Récupérer les comptes
        Compte compteSource = compteRepository.findById(compteSourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Compte source", "numéro", compteSourceId));

        Compte compteDest = compteRepository.findById(compteDestId)
                .orElseThrow(() -> new ResourceNotFoundException("Compte destination", "numéro", compteDestId));

        // Vérifier que les comptes sont actifs
        if (!compteSource.getActif()) {
            throw new IllegalArgumentException("Le compte source est désactivé");
        }

        if (!compteDest.getActif()) {
            throw new IllegalArgumentException("Le compte destination est désactivé");
        }

        // Débiter le compte source (validation du découvert automatique)
        compteSource.debiter(montant);

        // Créditer le compte destination
        compteDest.crediter(montant);

        // Sauvegarder les modifications (transaction atomique)
        compteRepository.save(compteSource);
        compteRepository.save(compteDest);

        // Construire le résultat
        VirementResultDto result = new VirementResultDto();
        result.setCompteSourceId(compteSourceId);
        result.setNouveauSoldeSource(compteSource.getSolde());
        result.setCompteDestId(compteDestId);
        result.setNouveauSoldeDest(compteDest.getSolde());
        result.setMontant(montant);
        result.setDateOperation(LocalDateTime.now());
        result.setMessage("Virement effectué avec succès");

        return result;
    }
}

