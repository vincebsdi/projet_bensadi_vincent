package epita.projet_bensadi_vincent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VirementResultDto {

    private String compteSourceId;
    private BigDecimal nouveauSoldeSource;

    private String compteDestId;
    private BigDecimal nouveauSoldeDest;

    private BigDecimal montant;
    private LocalDateTime dateOperation;
    private String message;
}

