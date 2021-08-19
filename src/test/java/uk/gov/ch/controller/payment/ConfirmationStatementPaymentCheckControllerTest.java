package uk.gov.ch.controller.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.ch.service.payment.ConfirmationStatementPaymentCheckService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConfirmationStatementPaymentCheckControllerTest {

    private static final Long PAID_BY_TRANSACTION_ID = 01234L;
    private static String COMPANY_NUMBER = "01234567";
    private static String DUE_DATE = "2022-01-01";

    @InjectMocks
    private ConfirmationStatementPaymentCheckController confirmationStatementPaymentCheckContoller;

    @Mock
    private ConfirmationStatementPaymentCheckService confirmationStatementPaymentCheckService;

    @Test
    void testIsConfirmationStatementPaid() {
        when(confirmationStatementPaymentCheckService.isConfirmationStatementPaid(COMPANY_NUMBER, DUE_DATE)).thenReturn(true);
        ResponseEntity<Boolean> response = confirmationStatementPaymentCheckContoller.isConfirmationStatementPaid(COMPANY_NUMBER, DUE_DATE);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(Boolean.TRUE,response.getBody());
    }
}
