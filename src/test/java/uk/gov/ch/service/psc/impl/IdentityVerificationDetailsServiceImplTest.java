package uk.gov.ch.service.psc.impl;

import static java.util.Optional.of;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.ch.model.psc.IdentityVerificationDetails;
import uk.gov.ch.model.psc.IdentityVerificationDetailsDto;
import uk.gov.ch.model.psc.IdentityVerificationDetailsMapper;
import uk.gov.ch.repository.psc.IdentityVerificationDetailsRepository;
import uk.gov.ch.service.psc.IdentityVerificationDetailsService;

@ExtendWith(MockitoExtension.class)
class IdentityVerificationDetailsServiceImplTest {
    private static final Long APPOINTMENT_ID = 9576890767L;
    private static final LocalDate START_ON = LocalDate.parse("2025-06-12");
    private static final LocalDate END_ON = LocalDate.parse("9999-12-31");
    private static final LocalDate STATEMENT_DATE = LocalDate.parse("2025-06-01");
    private static final LocalDate STATEMENT_DUE_DATE = LocalDate.parse("2025-06-15");

    private IdentityVerificationDetailsService testService;

    @Mock
    private IdentityVerificationDetailsRepository repository;

    @Mock
    private IdentityVerificationDetailsMapper mapper;

    @BeforeEach
    void setUp() {
        testService = new IdentityVerificationDetailsServiceImpl(repository, mapper);
    }

    private IdentityVerificationDetails createDetails(final Long longValue, final boolean hasCurrentVerification) {
        final var details = new IdentityVerificationDetails();

        details.setCorporateBodyAppointmentId(longValue);
        details.setAppointmentVerificationStatementDate(STATEMENT_DATE);
        details.setAppointmentVerificationStatementDueDate(STATEMENT_DUE_DATE);
        if (hasCurrentVerification) {
            details.setAppointmentVerificationStartOn(START_ON);
            details.setAppointmentVerificationEndOn(END_ON);
        }

        return details;
    }

    static Stream<Arguments> verificationDetailsProvider() {
        return Stream.of(
            arguments(true, START_ON, END_ON),
            arguments(false, null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("verificationDetailsProvider")
    void findIdentityVerificationDetailsParameterized(boolean hasCurrentVerification, LocalDate expectedStart, LocalDate expectedEnd) {
        final var verificationDetails = createDetails(APPOINTMENT_ID, hasCurrentVerification);

        when(repository.findIdentityVerificationDetails(APPOINTMENT_ID)).thenReturn(of(verificationDetails));
        when(mapper.entityToDto(verificationDetails)).thenReturn(
            new IdentityVerificationDetailsDto(expectedStart, expectedEnd, STATEMENT_DATE, STATEMENT_DUE_DATE));

        final var result = testService.findIdentityVerificationDetails(APPOINTMENT_ID);

        assertThat(result,
            is(of(new IdentityVerificationDetailsDto(expectedStart, expectedEnd, STATEMENT_DATE, STATEMENT_DUE_DATE))));
        verify(mapper).entityToDto(verificationDetails);
    }

    @Test
    void findIdentityVerificationDetailsWhenNotFound() {
        when(repository.findIdentityVerificationDetails(APPOINTMENT_ID)).thenReturn(Optional.empty());

        final var result = testService.findIdentityVerificationDetails(APPOINTMENT_ID);

        assertThat(result.isEmpty(), is(true));
        verifyNoInteractions(mapper);
    }

}