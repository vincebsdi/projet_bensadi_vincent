package epita.projet_bensadi_vincent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RapportAuditDto {
    private List<AuditCompteDto> crediteurs = new ArrayList<>();
    private List<AuditCompteDto> debiteurs = new ArrayList<>();
    private TotauxAuditDto totaux = new TotauxAuditDto();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TotauxAuditDto {
        private int nombreCrediteurs;
        private BigDecimal totalCredit = BigDecimal.ZERO;
        private int nombreDebiteurs;
        private BigDecimal totalDebit = BigDecimal.ZERO;
        private int nombreDepassements;
        private BigDecimal totalDepassements = BigDecimal.ZERO;
    }
}

