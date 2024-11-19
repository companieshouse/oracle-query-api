package uk.gov.ch.repository.payment;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uk.gov.ch.model.payment.ConfirmationStatementPayment;

public interface ConfirmationStatementPaymentCheckRepository extends
        CrudRepository<ConfirmationStatementPayment, Long> {

    @Query(value = "select " +
            "CSPP.paid_by_transaction_id " +
            "from CORPORATE_BODY CB " +
            "inner join CONFIRMATION_STMT_PYMT_PERIOD CSPP on CB.CORPORATE_BODY_ID=CSPP.CORPORATE_BODY_ID "
            +
            "where CSPP.PAYMENT_PERIOD_DUE_DATE >= TO_DATE (?2, 'YYYY-MM-dd') " +
            "and CSPP.PAYMENT_PERIOD_START_DATE <= TO_DATE (?2, 'YYYY-MM-dd') " +
            "and CB.INCORPORATION_NUMBER=?1", nativeQuery = true)
    Optional<ConfirmationStatementPayment> findPaymentsForPeriod(String companyNumber,
            String madeUpToDate);
}
