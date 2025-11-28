package epita.projet_bensadi_vincent.controller;
// ...existing imports...

@RestController
@RequestMapping("/api/comptes")
public class CompteController {

    private final CompteService compteService;

    /**
     * Constructeur du contrôleur CompteController.
     * @param compteService Service de gestion des comptes
     */
    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    /**
     * Crédite un compte avec le montant spécifié.
     * <p>Ajoute le montant au solde du compte. Le compte doit être actif.</p>
     * @param id le numéro du compte à créditer
     * @param montantDto le montant à créditer
     * @return le compte avec le nouveau solde, HTTP 200 (OK)
     */
    @PostMapping("/{id}/crediter")
    public ResponseEntity<CompteDto> crediter(@PathVariable String id,
                                              @Valid @RequestBody MontantDto montantDto) {
        CompteDto compte = compteService.crediter(id, montantDto.getMontant());
        return ResponseEntity.ok(compte);
    }

    /**
     * Débite un compte avec le montant spécifié.
     * <p>Valide que le découvert autorisé n'est pas dépassé.</p>
     * @param id le numéro du compte à débiter
     * @param montantDto le montant à débiter
     * @return le compte avec le nouveau solde, HTTP 200 (OK)
     */
    @PostMapping("/{id}/debiter")
    public ResponseEntity<CompteDto> debiter(@PathVariable String id,
                                             @Valid @RequestBody MontantDto montantDto) {
        CompteDto compte = compteService.debiter(id, montantDto.getMontant());
        return ResponseEntity.ok(compte);
    }
}

