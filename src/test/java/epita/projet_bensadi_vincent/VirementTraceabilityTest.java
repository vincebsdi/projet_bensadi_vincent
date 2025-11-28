package epita.projet_bensadi_vincent;

import epita.projet_bensadi_vincent.dto.VirementDto;
import epita.projet_bensadi_vincent.dto.VirementResultDto;
import epita.projet_bensadi_vincent.service.VirementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test de démonstration du système de traçabilité AOP des virements
 * Les logs sont automatiquement écrits dans logs/virements.log
 */
@SpringBootTest
class VirementTraceabilityTest {

    @Autowired
    private VirementService virementService;

    @Test
    void testVirementSuccessIsLogged() {
        // Ce test vérifie qu'un virement réussi est bien tracé
        // Vérifier le fichier logs/virements.log après exécution

        VirementResultDto result = virementService.effectuerVirement(
                "CC001",
                "CE001",
                new BigDecimal("100.00")
        );

        assertNotNull(result);
        assertEquals("Virement effectué avec succès", result.getMessage());

        // Le log suivant devrait apparaître dans logs/virements.log :
        // [YYYY-MM-DD HH:MM:SS] System | CC001 -> CE001 | 100.00 EUR | SUCCESS | ...
    }

    @Test
    void testVirementFailureIsLogged() {
        // Ce test vérifie qu'un virement échoué est bien tracé
        // Vérifier le fichier logs/virements.log après exécution

        assertThrows(IllegalArgumentException.class, () -> {
            virementService.effectuerVirement(
                    "CC001",
                    "CE001",
                    new BigDecimal("10000.00") // Montant trop élevé
            );
        });

        // Le log suivant devrait apparaître dans logs/virements.log :
        // [YYYY-MM-DD HH:MM:SS] System | CC001 -> CE001 | 10000.00 EUR | FAILED | Raison: ...
    }
}

