package epita.projet_bensadi_vincent.config;

import epita.projet_bensadi_vincent.entity.Agence;
import epita.projet_bensadi_vincent.entity.Conseiller;
import epita.projet_bensadi_vincent.entity.Gerant;
import epita.projet_bensadi_vincent.repository.AgenceRepository;
import epita.projet_bensadi_vincent.repository.ConseillerRepository;
import epita.projet_bensadi_vincent.repository.GerantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(AgenceRepository agenceRepository,
                                   GerantRepository gerantRepository,
                                   ConseillerRepository conseillerRepository) {
        return args -> {
            // Vérifier si des données existent déjà
            if (agenceRepository.count() > 0) {
                return; // Ne pas réinsérer les données
            }

            // Créer une agence
            Agence agence = new Agence();
            agence.setId("AG001");
            agence = agenceRepository.save(agence);

            // Créer un gérant
            Gerant gerant = new Gerant();
            gerant.setNom("Martin");
            gerant.setPrenom("Sophie");
            gerant.setEmail("sophie.martin@simplecash.fr");
            gerant.setTelephone("0601020304");
            gerant.setAgence(agence);
            gerantRepository.save(gerant);

            // Créer des conseillers
            Conseiller conseiller1 = new Conseiller();
            conseiller1.setNom("Dubois");
            conseiller1.setPrenom("Pierre");
            conseiller1.setEmail("pierre.dubois@simplecash.fr");
            conseiller1.setTelephone("0612345678");
            conseiller1.setMaxClients(10);
            conseiller1.setAgence(agence);
            conseillerRepository.save(conseiller1);

            Conseiller conseiller2 = new Conseiller();
            conseiller2.setNom("Bernard");
            conseiller2.setPrenom("Marie");
            conseiller2.setEmail("marie.bernard@simplecash.fr");
            conseiller2.setTelephone("0623456789");
            conseiller2.setMaxClients(10);
            conseiller2.setAgence(agence);
            conseillerRepository.save(conseiller2);

            System.out.println("✅ Données de test insérées : 1 agence, 1 gérant, 2 conseillers");
        };
    }
}

