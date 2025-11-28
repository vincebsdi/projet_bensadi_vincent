package epita.projet_bensadi_vincent.controller;

import epita.projet_bensadi_vincent.dto.RapportAuditDto;
import epita.projet_bensadi_vincent.service.AuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/audits")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    /**
     * Effectue un audit complet de tous les comptes
     * Détecte les dépassements selon le type de client :
     * - Particulier : dépassement si solde < -5000€
     * - Entreprise : dépassement si solde < -50000€
     *
     * @return rapport d'audit avec comptes créditeurs, débiteurs et totaux (HTTP 200)
     */
    @GetMapping("/comptes")
    public ResponseEntity<RapportAuditDto> auditerComptes() {
        RapportAuditDto rapport = auditService.auditerComptes();
        return ResponseEntity.ok(rapport);
    }
}

