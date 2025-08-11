package uk.gov.ch.controller.psc;

import static com.google.common.net.HttpHeaders.X_REQUEST_ID;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.json.JsonCompareMode;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.ch.model.psc.IdentityVerificationDetailsDto;
import uk.gov.ch.service.psc.IdentityVerificationDetailsService;

@WebMvcTest(properties = {"feature.psc_verification_details_get=true"},
    controllers = IdentityVerificationDetailsController.class)
class IdentityVerificationDetailsControllerFeatureEnabledTest {
    private static final Long APPOINTMENT_ID = 9576890767L;
    private static final LocalDate START_ON = LocalDate.parse("2025-06-12");
    private static final LocalDate END_ON = LocalDate.parse("9999-12-31");
    private static final LocalDate STATEMENT_DATE = LocalDate.parse("2025-06-01");
    private static final LocalDate STATEMENT_DUE_DATE = LocalDate.parse("2025-06-15");
    private static final String ERIC_IDENTITY = "Test-Identity";
    private static final String ERIC_IDENTITY_TYPE = "key";
    private static final String ERIC_PRIVILEGES = "*";
    private static final String ERIC_AUTH_INTERNAL = "internal-app";
    private static final String REQUEST_URL = "/corporate-body-appointments/persons-of-significant-control/identity-verification-details";

    @MockitoBean
    private IdentityVerificationDetailsService identityVerificationDetailsService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private IdentityVerificationDetailsController testController;

    @Nested
    @DisplayName("When psc_verification_details_get feature flag IS enabled")
    class FeatureFlagEnabled {

        @Test
        @DisplayName("should create Controller bean")
        void contextLoads() {
            Assertions.assertThat(testController).isNotNull();
        }

        @Nested
        @DisplayName("Should return 200 status")
        class QueryResultsFound {

            @Test
            @DisplayName("with non-null verification dates when a 'current' verification exists")
            void shouldReturn200WhenDataFound() throws Exception {
                when(identityVerificationDetailsService.findIdentityVerificationDetails(APPOINTMENT_ID)).thenReturn(
                    createDetailsOptional(true));

                final String requestBody = """
                    {
                        "appointment_id" : %d
                    }
                    """.formatted(APPOINTMENT_ID);
                final String expectedData = """
                    {
                        "identity_verification_details":
                        {
                            "appointment_verification_start_on": "2025-06-12",
                            "appointment_verification_end_on" : "9999-12-31",
                            "appointment_verification_statement_date": "2025-06-01",
                            "appointment_verification_statement_due_on": "2025-06-15"
                        }
                    }
                    """;

                mockMvc.perform(post(REQUEST_URL).header("ERIC-Identity", ERIC_IDENTITY)
                    .header("ERIC-Identity-Type", ERIC_IDENTITY_TYPE)
                    .contentType(APPLICATION_JSON)
                    .content(requestBody)
                    .header("x-request-id", X_REQUEST_ID)
                    .header("ERIC-Authorised-Key-Roles", ERIC_PRIVILEGES)
                    .header("ERIC-Authorised-Key-Privileges", ERIC_AUTH_INTERNAL)).andExpect(status().isOk()).andDo(
                    print()).andExpect(content().json(expectedData, JsonCompareMode.STRICT));
            }

            @Test
            @DisplayName("without verification dates when no 'current' verification exists")
            void shouldReturn200WithNullDatesWhenNoVerification() throws Exception {
                when(identityVerificationDetailsService.findIdentityVerificationDetails(APPOINTMENT_ID)).thenReturn(
                    createDetailsOptional(false));

                final String requestBody = """
                    {
                        "appointment_id" : %d
                    }
                    """.formatted(APPOINTMENT_ID);
                final String expectedData = """
                    {
                        "identity_verification_details":
                        {
                            "appointment_verification_statement_date": "2025-06-01",
                            "appointment_verification_statement_due_on": "2025-06-15"
                        }
                    }
                    """;

                mockMvc.perform(post(REQUEST_URL).header("ERIC-Identity", ERIC_IDENTITY)
                    .header("ERIC-Identity-Type", ERIC_IDENTITY_TYPE)
                    .contentType(APPLICATION_JSON)
                    .content(requestBody)
                    .header("x-request-id", X_REQUEST_ID)
                    .header("ERIC-Authorised-Key-Roles", ERIC_PRIVILEGES)
                    .header("ERIC-Authorised-Key-Privileges", ERIC_AUTH_INTERNAL)).andExpect(status().isOk()).andDo(
                    print()).andExpect(content().json(expectedData, JsonCompareMode.STRICT));
            }

        }

        @Test
        @DisplayName("Should return 404 when no Identity Verification Details found")
        void shouldReturn404WhenNotFound() throws Exception {
            when(identityVerificationDetailsService.findIdentityVerificationDetails(APPOINTMENT_ID)).thenReturn(
                Optional.empty());

            final String requestBody = """
                {
                    "appointment_id" : %d
                }
                """.formatted(APPOINTMENT_ID);

            mockMvc.perform(post(REQUEST_URL).header("ERIC-Identity", ERIC_IDENTITY)
                .header("ERIC-Identity-Type", ERIC_IDENTITY_TYPE)
                .contentType(APPLICATION_JSON)
                .content(requestBody)
                .header("x-request-id", X_REQUEST_ID)
                .header("ERIC-Authorised-Key-Roles", ERIC_PRIVILEGES)
                .header("ERIC-Authorised-Key-Privileges", ERIC_AUTH_INTERNAL)).andDo(print()).andExpect(
                status().isNotFound());
        }
    }


    private Optional<IdentityVerificationDetailsDto> createDetailsOptional(final boolean hasCurrentVerification) {

        return Optional.of(hasCurrentVerification ? new IdentityVerificationDetailsDto(START_ON, END_ON, STATEMENT_DATE,
            STATEMENT_DUE_DATE) : new IdentityVerificationDetailsDto(null, null, STATEMENT_DATE, STATEMENT_DUE_DATE));

    }

}