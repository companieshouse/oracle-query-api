package uk.gov.ch.repository.transaction;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import uk.gov.ch.model.transaction.sqldatamodels.Gaz2TransactionDataModel;

public interface Gaz2TransactionRepository extends PagingAndSortingRepository<Gaz2TransactionDataModel, Long> {
    @Query(value = "select tr.TRANSACTION_ID, tr.TRANSACTION_TYPE_ID, tr.TRANSACTION_STATUS_TYPE_ID " +
            "from corporate_body cb, transaction tr " +
            "where tr.CORPORATE_BODY_ID = cb.CORPORATE_BODY_ID " +
            "and tr.TRANSACTION_TYPE_ID in (541, 542) " +
            "and tr.TRANSACTION_STATUS_TYPE_ID = 26 " +
            "and cb.INCORPORATION_NUMBER = :incorp_number",
            nativeQuery = true)
    Gaz2TransactionDataModel findRequestedGaz2(@Param("incorp_number") String incorporationNumber);
}
