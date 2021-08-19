package uk.gov.ch.service.payment;

public interface ConfirmationStatementPaymentCheckService {

    boolean isConfirmationStatementPaid(String companyNumber, String dueDate);
}
