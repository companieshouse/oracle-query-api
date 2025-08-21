package uk.gov.ch.model.psc;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IdentityVerificationExtensionDetailsMapper {
    IdentityVerificationExtensionDetailsDto entityToDto(final IdentityVerificationExtensionDetails entity);

}
