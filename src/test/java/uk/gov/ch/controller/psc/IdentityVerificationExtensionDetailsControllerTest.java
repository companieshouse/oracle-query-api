package uk.gov.ch.controller.psc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import uk.gov.ch.model.psc.IdentityVerificationExtensionDetailsCriteriaDto;
import uk.gov.ch.model.psc.IdentityVerificationExtensionDetailsDto;
import uk.gov.ch.service.psc.IdentityVerificationExtensionDetailsService;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IdentityVerificationExtensionDetailsControllerTest {
    private static final Long EXTENSION_REQUEST_ID = 9576890767L;
    private static final LocalDate EXTENSION_REQUESTED_DATE = LocalDate.parse("9999-12-31");
    private static final LocalDate STATEMENT_DATE = LocalDate.parse("2025-06-01");
    private static final LocalDate STATEMENT_DUE_DATE = LocalDate.parse("2025-06-15");

    private IdentityVerificationExtensionDetailsController testController;

    @Mock
    private IdentityVerificationExtensionDetailsService service;
    private IdentityVerificationExtensionDetailsDto detailsDto;

    @BeforeEach
    void setUp() {
        testController = new IdentityVerificationExtensionDetailsController(service);
        detailsDto = new IdentityVerificationExtensionDetailsDto(EXTENSION_REQUESTED_DATE, STATEMENT_DATE, STATEMENT_DUE_DATE);
    }

    @Test
    void getIdentityVerificationExtensionDetailsWhenFound() {
        when(service.findExtensionRequest(EXTENSION_REQUEST_ID)).thenReturn(Optional.of(detailsDto));

        final var result = testController.getIdentityVerificationExtensionRequestDetails(
            new IdentityVerificationExtensionDetailsCriteriaDto(EXTENSION_REQUEST_ID));

        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), is(detailsDto));
    }

    @Test
    void getIdentityVerificationExtensionDetailsWhenNotFound() {
        when(service.findExtensionRequest(EXTENSION_REQUEST_ID)).thenReturn(Optional.empty());

        final var result = testController.getIdentityVerificationExtensionRequestDetails(
            new IdentityVerificationExtensionDetailsCriteriaDto(EXTENSION_REQUEST_ID));

        assertThat(result.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(result.hasBody(), is(false));
    }
}