package epita.projet_bensadi_vincent.service;

import epita.projet_bensadi_vincent.dto.CompteDto;
import epita.projet_bensadi_vincent.entity.Compte;
import epita.projet_bensadi_vincent.exception.ResourceNotFoundException;
import epita.projet_bensadi_vincent.mapper.CompteMapper;
import epita.projet_bensadi_vincent.repository.CompteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final CompteMapper compteMapper;

    public CompteServiceImpl(CompteRepository compteRepository, CompteMapper compteMapper) {
        this.compteRepository = compteRepository;
        this.compteMapper = compteMapper;
    }

    @Override
    public CompteDto crediter(String numeroCompte, BigDecimal montant) {
        // Vérifier que le compte existe
        Compte compte = compteRepository.findById(numeroCompte)
                .orElseThrow(() -> new ResourceNotFoundException("Compte", "numéro", numeroCompte));

        // Vérifier que le montant est positif
        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }

        // Vérifier que le compte est actif
        if (!compte.getActif()) {
            throw new IllegalArgumentException("Le compte est désactivé et ne peut pas être crédité");
        }

        // Créditer le compte
        compte.crediter(montant);

        // Sauvegarder les modifications
        Compte compteUpdated = compteRepository.save(compte);

        // Retourner le DTO
        return compteMapper.toDto(compteUpdated);
    }

    @Override
    public CompteDto debiter(String numeroCompte, BigDecimal montant) {
        // Vérifier que le compte existe
        Compte compte = compteRepository.findById(numeroCompte)
                .orElseThrow(() -> new ResourceNotFoundException("Compte", "numéro", numeroCompte));

        // Vérifier que le montant est positif
        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }

        // Vérifier que le compte est actif
        if (!compte.getActif()) {
            throw new IllegalArgumentException("Le compte est désactivé et ne peut pas être débité");
        }

        // Débiter le compte (la validation du découvert est faite dans la méthode debiter de l'entité)
        compte.debiter(montant);

        // Sauvegarder les modifications
        Compte compteUpdated = compteRepository.save(compte);

        // Retourner le DTO
        return compteMapper.toDto(compteUpdated);
    }
}

