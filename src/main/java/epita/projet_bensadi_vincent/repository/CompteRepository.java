package epita.projet_bensadi_vincent.repository;

import epita.projet_bensadi_vincent.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, String> {
}

