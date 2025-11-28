package epita.projet_bensadi_vincent.service;

import epita.projet_bensadi_vincent.dto.CompteDto;

import java.math.BigDecimal;

public interface CompteService {

    /**
     * Crédite un compte avec le montant spécifié
     * @param numeroCompte le numéro du compte à créditer
     * @param montant le montant à créditer (doit être positif)
     * @return le compte mis à jour avec le nouveau solde
     * @throws epita.projet_bensadi_vincent.exception.ResourceNotFoundException si le compte n'existe pas (HTTP 404)
     * @throws IllegalArgumentException si le montant est invalide (HTTP 400)
     */
    CompteDto crediter(String numeroCompte, BigDecimal montant);

    /**
     * Débite un compte avec le montant spécifié
     * Valide que le solde après retrait reste dans les limites autorisées :
     * - Compte épargne : solde doit rester >= 0
     * - Compte courant : solde peut être négatif dans la limite du découvert autorisé
     * @param numeroCompte le numéro du compte à débiter
     * @param montant le montant à débiter (doit être positif)
     * @return le compte mis à jour avec le nouveau solde
     * @throws epita.projet_bensadi_vincent.exception.ResourceNotFoundException si le compte n'existe pas (HTTP 404)
     * @throws IllegalArgumentException si le montant est invalide ou si le découvert est dépassé (HTTP 400)
     */
    CompteDto debiter(String numeroCompte, BigDecimal montant);
}

