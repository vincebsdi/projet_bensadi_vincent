package epita.projet_bensadi_vincent.controller;

import epita.projet_bensadi_vincent.dto.CompteDto;
import epita.projet_bensadi_vincent.dto.MontantDto;
import epita.projet_bensadi_vincent.service.CompteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {

    private final CompteService compteService;

    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    /**
     * Crédite un compte avec le montant spécifié
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
     * Débite un compte avec le montant spécifié
     * Valide que le découvert autorisé n'est pas dépassé
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

