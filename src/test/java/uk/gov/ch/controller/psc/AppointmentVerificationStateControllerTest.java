package uk.gov.ch.controller.psc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import uk.gov.ch.model.psc.AppointmentVerificationStateDto;
import uk.gov.ch.model.psc.VerificationStateCriteriaDto;
import uk.gov.ch.model.psc.VerificationStatusType;
import uk.gov.ch.service.psc.AppointmentVerificationStateService;

@ExtendWith(MockitoExtension.class)
class AppointmentVerificationStateControllerTest {
    private static final Long APPOINTMENT_ID = 9576890767L;
    private static final LocalDate DATE1 = LocalDate.parse("2025-04-20");
    private static final LocalDate DATE2 = LocalDate.parse("2025-05-04");

    private AppointmentVerificationStateController testController;

    @Mock
    private AppointmentVerificationStateService service;
    private AppointmentVerificationStateDto stateDto;

    @BeforeEach
    void setUp() {
        testController = new AppointmentVerificationStateController(service);
        stateDto = new AppointmentVerificationStateDto(VerificationStatusType.UNVERIFIED, DATE1, DATE2);
    }

    @Test
    void getAppointmentVerificationStateWhenFound() {
        when(service.findAppointmentVerificationState(APPOINTMENT_ID)).thenReturn(Optional.of(stateDto));

        final var result = testController.getAppointmentVerificationState(
            new VerificationStateCriteriaDto(APPOINTMENT_ID));

        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), is(stateDto));
    }

    @Test
    void getAppointmentVerificationStateWhenNotFound() {
        when(service.findAppointmentVerificationState(APPOINTMENT_ID)).thenReturn(Optional.empty());

        final var result = testController.getAppointmentVerificationState(
            new VerificationStateCriteriaDto(APPOINTMENT_ID));

        assertThat(result.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(result.hasBody(), is(false));
    }
}