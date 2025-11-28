package epita.projet_bensadi_vincent.repository;

import epita.projet_bensadi_vincent.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
