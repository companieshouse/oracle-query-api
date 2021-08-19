package uk.gov.ch.repository.payment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uk.gov.ch.model.payment.ConfirmationStatementPayment;

import java.sql.Date;
import java.util.Optional;


public interface ConfirmationStatementPaymentCheckRepository extends CrudRepository<ConfirmationStatementPayment, Long> {

    @Query(value = "select " +
           "CSPP.paid_by_transaction_id " +
           "from CORPORATE_BODY CB " +
           "inner join CONFIRMATION_STMT_PYMT_PERIOD CSPP on CB.CORPORATE_BODY_ID=CSPP.CORPORATE_BODY_ID " +
           "where CB.INCORPORATION_NUMBER=?1 and CSPP.PAYMENT_PERIOD_DUE_DATE=?2", nativeQuery = true)
    Optional<ConfirmationStatementPayment> findPaymentsForPeriod(String companyNumber, Date dueDate);
}
