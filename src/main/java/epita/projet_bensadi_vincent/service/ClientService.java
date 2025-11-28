package epita.projet_bensadi_vincent.service;

import epita.projet_bensadi_vincent.dto.ClientCreateDto;
import epita.projet_bensadi_vincent.dto.ClientDto;

public interface ClientService {

    /**
     * Crée un nouveau client
     * @param clientCreateDto les données du client à créer
     * @return le client créé
     * @throws epita.projet_bensadi_vincent.exception.ResourceNotFoundException si le conseiller n'existe pas (HTTP 404)
     * @throws IllegalArgumentException si le conseiller a atteint son quota (HTTP 400)
     */
    ClientDto create(ClientCreateDto clientCreateDto);

    /**
     * Récupère un client par son ID
     * @param id l'identifiant du client
     * @return le client trouvé
     * @throws epita.projet_bensadi_vincent.exception.ResourceNotFoundException si le client n'existe pas (HTTP 404)
     */
    ClientDto getById(Long id);

    /**
     * Met à jour les coordonnées d'un client
     * @param id l'identifiant du client à modifier
     * @param clientUpdateDto les nouvelles données du client
     * @return le client mis à jour
     * @throws epita.projet_bensadi_vincent.exception.ResourceNotFoundException si le client n'existe pas (HTTP 404)
     */
    ClientDto update(Long id, epita.projet_bensadi_vincent.dto.ClientUpdateDto clientUpdateDto);

    /**
     * Supprime un client et effectue les cascades nécessaires :
     * - Suppression de tous les comptes associés
     * - Désactivation de toutes les cartes bancaires associées
     * @param id l'identifiant du client à supprimer
     * @throws epita.projet_bensadi_vincent.exception.ResourceNotFoundException si le client n'existe pas (HTTP 404)
     */
    void delete(Long id);
}
