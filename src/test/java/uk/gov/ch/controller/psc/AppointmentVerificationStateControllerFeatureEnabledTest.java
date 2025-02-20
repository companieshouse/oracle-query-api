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
import uk.gov.ch.model.psc.AppointmentVerificationStateDto;
import uk.gov.ch.model.psc.VerificationStatusType;
import uk.gov.ch.service.psc.AppointmentVerificationStateService;

@WebMvcTest(properties = {"feature.psc_verification_state_get=true"},
    controllers = AppointmentVerificationStateController.class)
class AppointmentVerificationStateControllerFeatureEnabledTest {
    private static final Long APPOINTMENT_ID = 9576890767L;
    private static final LocalDate DATE1 = LocalDate.parse("2025-04-20");
    private static final LocalDate DATE2 = LocalDate.parse("2025-05-04");
    private static final String ERIC_IDENTITY = "Test-Identity";
    private static final String ERIC_IDENTITY_TYPE = "key";
    private static final String ERIC_PRIVILEGES = "*";
    private static final String ERIC_AUTH_INTERNAL = "internal-app";
    private static final String GET_VERIFICATION_STATE_URL = "/corporate-body-appointments/persons-of-significant-control/verification-state";

    @MockitoBean
    private AppointmentVerificationStateService appointmentVerificationStateService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AppointmentVerificationStateController testController;

    @Test
    void contextLoads() {
        Assertions.assertThat(testController).isNotNull();
    }

    @Nested
    @DisplayName("Should return 200 status")
    class QueryResultsFound {

        @Test
        @DisplayName("with verification_status when not null")
        void shouldReturn200WhenDataFound() throws Exception {
            when(appointmentVerificationStateService.findAppointmentVerificationState(APPOINTMENT_ID)).thenReturn(
                createVerificationState(VerificationStatusType.UNVERIFIED));

            final String requestBody = """
            {
                "appointment_id" : %d
            }
            """.formatted(APPOINTMENT_ID);
            final String expectedData = """
            {
              "verification_status": "UNVERIFIED",
              "verification_start_date": "2025-04-20",
              "verification_statement_due_date" : "2025-05-04"
            }
            """;

            mockMvc.perform(post(GET_VERIFICATION_STATE_URL).header("ERIC-Identity", ERIC_IDENTITY)
                    .header("ERIC-Identity-Type", ERIC_IDENTITY_TYPE)
                    .contentType(APPLICATION_JSON)
                    .content(requestBody)
                    .header("x-request-id", X_REQUEST_ID)
                    .header("ERIC-Authorised-Key-Roles", ERIC_PRIVILEGES)
                    .header("ERIC-Authorised-Key-Privileges", ERIC_AUTH_INTERNAL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(expectedData, JsonCompareMode.STRICT));
        }

        @Test
        @DisplayName("without verification_status when null")
        void shouldReturn200WithNullStatusWhenNotFound() throws Exception {
            when(appointmentVerificationStateService.findAppointmentVerificationState(APPOINTMENT_ID)).thenReturn(
                createVerificationState(null));

            final String requestBody = """
            {
                "appointment_id" : %d
            }
            """.formatted(APPOINTMENT_ID);
            final String expectedData = """
            {
              "verification_start_date": "2025-04-20",
              "verification_statement_due_date" : "2025-05-04"
            }
            """;

            mockMvc.perform(post(GET_VERIFICATION_STATE_URL).header("ERIC-Identity", ERIC_IDENTITY)
                    .header("ERIC-Identity-Type", ERIC_IDENTITY_TYPE)
                    .contentType(APPLICATION_JSON)
                    .content(requestBody)
                    .header("x-request-id", X_REQUEST_ID)
                    .header("ERIC-Authorised-Key-Roles", ERIC_PRIVILEGES)
                    .header("ERIC-Authorised-Key-Privileges", ERIC_AUTH_INTERNAL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(expectedData, JsonCompareMode.STRICT));
        }

    }

    @Test
    @DisplayName("Should return 404 when Verification State not found")
    void shouldReturn404WhenNotFound() throws Exception {
        when(appointmentVerificationStateService.findAppointmentVerificationState(APPOINTMENT_ID)).thenReturn(
            Optional.empty());

        final String requestBody = """
            {
                "appointment_id" : %d
            }
            """.formatted(APPOINTMENT_ID);

        mockMvc.perform(post(GET_VERIFICATION_STATE_URL).header("ERIC-Identity", ERIC_IDENTITY)
                .header("ERIC-Identity-Type", ERIC_IDENTITY_TYPE)
                .contentType(APPLICATION_JSON)
                .content(requestBody)
                .header("x-request-id", X_REQUEST_ID)
                .header("ERIC-Authorised-Key-Roles", ERIC_PRIVILEGES)
                .header("ERIC-Authorised-Key-Privileges", ERIC_AUTH_INTERNAL))
            .andDo(print())
            .andExpect(status().isNotFound());
    }


    private Optional<AppointmentVerificationStateDto> createVerificationState(final VerificationStatusType status) {
        return Optional.of(new AppointmentVerificationStateDto(status, DATE1, DATE2));
    }

}