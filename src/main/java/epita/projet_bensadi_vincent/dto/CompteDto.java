package epita.projet_bensadi_vincent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompteDto {
    private String numeroCompte;
    private BigDecimal solde;
    private LocalDate dateOuverture;
    private Boolean actif;
    private String typeCompte;
    private Long clientId;

    // Champs spécifiques au compte courant
    private BigDecimal decouvertAutorise;

    // Champs spécifiques au compte épargne
    private BigDecimal tauxRemuneration;
}

