package epita.projet_bensadi_vincent.dto;

import epita.projet_bensadi_vincent.entity.TypeClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
    private String codePostal;
    private String ville;
    private TypeClient typeClient;
    private Long conseillerId;
}

