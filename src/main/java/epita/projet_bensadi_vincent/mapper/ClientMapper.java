package epita.projet_bensadi_vincent.mapper;

import epita.projet_bensadi_vincent.dto.ClientCreateDto;
import epita.projet_bensadi_vincent.dto.ClientDto;
import epita.projet_bensadi_vincent.dto.ClientUpdateDto;
import epita.projet_bensadi_vincent.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {

    @Mapping(source = "conseiller.id", target = "conseillerId")
    ClientDto toDto(Client client);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "conseiller", ignore = true)
    @Mapping(target = "comptes", ignore = true)
    Client toEntity(ClientCreateDto clientCreateDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "conseiller", ignore = true)
    @Mapping(target = "comptes", ignore = true)
    void updateEntity(ClientUpdateDto clientUpdateDto, @MappingTarget Client client);

}
