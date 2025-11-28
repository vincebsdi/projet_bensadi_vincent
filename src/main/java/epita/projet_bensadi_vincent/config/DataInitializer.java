package epita.projet_bensadi_vincent.config;

import epita.projet_bensadi_vincent.entity.*;
import epita.projet_bensadi_vincent.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(AgenceRepository agenceRepository,
                                   GerantRepository gerantRepository,
                                   ConseillerRepository conseillerRepository,
                                   ClientRepository clientRepository,
                                   CompteRepository compteRepository) {
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

            // Créer un client
            Client client1 = new Client();
            client1.setNom("Dupont");
            client1.setPrenom("Jean");
            client1.setEmail("jean.dupont@example.com");
            client1.setTelephone("0601020304");
            client1.setAdresse("10 rue de la Paix");
            client1.setCodePostal("75001");
            client1.setVille("Paris");
            client1.setTypeClient(TypeClient.PARTICULIER);
            client1.setConseiller(conseiller1);
            client1 = clientRepository.save(client1);

            // Créer un compte courant
            CompteCourant compteCourant = new CompteCourant();
            compteCourant.setNumeroCompte("CC001");
            compteCourant.setSolde(new java.math.BigDecimal("1000.00"));
            compteCourant.setDateOuverture(java.time.LocalDate.now());
            compteCourant.setActif(true);
            compteCourant.setClient(client1);
            compteCourant.setDecouvertAutorise(new java.math.BigDecimal("1000.00"));
            compteRepository.save(compteCourant);

            // Créer un compte épargne
            CompteEpargne compteEpargne = new CompteEpargne();
            compteEpargne.setNumeroCompte("CE001");
            compteEpargne.setSolde(new java.math.BigDecimal("5000.00"));
            compteEpargne.setDateOuverture(java.time.LocalDate.now());
            compteEpargne.setActif(true);
            compteEpargne.setClient(client1);
            compteEpargne.setTauxRemuneration(new java.math.BigDecimal("3.00"));
            compteRepository.save(compteEpargne);

            // Créer un client entreprise
            Client client2 = new Client();
            client2.setNom("Entreprise SARL");
            client2.setPrenom("Tech");
            client2.setEmail("contact@tech-sarl.com");
            client2.setTelephone("0612345679");
            client2.setAdresse("50 avenue des Entrepreneurs");
            client2.setCodePostal("75002");
            client2.setVille("Paris");
            client2.setTypeClient(TypeClient.ENTREPRISE);
            client2.setConseiller(conseiller2);
            client2 = clientRepository.save(client2);

            // Compte entreprise en dépassement léger (-30000€ < -50000€, donc pas de dépassement)
            CompteCourant compteEntreprise1 = new CompteCourant();
            compteEntreprise1.setNumeroCompte("CC002");
            compteEntreprise1.setSolde(new java.math.BigDecimal("-30000.00"));
            compteEntreprise1.setDateOuverture(java.time.LocalDate.now());
            compteEntreprise1.setActif(true);
            compteEntreprise1.setClient(client2);
            compteEntreprise1.setDecouvertAutorise(new java.math.BigDecimal("50000.00"));
            compteRepository.save(compteEntreprise1);

            // Compte entreprise en dépassement critique (-60000€ < -50000€, DÉPASSEMENT!)
            CompteCourant compteEntreprise2 = new CompteCourant();
            compteEntreprise2.setNumeroCompte("CC003");
            compteEntreprise2.setSolde(new java.math.BigDecimal("-60000.00"));
            compteEntreprise2.setDateOuverture(java.time.LocalDate.now());
            compteEntreprise2.setActif(true);
            compteEntreprise2.setClient(client2);
            compteEntreprise2.setDecouvertAutorise(new java.math.BigDecimal("70000.00"));
            compteRepository.save(compteEntreprise2);

            // Créer un client particulier avec compte en dépassement
            Client client3 = new Client();
            client3.setNom("Martin");
            client3.setPrenom("Alice");
            client3.setEmail("alice.martin@example.com");
            client3.setTelephone("0623456780");
            client3.setAdresse("15 rue des Lilas");
            client3.setCodePostal("75003");
            client3.setVille("Paris");
            client3.setTypeClient(TypeClient.PARTICULIER);
            client3.setConseiller(conseiller1);
            client3 = clientRepository.save(client3);

            // Compte particulier en dépassement (-6000€ < -5000€, DÉPASSEMENT!)
            CompteCourant compteParticulier = new CompteCourant();
            compteParticulier.setNumeroCompte("CC004");
            compteParticulier.setSolde(new java.math.BigDecimal("-6000.00"));
            compteParticulier.setDateOuverture(java.time.LocalDate.now());
            compteParticulier.setActif(true);
            compteParticulier.setClient(client3);
            compteParticulier.setDecouvertAutorise(new java.math.BigDecimal("7000.00"));
            compteRepository.save(compteParticulier);

            System.out.println("✅ Données de test insérées : 1 agence, 1 gérant, 2 conseillers, 3 clients, 6 comptes (dont 2 en dépassement)");
        };
    }
}
