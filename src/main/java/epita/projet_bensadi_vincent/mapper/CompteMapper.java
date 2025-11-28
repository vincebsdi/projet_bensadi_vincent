package epita.projet_bensadi_vincent.mapper;

import epita.projet_bensadi_vincent.dto.CompteDto;
import epita.projet_bensadi_vincent.entity.Compte;
import epita.projet_bensadi_vincent.entity.CompteCourant;
import epita.projet_bensadi_vincent.entity.CompteEpargne;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompteMapper {

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(target = "typeCompte", expression = "java(getTypeCompte(compte))")
    @Mapping(target = "decouvertAutorise", expression = "java(getDecouvertAutorise(compte))")
    @Mapping(target = "tauxRemuneration", expression = "java(getTauxRemuneration(compte))")
    CompteDto toDto(Compte compte);

    default String getTypeCompte(Compte compte) {
        if (compte instanceof CompteCourant) {
            return "COMPTE_COURANT";
        } else if (compte instanceof CompteEpargne) {
            return "COMPTE_EPARGNE";
        }
        return null;
    }

    default java.math.BigDecimal getDecouvertAutorise(Compte compte) {
        if (compte instanceof CompteCourant) {
            return ((CompteCourant) compte).getDecouvertAutorise();
        }
        return null;
    }

    default java.math.BigDecimal getTauxRemuneration(Compte compte) {
        if (compte instanceof CompteEpargne) {
            return ((CompteEpargne) compte).getTauxRemuneration();
        }
        return null;
    }
}

