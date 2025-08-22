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
import uk.gov.ch.service.psc.IdentityVerificationExtensionDetailsService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Optional.of;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IdentityVerificationExtensionDetailsServiceImplTest {
    private static final Long EXTENSION_REQUEST_ID = 9576890767L;
    private static final LocalDate EXTENSION_REQUESTED_DATE = LocalDate.parse("9999-12-31");
    private static final LocalDate STATEMENT_DATE = LocalDate.parse("2025-06-01");
    private static final LocalDate STATEMENT_DUE_DATE = LocalDate.parse("2025-06-15");

    private IdentityVerificationExtensionDetailsService testService;

    @Mock
    private IdentityVerificationExtensionDetailsRepository repository;

    @Mock
    private IdentityVerificationExtensionDetailsMapper mapper;

    @BeforeEach
    void setUp() {
        testService = new IdentityVerificationExtensionDetailsServiceImpl(repository, mapper);
    }

    private IdentityVerificationExtensionDetails createDetails(final Long longValue, final boolean hasCurrentVerification) {
        final var details = new IdentityVerificationExtensionDetails();

        details.setExtensionRequestId(EXTENSION_REQUEST_ID);
        details.setExtensionRequestedDate(EXTENSION_REQUESTED_DATE);
        details.setPreviousAppointmentVerificationStatementDate(STATEMENT_DUE_DATE);
        if (hasCurrentVerification) {
            details.setPreviousAppointmentVerificationStatementDate(STATEMENT_DATE);
            details.setNewAppointmentVerificationStatementDueOn(STATEMENT_DUE_DATE);
        }

        return details;
    }

    static Stream<Arguments> verificationExtensionDetailsProvider() {
        return Stream.of(
            arguments(true, STATEMENT_DATE, STATEMENT_DUE_DATE),
            arguments(false, null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("verificationExtensionDetailsProvider")
    void findIdentityVerificationExtensionDetailsParameterized(boolean hasCurrentVerification, LocalDate extensionRequestDate ) {
        final var extensionDetails = createDetails(EXTENSION_REQUEST_ID, hasCurrentVerification);

        when(repository.findExtensionRequest(EXTENSION_REQUEST_ID)).thenReturn(of(extensionDetails));
        when(mapper.entityToDto(extensionDetails)).thenReturn(
            new IdentityVerificationExtensionDetailsDto(extensionRequestDate, STATEMENT_DATE, STATEMENT_DUE_DATE));

        final var result = testService.findExtensionRequest(EXTENSION_REQUEST_ID);

        assertThat(result,
            is(of(new IdentityVerificationExtensionDetailsDto(extensionRequestDate, STATEMENT_DATE, STATEMENT_DUE_DATE))));
        verify(mapper).entityToDto(extensionDetails);
    }

    @Test
    void findIdentityVerificationDetailsWhenNotFound() {
        when(repository.findExtensionRequest(EXTENSION_REQUEST_ID)).thenReturn(Optional.empty());

        final var result = testService.findExtensionRequest(EXTENSION_REQUEST_ID);

        assertThat(result.isEmpty(), is(true));
        verifyNoInteractions(mapper);
    }

}