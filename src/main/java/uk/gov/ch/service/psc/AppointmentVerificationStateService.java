package uk.gov.ch.service.psc;

import java.util.Optional;
import uk.gov.ch.model.psc.AppointmentVerificationStateDto;

public interface AppointmentVerificationStateService {
    Optional<AppointmentVerificationStateDto> findAppointmentVerificationState(final Long appointmentId);
}
