package uk.gov.ch.service.psc.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ch.model.psc.AppointmentVerificationStateDto;
import uk.gov.ch.model.psc.AppointmentVerificationStateMapper;
import uk.gov.ch.repository.psc.AppointmentVerificationStateRepository;
import uk.gov.ch.service.psc.AppointmentVerificationStateService;

@Service
public class AppointmentVerificationStateServiceImpl implements AppointmentVerificationStateService {
    private AppointmentVerificationStateRepository appointmentVerificationStateRepository;
    private AppointmentVerificationStateMapper appointmentVerificationStateMapper;

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
