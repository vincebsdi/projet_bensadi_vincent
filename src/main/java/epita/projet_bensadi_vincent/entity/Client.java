package epita.projet_bensadi_vincent.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 100)
    private String prenom;

    @Column(nullable = false, length = 150, unique = true)
    private String email;

    @Column(nullable = false, length = 20)
    private String telephone;

    @Column(nullable = false)
    private String adresse;

    @Column(name = "code_postal",nullable = false, length = 10)
    private String codePostal;

    @Column(nullable = false, length = 50)
    private String ville;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_client", nullable = false)
    private TypeClient typeClient;

    @ManyToOne
    @JoinColumn(name = "conseiller_id", nullable = false)
    private Conseiller conseiller;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Compte> comptes = new HashSet<>();

}
