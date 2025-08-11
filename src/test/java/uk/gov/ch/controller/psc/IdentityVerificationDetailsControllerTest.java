package uk.gov.ch.controller.psc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import uk.gov.ch.model.psc.IdentityVerificationDetailsCriteriaDto;
import uk.gov.ch.model.psc.IdentityVerificationDetailsDto;
import uk.gov.ch.service.psc.IdentityVerificationDetailsService;

@ExtendWith(MockitoExtension.class)
class IdentityVerificationDetailsControllerTest {
    private static final Long APPOINTMENT_ID = 9576890767L;
    private static final LocalDate START_ON = LocalDate.parse("2025-06-12");
    private static final LocalDate END_ON = LocalDate.parse("9999-12-31");
    private static final LocalDate STATEMENT_DATE = LocalDate.parse("2025-06-01");
    private static final LocalDate STATEMENT_DUE_DATE = LocalDate.parse("2025-06-15");

    private IdentityVerificationDetailsController testController;

    @Mock
    private IdentityVerificationDetailsService service;
    private IdentityVerificationDetailsDto detailsDto;

    @BeforeEach
    void setUp() {
        testController = new IdentityVerificationDetailsController(service);
        detailsDto = new IdentityVerificationDetailsDto(START_ON, END_ON, STATEMENT_DATE, STATEMENT_DUE_DATE);
    }

    @Test
    void getIdentityVerificationDetailsWhenFound() {
        when(service.findIdentityVerificationDetails(APPOINTMENT_ID)).thenReturn(Optional.of(detailsDto));

        final var result = testController.getIdentityVerificationDetails(
            new IdentityVerificationDetailsCriteriaDto(APPOINTMENT_ID));

        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), is(Map.of("identity_verification_details", detailsDto)));
    }

    @Test
    void getIdentityVerificationDetailsWhenNotFound() {
        when(service.findIdentityVerificationDetails(APPOINTMENT_ID)).thenReturn(Optional.empty());

        final var result = testController.getIdentityVerificationDetails(
            new IdentityVerificationDetailsCriteriaDto(APPOINTMENT_ID));

        assertThat(result.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(result.hasBody(), is(false));
    }
}