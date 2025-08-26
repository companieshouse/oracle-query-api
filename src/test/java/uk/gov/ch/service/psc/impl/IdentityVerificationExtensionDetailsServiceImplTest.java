package uk.gov.ch.service.psc.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.ch.model.psc.*;
import uk.gov.ch.repository.psc.IdentityVerificationExtensionDetailsRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IdentityVerificationExtensionDetailsServiceImplTest {
    private static final Long APPOINTMENT_ID = 9576890767L;
    private static final Long CHS_USER_ID = 12345L;
    private static final LocalDate EXTENSION_REQUESTED_DATE = LocalDate.parse("9999-12-31");
    private static final LocalDate STATEMENT_DATE = LocalDate.parse("2025-06-01");
    private static final LocalDate STATEMENT_DUE_DATE = LocalDate.parse("2025-06-15");

    @Mock
    private IdentityVerificationExtensionDetailsServiceImpl testService;

    @Mock
    private IdentityVerificationExtensionDetailsRepository repository;

    @Mock
    private IdentityVerificationExtensionDetailsMapper mapper;

    @BeforeEach
    void setUp() {
        testService = new IdentityVerificationExtensionDetailsServiceImpl(repository, mapper);
    }

    private IdentityVerificationExtensionDetails createDetails(final Long appointmentId, final long chsUserId, final boolean hasCurrentVerification) {
        final var details = new IdentityVerificationExtensionDetails();

        details.setCorporateBodyAppointmentId(appointmentId);
        details.setChsUserId(chsUserId);
        details.setExtensionRequestedDate(EXTENSION_REQUESTED_DATE);

        if (hasCurrentVerification) {
            details.setPreviousAppointmentVerificationStatementDate(STATEMENT_DATE);
            details.setNewAppointmentVerificationStatementDueOn(STATEMENT_DUE_DATE);
        } else {
            details.setPreviousAppointmentVerificationStatementDate(STATEMENT_DUE_DATE);
        }

        return details;
    }

    static Stream<Arguments> verificationExtensionDetailsProvider() {
        return Stream.of(
                Arguments.arguments(true, STATEMENT_DATE, STATEMENT_DUE_DATE),
                Arguments.arguments(false, STATEMENT_DUE_DATE, null)
        );
    }

    @ParameterizedTest
    @MethodSource("verificationExtensionDetailsProvider")
    void findIdentityVerificationExtensionDetailsParameterized(boolean hasCurrentVerification, LocalDate expectedStatementDate, LocalDate expectedDueDate) {
        final var extensionDetails = createDetails(APPOINTMENT_ID, CHS_USER_ID, hasCurrentVerification);
        List<IdentityVerificationExtensionDetails> extensionDetailsList = List.of(extensionDetails);

        when(repository.findExtensionRequest(APPOINTMENT_ID)).thenReturn(extensionDetailsList);
        when(mapper.entityToDto(extensionDetails)).thenReturn(
                new IdentityVerificationExtensionDetailsDto(
                        EXTENSION_REQUESTED_DATE,
                        expectedStatementDate,
                        expectedDueDate,
                        APPOINTMENT_ID,
                        CHS_USER_ID
                )
        );

        final var result = testService.findExtensionRequest(APPOINTMENT_ID);


        assertThat(result).containsExactly(
                new IdentityVerificationExtensionDetailsDto(
                        EXTENSION_REQUESTED_DATE,
                        expectedStatementDate,
                        expectedDueDate,
                        APPOINTMENT_ID,
                        CHS_USER_ID
                )
        );

    }

    @Test
    void findIdentityVerificationDetailsWhenNotFound() {
        when(repository.findExtensionRequest(APPOINTMENT_ID)).thenReturn(Collections.emptyList());

        final var result = testService.findExtensionRequest(APPOINTMENT_ID);

        assertThat(result).isEmpty();
        verifyNoInteractions(mapper);
    }
}