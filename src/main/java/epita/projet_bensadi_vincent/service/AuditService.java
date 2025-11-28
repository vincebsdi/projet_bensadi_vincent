package epita.projet_bensadi_vincent.service;

import epita.projet_bensadi_vincent.dto.RapportAuditDto;

public interface AuditService {

    /**
     * Effectue un audit complet de tous les comptes
     * Détecte les dépassements selon le type de client :
     * - Particulier : dépassement si solde < -5000€
     * - Entreprise : dépassement si solde < -50000€
     *
     * @return un rapport d'audit avec les comptes créditeurs, débiteurs et les totaux
     */
    RapportAuditDto auditerComptes();
}

