package uk.gov.ch.service.payment;

import uk.gov.ch.model.payment.ConfirmationStatementPaymentJson;

public interface ConfirmationStatementPaymentCheckService {

    ConfirmationStatementPaymentJson isConfirmationStatementPaid(String companyNumber,
            String dueDate);
}
