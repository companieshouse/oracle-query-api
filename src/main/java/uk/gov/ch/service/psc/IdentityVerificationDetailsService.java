package uk.gov.ch.service.psc;

import java.util.Optional;
import uk.gov.ch.model.psc.IdentityVerificationDetailsDto;

public interface IdentityVerificationDetailsService {
    Optional<IdentityVerificationDetailsDto> findIdentityVerificationDetails(final Long appointmentId);
}
