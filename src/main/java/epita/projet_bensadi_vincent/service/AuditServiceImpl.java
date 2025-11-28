package epita.projet_bensadi_vincent.service;

import epita.projet_bensadi_vincent.dto.AuditCompteDto;
import epita.projet_bensadi_vincent.dto.RapportAuditDto;
import epita.projet_bensadi_vincent.entity.Compte;
import epita.projet_bensadi_vincent.entity.CompteCourant;
import epita.projet_bensadi_vincent.entity.TypeClient;
import epita.projet_bensadi_vincent.repository.CompteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class AuditServiceImpl implements AuditService {

    private static final BigDecimal SEUIL_PARTICULIER = new BigDecimal("-5000.00");
    private static final BigDecimal SEUIL_ENTREPRISE = new BigDecimal("-50000.00");

    private final CompteRepository compteRepository;

    public AuditServiceImpl(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @Override
    public RapportAuditDto auditerComptes() {
        RapportAuditDto rapport = new RapportAuditDto();

        // Récupérer tous les comptes actifs
        List<Compte> comptes = compteRepository.findAll().stream()
                .filter(Compte::getActif)
                .toList();

        // Analyser chaque compte
        for (Compte compte : comptes) {
            AuditCompteDto auditDto = creerAuditDto(compte);

            // Classifier le compte
            if (compte.getSolde().compareTo(BigDecimal.ZERO) >= 0) {
                // Compte créditeur
                rapport.getCrediteurs().add(auditDto);
                rapport.getTotaux().setNombreCrediteurs(rapport.getTotaux().getNombreCrediteurs() + 1);
                rapport.getTotaux().setTotalCredit(
                    rapport.getTotaux().getTotalCredit().add(compte.getSolde())
                );
            } else {
                // Compte débiteur
                rapport.getDebiteurs().add(auditDto);
                rapport.getTotaux().setNombreDebiteurs(rapport.getTotaux().getNombreDebiteurs() + 1);
                rapport.getTotaux().setTotalDebit(
                    rapport.getTotaux().getTotalDebit().add(compte.getSolde())
                );

                // Vérifier le dépassement
                BigDecimal depassement = calculerDepassement(compte);
                if (depassement.compareTo(BigDecimal.ZERO) < 0) {
                    auditDto.setDepassement(depassement.abs());
                    rapport.getTotaux().setNombreDepassements(rapport.getTotaux().getNombreDepassements() + 1);
                    rapport.getTotaux().setTotalDepassements(
                        rapport.getTotaux().getTotalDepassements().add(depassement.abs())
                    );
                }
            }
        }

        return rapport;
    }

    /**
     * Crée un DTO d'audit à partir d'un compte
     */
    private AuditCompteDto creerAuditDto(Compte compte) {
        AuditCompteDto dto = new AuditCompteDto();
        dto.setNumeroCompte(compte.getNumeroCompte());
        dto.setSolde(compte.getSolde());
        dto.setClientNom(compte.getClient().getNom());
        dto.setClientPrenom(compte.getClient().getPrenom());
        dto.setTypeClient(compte.getClient().getTypeClient().name());

        // Type de compte
        if (compte instanceof CompteCourant) {
            dto.setTypeCompte("COMPTE_COURANT");
            dto.setDecouvertAutorise(((CompteCourant) compte).getDecouvertAutorise());
        } else {
            dto.setTypeCompte("COMPTE_EPARGNE");
            dto.setDecouvertAutorise(BigDecimal.ZERO);
        }

        return dto;
    }

    /**
     * Calcule le dépassement selon le type de client
     * Retourne une valeur négative si dépassement, 0 ou positive sinon
     */
    private BigDecimal calculerDepassement(Compte compte) {
        TypeClient typeClient = compte.getClient().getTypeClient();
        BigDecimal seuil;

        if (typeClient == TypeClient.PARTICULIER) {
            seuil = SEUIL_PARTICULIER;
        } else {
            seuil = SEUIL_ENTREPRISE;
        }

        // Si solde < seuil, il y a dépassement
        // depassement = solde - seuil (sera négatif si dépassement)
        return compte.getSolde().subtract(seuil);
    }
}

