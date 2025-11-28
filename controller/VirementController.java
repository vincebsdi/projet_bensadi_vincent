package epita.projet_bensadi_vincent.controller;
// ...existing imports...

@RestController
@RequestMapping("/api/virements")
public class VirementController {

    private final VirementService virementService;

    /**
     * Constructeur du contrôleur VirementController.
     * @param virementService Service de gestion des virements
     */
    public VirementController(VirementService virementService) {
        this.virementService = virementService;
    }

    /**
     * Effectue un virement entre deux comptes.
     * <p>Transaction atomique garantie. Valide les soldes et met à jour les comptes.</p>
     * @param virementDto les détails du virement
     * @return le résultat du virement avec les nouveaux soldes, HTTP 200 (OK)
     */
    @PostMapping
    public ResponseEntity<VirementResultDto> effectuerVirement(@Valid @RequestBody VirementDto virementDto) {
        VirementResultDto result = virementService.effectuerVirement(
                virementDto.getCompteSourceId(),
                virementDto.getCompteDestId(),
                virementDto.getMontant()
        );
        return ResponseEntity.ok(result);
    }
}

