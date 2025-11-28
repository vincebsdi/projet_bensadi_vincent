package epita.projet_bensadi_vincent.controller;
// ...existing imports...

@RestController
@RequestMapping("/api/audits")
public class AuditController {

    private final AuditService auditService;

    /**
     * Constructeur du contrôleur AuditController.
     * @param auditService Service d'audit des comptes
     */
    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    /**
     * Effectue un audit complet de tous les comptes.
     * <p>Détecte les dépassements selon le type de client :
     * - Particulier : dépassement si solde < -5000€
     * - Entreprise : dépassement si solde < -50000€</p>
     * @return rapport d'audit avec comptes créditeurs, débiteurs et totaux (HTTP 200)
     */
    @GetMapping("/comptes")
    public ResponseEntity<RapportAuditDto> auditerComptes() {
        RapportAuditDto rapport = auditService.auditerComptes();
        return ResponseEntity.ok(rapport);
    }
}

