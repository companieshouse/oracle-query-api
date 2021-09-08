package uk.gov.ch.service.payment.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.ch.model.payment.ConfirmationStatementPayment;
import uk.gov.ch.model.payment.ConfirmationStatementPaymentJson;
import uk.gov.ch.repository.payment.ConfirmationStatementPaymentCheckRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConfirmationStatementPaymentCheckServiceImplTest {

    private static final Long PAID_BY_TRANSACTION_ID = 01234L;
    private static String COMPANY_NUMBER = "01234567";
    private static String MADE_UP_TO_DATE = "2022-01-01";

    @InjectMocks
    private ConfirmationStatementPaymentCheckServiceImpl confirmationStatementPaymentCheckServiceImpl;

    @Mock
    private ConfirmationStatementPaymentCheckRepository confirmationStatementPaymentCheckRepository;

    @Test
    void testIsConfirmationStatementPaid() {
        ConfirmationStatementPayment confirmationStatementPayment = new ConfirmationStatementPayment();
        confirmationStatementPayment.setPaidByTransactionId(PAID_BY_TRANSACTION_ID);
        when(confirmationStatementPaymentCheckRepository.findPaymentsForPeriod(COMPANY_NUMBER, MADE_UP_TO_DATE)).thenReturn(Optional.of(confirmationStatementPayment));
        ConfirmationStatementPaymentJson confirmationStatementPaymentJson = confirmationStatementPaymentCheckServiceImpl.isConfirmationStatementPaid(COMPANY_NUMBER, MADE_UP_TO_DATE);
        assertEquals(Boolean.TRUE, confirmationStatementPaymentJson.isPaid());
    }

    @Test
    void testIsNotConfirmationStatementPaidForEmptyResult() {
        ConfirmationStatementPayment confirmationStatementPayment = new ConfirmationStatementPayment();
        confirmationStatementPayment.setPaidByTransactionId(PAID_BY_TRANSACTION_ID);
        when(confirmationStatementPaymentCheckRepository.findPaymentsForPeriod(COMPANY_NUMBER, MADE_UP_TO_DATE)).thenReturn(Optional.empty());
        ConfirmationStatementPaymentJson confirmationStatementPaymentJson = confirmationStatementPaymentCheckServiceImpl.isConfirmationStatementPaid(COMPANY_NUMBER, MADE_UP_TO_DATE);
        assertEquals(Boolean.FALSE, confirmationStatementPaymentJson.isPaid());
    }

    @Test
    void testIsNotConfirmationStatementPaidForResultButNoId() {
        ConfirmationStatementPayment confirmationStatementPayment = new ConfirmationStatementPayment();
        when(confirmationStatementPaymentCheckRepository.findPaymentsForPeriod(COMPANY_NUMBER, MADE_UP_TO_DATE)).thenReturn(Optional.of(confirmationStatementPayment));
        ConfirmationStatementPaymentJson confirmationStatementPaymentJson = confirmationStatementPaymentCheckServiceImpl.isConfirmationStatementPaid(COMPANY_NUMBER, MADE_UP_TO_DATE);
        assertEquals(Boolean.FALSE, confirmationStatementPaymentJson.isPaid());
    }
}
