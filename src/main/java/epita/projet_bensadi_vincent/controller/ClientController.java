package epita.projet_bensadi_vincent.controller;

import epita.projet_bensadi_vincent.dto.ClientCreateDto;
import epita.projet_bensadi_vincent.dto.ClientDto;
import epita.projet_bensadi_vincent.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Crée un nouveau client
     * @param clientCreateDto les données du client à créer
     * @return le client créé avec le statut HTTP 201 (Created)
     */
    @PostMapping
    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientCreateDto clientCreateDto) {
        ClientDto createdClient = clientService.create(clientCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    /**
     * Récupère un client par son ID
     * @param id l'identifiant du client
     * @return le client trouvé avec le statut HTTP 200 (OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        ClientDto client = clientService.getById(id);
        return ResponseEntity.ok(client);
    }

    /**
     * Met à jour les coordonnées d'un client
     * @param id l'identifiant du client à modifier
     * @param clientUpdateDto les nouvelles données du client
     * @return le client mis à jour avec le statut HTTP 200 (OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long id,
                                                   @Valid @RequestBody epita.projet_bensadi_vincent.dto.ClientUpdateDto clientUpdateDto) {
        ClientDto updatedClient = clientService.update(id, clientUpdateDto);
        return ResponseEntity.ok(updatedClient);
    }

    /**
     * Supprime un client et clôture son dossier
     * Effectue les cascades :
     * - Suppression de tous les comptes associés
     * - Désactivation de toutes les cartes bancaires associées
     * @param id l'identifiant du client à supprimer
     * @return HTTP 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

