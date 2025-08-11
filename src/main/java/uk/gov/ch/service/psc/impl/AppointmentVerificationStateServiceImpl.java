package uk.gov.ch.service.psc.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import uk.gov.ch.model.psc.AppointmentVerificationStateDto;
import uk.gov.ch.model.psc.AppointmentVerificationStateMapper;
import uk.gov.ch.repository.psc.AppointmentVerificationStateRepository;
import uk.gov.ch.service.psc.AppointmentVerificationStateService;

@Service
@ConditionalOnProperty(prefix = "feature", name = "psc_verification_state_get", havingValue = "true")
public class AppointmentVerificationStateServiceImpl implements AppointmentVerificationStateService {
    private final AppointmentVerificationStateRepository appointmentVerificationStateRepository;
    private final AppointmentVerificationStateMapper appointmentVerificationStateMapper;

    public AppointmentVerificationStateServiceImpl(
        @Autowired final AppointmentVerificationStateRepository appointmentVerificationStateRepository,
        @Autowired final AppointmentVerificationStateMapper appointmentVerificationStateMapper) {
        this.appointmentVerificationStateRepository = appointmentVerificationStateRepository;
        this.appointmentVerificationStateMapper = appointmentVerificationStateMapper;
    }

    @Override
    public Optional<AppointmentVerificationStateDto> findAppointmentVerificationState(final Long appointmentId) {
        return appointmentVerificationStateRepository.findAppointmentVerificationState(appointmentId)
            .map(appointmentVerificationStateMapper::entityToDto);
    }
}
