package epita.projet_bensadi_vincent.aspect;

import epita.projet_bensadi_vincent.dto.VirementResultDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class VirementAspect {

    private static final Logger logger = LoggerFactory.getLogger("VIREMENT_LOGGER");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Aspect qui intercepte tous les appels à la méthode effectuerVirement du VirementService
     * et trace l'opération dans le fichier virements.log
     */
    @Around("execution(* epita.projet_bensadi_vincent.service.VirementService.effectuerVirement(..))")
    public Object logVirement(ProceedingJoinPoint joinPoint) throws Throwable {
        // Récupérer les paramètres de la méthode
        Object[] args = joinPoint.getArgs();
        String compteSourceId = (String) args[0];
        String compteDestId = (String) args[1];
        BigDecimal montant = (BigDecimal) args[2];

        String timestamp = LocalDateTime.now().format(formatter);
        String user = "System"; // Peut être remplacé par le user authentifié si Spring Security est implémenté

        try {
            // Exécuter la méthode
            Object result = joinPoint.proceed();

            // Si succès, logger avec statut SUCCESS
            VirementResultDto virementResult = (VirementResultDto) result;
            String logMessage = String.format(
                "[%s] %s | %s -> %s | %.2f EUR | SUCCESS | Nouveau solde source: %.2f EUR | Nouveau solde dest: %.2f EUR",
                timestamp,
                user,
                compteSourceId,
                compteDestId,
                montant,
                virementResult.getNouveauSoldeSource(),
                virementResult.getNouveauSoldeDest()
            );
            logger.info(logMessage);

            return result;

        } catch (Exception e) {
            // Si erreur, logger avec statut FAILED et la raison
            String logMessage = String.format(
                "[%s] %s | %s -> %s | %.2f EUR | FAILED | Raison: %s",
                timestamp,
                user,
                compteSourceId,
                compteDestId,
                montant,
                e.getMessage()
            );
            logger.error(logMessage);

            // Relancer l'exception
            throw e;
        }
    }
}

