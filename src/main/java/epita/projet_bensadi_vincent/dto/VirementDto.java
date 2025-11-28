package epita.projet_bensadi_vincent.dto;

import java.math.BigDecimal;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VirementDto {
    @NotBlank(message = "Le compte source est obligatoire")
    private String compteSourceId;

    @NotBlank(message = "Le compte destination est obligatoire")
    private String compteDestId;

    @NotNull(message = "Le montant est obligatoire")
    @DecimalMin(value = "0.01", message = "Le montant doit être supérieur à 0")
    private BigDecimal montant;
}
