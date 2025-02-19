package uk.gov.ch.controller.psc;

import static com.google.common.net.HttpHeaders.X_REQUEST_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.ch.service.psc.AppointmentVerificationStateService;

@WebMvcTest(properties = {"feature.psc_verification_state_get=false"},
    controllers = AppointmentVerificationStateController.class)
class AppointmentVerificationStateControllerFeatureDisabledTest {
    private static final Long APPOINTMENT_ID = 9576890767L;
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
    private ApplicationContext context;

    @Nested
    @DisplayName("When psc_verification_state_get feature flag not enabled")
    class FeatureFlagNotEnabled {

        @Test
        @DisplayName("should not create Controller bean")
        void shouldNotCreateControllerBean() {
            final var exception = assertThrows(NoSuchBeanDefinitionException.class,
                () -> context.getBean(AppointmentVerificationStateController.class));

            assertThat(exception.getBeanType()).isEqualTo(AppointmentVerificationStateController.class);
        }

        @Test
        @DisplayName("should return 404 status for POST request")
        void shouldReturn404NotFound() throws Exception {
            final String requestBody = """
                {
                    "appointment_id" : %d
                }
                """.formatted(APPOINTMENT_ID);

            mockMvc.perform(post(GET_VERIFICATION_STATE_URL).header("ERIC-Identity", ERIC_IDENTITY)
                .header("ERIC-Identity-Type", ERIC_IDENTITY_TYPE)
                .contentType(APPLICATION_JSON)
                .header("x-request-id", X_REQUEST_ID)
                .header("ERIC-Authorised-Key-Roles", ERIC_PRIVILEGES)
                .header("ERIC-Authorised-Key-Privileges", ERIC_AUTH_INTERNAL)
                .contentType(APPLICATION_JSON)
                .content(requestBody)).andExpect(status().isNotFound());
            verifyNoInteractions(appointmentVerificationStateService);
        }
    }

}