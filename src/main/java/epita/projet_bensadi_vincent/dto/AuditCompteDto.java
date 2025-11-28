package epita.projet_bensadi_vincent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditCompteDto {
    private String numeroCompte;
    private String typeCompte;
    private BigDecimal solde;
    private String clientNom;
    private String clientPrenom;
    private String typeClient;
    private BigDecimal decouvertAutorise;
    private BigDecimal depassement; // Montant du dépassement si applicable
}

