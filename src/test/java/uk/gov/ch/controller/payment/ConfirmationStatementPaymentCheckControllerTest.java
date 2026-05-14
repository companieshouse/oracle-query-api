package uk.gov.ch.controller.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.ch.model.payment.ConfirmationStatementPaymentJson;
import uk.gov.ch.service.payment.ConfirmationStatementPaymentCheckService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ConfirmationStatementPaymentCheckController.class)
class ConfirmationStatementPaymentCheckControllerTest {

    private static final String COMPANY_NUMBER = "01234567";
    private static final String INVALID_COMPANY_NUMBER = "012#4567";
    private static final String DUE_DATE = "2022-01-01";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ConfirmationStatementPaymentCheckService confirmationStatementPaymentCheckService;

    private ConfirmationStatementPaymentCheckController confirmationStatementPaymentCheckController;

    @BeforeEach
    void setUp() {
        confirmationStatementPaymentCheckController = new ConfirmationStatementPaymentCheckController(confirmationStatementPaymentCheckService);
    }

    @Test
    void testIsConfirmationStatementPaid() {
        ConfirmationStatementPaymentJson confirmationStatementPaymentJson = new ConfirmationStatementPaymentJson();
        confirmationStatementPaymentJson.setPaid(true);
        when(confirmationStatementPaymentCheckService.isConfirmationStatementPaid(COMPANY_NUMBER, DUE_DATE)).thenReturn(confirmationStatementPaymentJson);
        ResponseEntity<ConfirmationStatementPaymentJson> response = confirmationStatementPaymentCheckController.isConfirmationStatementPaid(COMPANY_NUMBER, DUE_DATE);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isPaid());
    }

    @Test
    @DisplayName("Get trust details - invalid overseas entity number")
    void testGetConfirmationStatementPaymentCheckInvalidCompanyNumber() throws Exception {

        mockMvc.perform(get("/company/{companyNumber}/confirmation-statement/paid", INVALID_COMPANY_NUMBER))
            .andExpect(status().isBadRequest());
    }
}
