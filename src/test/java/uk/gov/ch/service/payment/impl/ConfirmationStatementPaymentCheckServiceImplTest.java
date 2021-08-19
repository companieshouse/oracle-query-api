package uk.gov.ch.service.payment.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.model.payment.ConfirmationStatementPayment;
import uk.gov.ch.repository.payment.ConfirmationStatementPaymentCheckRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConfirmationStatementPaymentCheckServiceImplTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final Long PAID_BY_TRANSACTION_ID = 01234L;
    private static String COMPANY_NUMBER = "01234567";
    private static String DUE_DATE = "2022-01-01";

    @InjectMocks
    private ConfirmationStatementPaymentCheckServiceImpl confirmationStatementPaymentCheckServiceImpl;

    @Mock
    private ConfirmationStatementPaymentCheckRepository confirmationStatementPaymentCheckRepository;


    @Test
    void testIsConfirmationStatementPaid() {
        ConfirmationStatementPayment confirmationStatementPayment = new ConfirmationStatementPayment();
        confirmationStatementPayment.setPaidByTransactionId(PAID_BY_TRANSACTION_ID);
        Date dueDateSql = Date.valueOf(LocalDate.parse(DUE_DATE, dateTimeFormatter));
        when(confirmationStatementPaymentCheckRepository.findPaymentsForPeriod(COMPANY_NUMBER, dueDateSql)).thenReturn(Optional.of(confirmationStatementPayment));
        boolean isPaid = confirmationStatementPaymentCheckServiceImpl.isConfirmationStatementPaid(COMPANY_NUMBER, DUE_DATE);
        assertTrue(isPaid);
    }

    @Test
    void testIsNotConfirmationStatementPaidForEmptyResult() {
        ConfirmationStatementPayment confirmationStatementPayment = new ConfirmationStatementPayment();
        confirmationStatementPayment.setPaidByTransactionId(PAID_BY_TRANSACTION_ID);
        Date dueDateSql = Date.valueOf(LocalDate.parse(DUE_DATE, dateTimeFormatter));
        when(confirmationStatementPaymentCheckRepository.findPaymentsForPeriod(COMPANY_NUMBER, dueDateSql)).thenReturn(Optional.empty());
        boolean isPaid = confirmationStatementPaymentCheckServiceImpl.isConfirmationStatementPaid(COMPANY_NUMBER, DUE_DATE);
        assertFalse(isPaid);
    }

    @Test
    void testIsNotConfirmationStatementPaidForResultButNoId() {
        ConfirmationStatementPayment confirmationStatementPayment = new ConfirmationStatementPayment();
        Date dueDateSql = Date.valueOf(LocalDate.parse(DUE_DATE, dateTimeFormatter));
        when(confirmationStatementPaymentCheckRepository.findPaymentsForPeriod(COMPANY_NUMBER, dueDateSql)).thenReturn(Optional.of(confirmationStatementPayment));
        boolean isPaid = confirmationStatementPaymentCheckServiceImpl.isConfirmationStatementPaid(COMPANY_NUMBER, DUE_DATE);
        assertFalse(isPaid);
    }
}
