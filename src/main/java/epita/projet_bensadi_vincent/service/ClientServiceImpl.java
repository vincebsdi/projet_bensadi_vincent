package epita.projet_bensadi_vincent.service;

import epita.projet_bensadi_vincent.dto.ClientCreateDto;
import epita.projet_bensadi_vincent.dto.ClientDto;
import epita.projet_bensadi_vincent.dto.ClientUpdateDto;
import epita.projet_bensadi_vincent.entity.Client;
import epita.projet_bensadi_vincent.entity.Conseiller;
import epita.projet_bensadi_vincent.exception.ResourceNotFoundException;
import epita.projet_bensadi_vincent.mapper.ClientMapper;
import epita.projet_bensadi_vincent.repository.CarteBancaireRepository;
import epita.projet_bensadi_vincent.repository.ClientRepository;
import epita.projet_bensadi_vincent.repository.ConseillerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ConseillerRepository conseillerRepository;
    private final ClientMapper clientMapper;
    private final CarteBancaireRepository carteBancaireRepository;

    public ClientServiceImpl(ClientRepository clientRepository,
                             ConseillerRepository conseillerRepository,
                             ClientMapper clientMapper,
                             CarteBancaireRepository carteBancaireRepository) {
        this.clientRepository = clientRepository;
        this.conseillerRepository = conseillerRepository;
        this.clientMapper = clientMapper;
        this.carteBancaireRepository = carteBancaireRepository;
    }

    @Override
    public ClientDto create(ClientCreateDto clientCreateDto) {
        // Vérifier que le conseiller existe
        Conseiller conseiller = conseillerRepository.findById(clientCreateDto.getConseillerId())
                .orElseThrow(() -> new ResourceNotFoundException("Conseiller", clientCreateDto.getConseillerId()));

        // Vérifier que le conseiller n'a pas atteint son quota de clients
        if (conseiller.getClients().size() >= conseiller.getMaxClients()) {
            throw new IllegalArgumentException(
                    "Le conseiller " + conseiller.getNom() + " " + conseiller.getPrenom() +
                            " a atteint son quota maximum de " + conseiller.getMaxClients() + " clients");
        }

        // Convertir le DTO en entité
        Client client = clientMapper.toEntity(clientCreateDto);

        // Associer le conseiller au client
        client.setConseiller(conseiller);

        // Sauvegarder le client
        Client savedClient = clientRepository.save(client);

        // Retourner le DTO
        return clientMapper.toDto(savedClient);
    }

    @Override
    public ClientDto getById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));

        return clientMapper.toDto(client);
    }

    @Override
    public ClientDto update(Long id, ClientUpdateDto clientUpdateDto) {
        // Vérifier que le client existe
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));

        // Mettre à jour les champs du client avec les données du DTO
        clientMapper.updateEntity(clientUpdateDto, client);

        // Sauvegarder les modifications
        Client updatedClient = clientRepository.save(client);

        // Retourner le DTO
        return clientMapper.toDto(updatedClient);
    }

    @Override
    public void delete(Long id) {
        // Vérifier que le client existe
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));

        // Désactiver toutes les cartes bancaires associées aux comptes du client
        // La cascade sur les comptes se fera automatiquement grâce à CascadeType.ALL
        client.getComptes().forEach(compte -> {
            carteBancaireRepository.deactivateByCompte(compte);
        });

        // Supprimer le client (les comptes seront supprimés automatiquement grâce à la cascade)
        clientRepository.delete(client);
    }
}

