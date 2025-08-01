package uk.gov.ch.model.psc;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IdentityVerificationDetailsMapper {
    IdentityVerificationDetailsDto entityToDto(final IdentityVerificationDetails entity);

}
