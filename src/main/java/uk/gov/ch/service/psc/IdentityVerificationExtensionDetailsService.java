package uk.gov.ch.service.psc;

import uk.gov.ch.model.psc.IdentityVerificationExtensionDetailsDto;

import java.util.List;

public interface IdentityVerificationExtensionDetailsService {
    List<IdentityVerificationExtensionDetailsDto> findExtensionRequest(final Long appointmentId);
}
