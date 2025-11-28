package epita.projet_bensadi_vincent.controller;

import epita.projet_bensadi_vincent.dto.VirementDto;
import epita.projet_bensadi_vincent.dto.VirementResultDto;
import epita.projet_bensadi_vincent.service.VirementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/virements")
public class VirementController {

    private final VirementService virementService;

    public VirementController(VirementService virementService) {
        this.virementService = virementService;
    }

    /**
     * Effectue un virement entre deux comptes
     * Transaction atomique garantie
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

