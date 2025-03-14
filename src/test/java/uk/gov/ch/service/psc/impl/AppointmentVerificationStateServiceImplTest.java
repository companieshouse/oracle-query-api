package uk.gov.ch.service.psc.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.ch.model.psc.AppointmentVerificationState;
import uk.gov.ch.model.psc.AppointmentVerificationStateDto;
import uk.gov.ch.model.psc.AppointmentVerificationStateMapper;
import uk.gov.ch.model.psc.VerificationStatusType;
import uk.gov.ch.repository.psc.AppointmentVerificationStateRepository;
import uk.gov.ch.service.psc.AppointmentVerificationStateService;

@ExtendWith(MockitoExtension.class)
class AppointmentVerificationStateServiceImplTest {
    private static final Long APPOINTMENT_ID = 9576890767L;
    private static final LocalDate DATE1 = LocalDate.parse("2025-04-20");
    private static final LocalDate DATE2 = LocalDate.parse("2025-05-04");

    private AppointmentVerificationStateService testService;

    @Mock
    private AppointmentVerificationStateRepository repository;

    @Mock
    private AppointmentVerificationStateMapper mapper;

    private AppointmentVerificationState verificationState;

    @BeforeEach
    void setUp() {
        testService = new AppointmentVerificationStateServiceImpl(repository, mapper);
        verificationState = createState(1L);
    }

    private AppointmentVerificationState createState(final Long longValue) {
        final var state = new AppointmentVerificationState();

        state.setVerificationStatusType(longValue);
        state.setVerificationStartDate(DATE1);
        state.setVerificationStatementDue(DATE2);

        return state;
    }

    @Test
    void findAppointmentVerificationStateWhenFoundAndMappedWithStatus() {
        when(repository.findAppointmentVerificationState(APPOINTMENT_ID)).thenReturn(Optional.of(verificationState));
        when(mapper.entityToDto(verificationState)).thenReturn(
            new AppointmentVerificationStateDto(VerificationStatusType.UNVERIFIED, DATE1, DATE2));

        final var result = testService.findAppointmentVerificationState(APPOINTMENT_ID);

        assertThat(result, is(Optional.of(new AppointmentVerificationStateDto(VerificationStatusType.UNVERIFIED, DATE1, DATE2))));
        verify(mapper).entityToDto(verificationState);
    }

    @Test
    void findAppointmentVerificationStateWhenFoundAndMappedWithoutStatus() {
        verificationState = createState(null);
        when(repository.findAppointmentVerificationState(APPOINTMENT_ID)).thenReturn(Optional.of(verificationState));
        when(mapper.entityToDto(verificationState)).thenReturn(new AppointmentVerificationStateDto(null, DATE1, DATE2));

        final var result = testService.findAppointmentVerificationState(APPOINTMENT_ID);

        assertThat(result, is(Optional.of(new AppointmentVerificationStateDto(null, DATE1, DATE2))));
        verify(mapper).entityToDto(verificationState);
    }

    @Test
    void findAppointmentVerificationStateWhenNotFound() {
        when(repository.findAppointmentVerificationState(APPOINTMENT_ID)).thenReturn(Optional.empty());

        final var result = testService.findAppointmentVerificationState(APPOINTMENT_ID);

        assertThat(result.isEmpty(), is(true));
        verifyNoInteractions(mapper);
    }
}