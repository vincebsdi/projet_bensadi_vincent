package epita.projet_bensadi_vincent.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Table(name ="carte_bancaire")
@NoArgsConstructor
@Getter
@Setter

public class CarteBancaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_carte", nullable = false, length = 16, unique = true)
    private String numeroCarte;

    @Enumerated
    @Column(name = "type_carte", nullable = false)
    private TypeCarte typeCarte;

    @Column(name = "date_expiration", nullable = false)
    private LocalDate dateExpiration;

    @Column(nullable = false)
    private Boolean actif = true;

    @ManyToOne
    @JoinColumn(name = "compte_id", nullable = false)
    private Compte compte;

}
