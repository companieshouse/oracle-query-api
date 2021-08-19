package uk.gov.ch.service.payment;

import java.time.LocalDate;

public interface ConfirmationStatementPaymentCheckService {

    boolean isConfirmationStatementPaid(String companyNumber, String dueDate);
}
