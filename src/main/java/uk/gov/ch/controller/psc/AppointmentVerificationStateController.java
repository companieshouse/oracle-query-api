package uk.gov.ch.controller.psc;

import jakarta.validation.Valid;
import java.text.MessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.model.psc.AppointmentVerificationStateDto;
import uk.gov.ch.model.psc.VerificationStateCriteriaDto;
import uk.gov.ch.service.psc.AppointmentVerificationStateService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@RestController
public class AppointmentVerificationStateController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    private final AppointmentVerificationStateService appointmentVerificationStateService;

    public AppointmentVerificationStateController(
        @Autowired final AppointmentVerificationStateService appointmentVerificationStateService) {
        this.appointmentVerificationStateService = appointmentVerificationStateService;

    }

    @PostMapping("/corporate-body-appointments/persons-of-significant-control/verification-state")
    public ResponseEntity<AppointmentVerificationStateDto> getAppointmentVerificationState(
        @RequestBody @Valid VerificationStateCriteriaDto stateCriteria) {
        LOGGER.info(MessageFormat.format(
            "Calling service to obtain persons with significant control appointment verification state, for ID {0,number,#}",
            stateCriteria.appointmentId()));

        final var stateDto = appointmentVerificationStateService.findAppointmentVerificationState(
            stateCriteria.appointmentId());

        LOGGER.info(MessageFormat.format("Found {0,choice,0#no matches|1#one match} for appointment ID {1,number,#}",
            stateDto.stream().count(), stateCriteria.appointmentId()));

        return ResponseEntity.of(stateDto);
    }
}
