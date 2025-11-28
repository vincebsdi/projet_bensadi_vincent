package epita.projet_bensadi_vincent.entity;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("COMPTE_EPARGNE")
public class CompteEpargne extends Compte{

    @Column(name = "taux_remuneration", nullable = false, precision = 5, scale = 2)
    private BigDecimal tauxRemuneration = new BigDecimal("3.00");

    @Override
    public boolean peutRetirer(BigDecimal montant) {
        return getSolde().compareTo(montant) >= 0;
    }
}
