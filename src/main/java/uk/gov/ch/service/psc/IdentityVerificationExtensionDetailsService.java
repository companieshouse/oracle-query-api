package uk.gov.ch.service.psc;

import uk.gov.ch.model.psc.IdentityVerificationExtensionDetailsDto;
import java.util.Optional;

public interface IdentityVerificationExtensionDetailsService {
    Optional<IdentityVerificationExtensionDetailsDto> findExtensionRequest(final Long extensionRequestId);
}
