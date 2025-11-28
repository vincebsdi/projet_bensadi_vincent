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
@DiscriminatorValue("COMPTE_COURANT")
public class CompteCourant extends Compte {

    @Column(name = "decouvert_autorise", nullable = true, precision = 10, scale = 2)
    private BigDecimal decouvertAutorise = new BigDecimal("1000.00");

    @Override
    public boolean peutRetirer(BigDecimal montant) {
        BigDecimal soldeApresRetrait = getSolde().subtract(montant);
        BigDecimal limiteDecouvert = decouvertAutorise.negate();
        return soldeApresRetrait.compareTo(limiteDecouvert) >= 0;
    }

}
