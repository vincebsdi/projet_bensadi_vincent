package epita.projet_bensadi_vincent.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_compte", discriminatorType = DiscriminatorType.STRING)
public abstract class Compte {

    @Id
    @Column(name = "numero_compte", unique = true, nullable = false, length = 20)
    private String numeroCompte;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal solde = BigDecimal.ZERO;

    @Column(name = "date_ouverture", nullable = false)
    private LocalDate dateOuverture;

    @Column(nullable = false)
    private Boolean actif = true;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public BigDecimal getSolde()
    {
        return solde;
    }

    public void crediter(BigDecimal montant)
    {
        this.solde = this.solde.add(montant);
    }

    public void debiter(BigDecimal montant)
    {
        if (!peutRetirer(montant)) {
            throw new IllegalArgumentException("Opération refusée : découvert autorisé dépassé");
        }
        this.solde = this.solde.subtract(montant);
    }

    public abstract boolean peutRetirer(BigDecimal montant);
}
