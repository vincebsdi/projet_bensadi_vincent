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
public class Agence {
    @Id
    @Column(length = 5, nullable = false)
    private String id;

    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL)
    private Set<Conseiller> conseillers = new HashSet<>();

    @OneToOne(mappedBy = "agence", cascade = CascadeType.ALL)
    private Gerant gerant;
}
