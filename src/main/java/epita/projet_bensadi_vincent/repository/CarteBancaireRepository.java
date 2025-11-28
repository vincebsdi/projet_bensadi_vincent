package epita.projet_bensadi_vincent.repository;

import epita.projet_bensadi_vincent.entity.CarteBancaire;
import epita.projet_bensadi_vincent.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarteBancaireRepository extends JpaRepository<CarteBancaire, Long> {

    /**
     * Trouve toutes les cartes bancaires associées à un compte
     */
    List<CarteBancaire> findByCompte(Compte compte);

    /**
     * Désactive toutes les cartes bancaires d'un compte
     */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE CarteBancaire c SET c.actif = false WHERE c.compte = :compte")
    void deactivateByCompte(@Param("compte") Compte compte);
}

