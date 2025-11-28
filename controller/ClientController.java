package epita.projet_bensadi_vincent.controller;
// ...existing imports...

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    /**
     * Constructeur du contrôleur ClientController.
     * @param clientService Service de gestion des clients
     */
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Crée un nouveau client dans le système.
     * <p>Valide les champs obligatoires et persiste le client en base.</p>
     * @param clientCreateDto les données du client à créer
     * @return le client créé avec le statut HTTP 201 (Created)
     */
    @PostMapping
    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientCreateDto clientCreateDto) {
        ClientDto createdClient = clientService.create(clientCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    /**
     * Récupère les informations d'un client par son identifiant.
     * @param id l'identifiant du client
     * @return le client trouvé avec le statut HTTP 200 (OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        ClientDto client = clientService.getById(id);
        return ResponseEntity.ok(client);
    }

    /**
     * Modifie les coordonnées d'un client existant.
     * <p>Valide les champs et met à jour le client en base.</p>
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
     * Supprime un client et clôture son dossier.
     * <p>Effectue la suppression en cascade des comptes associés et la désactivation des cartes bancaires.</p>
     * @param id l'identifiant du client à supprimer
     * @return HTTP 204 (No Content) si suppression réussie
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

