package epita.projet_bensadi_vincent.service;

import epita.projet_bensadi_vincent.dto.VirementResultDto;

import java.math.BigDecimal;

public interface VirementService {

    /**
     * Effectue un virement entre deux comptes
     * L'opération est atomique et transactionnelle
     * @param compteSourceId le numéro du compte source
     * @param compteDestId le numéro du compte destination
     * @param montant le montant à transférer
     * @return le résultat du virement avec les nouveaux soldes
     * @throws epita.projet_bensadi_vincent.exception.ResourceNotFoundException si un compte n'existe pas (HTTP 404)
     * @throws IllegalArgumentException si les validations échouent (HTTP 400)
     */
    VirementResultDto effectuerVirement(String compteSourceId, String compteDestId, BigDecimal montant);
}

