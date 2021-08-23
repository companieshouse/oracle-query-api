package uk.gov.ch.controller.payment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.ch.model.payment.ConfirmationStatementPaymentJson;
import uk.gov.ch.service.payment.ConfirmationStatementPaymentCheckService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConfirmationStatementPaymentCheckControllerTest {

    private static final String COMPANY_NUMBER = "01234567";
    private static final String DUE_DATE = "2022-01-01";

    @InjectMocks
    private ConfirmationStatementPaymentCheckController confirmationStatementPaymentCheckController;

    @Mock
    private ConfirmationStatementPaymentCheckService confirmationStatementPaymentCheckService;

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
}
