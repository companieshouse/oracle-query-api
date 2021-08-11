package uk.gov.ch.repository.shareholder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.shareholder.Shareholder;

public interface ShareholderRepository  extends PagingAndSortingRepository<Shareholder, Long> {

    @Query(value = "SELECT "
            + "sh.SHAREHOLDER_ID, "
            + "sh.SHAREHOLDER_FORENAME_1 AS FORENAME_1, "
            + "sh.SHAREHOLDER_FORENAME_2 AS FORENAME_2, "
            + "sh.SHAREHOLDER_SURNAME AS SURNAME, "
            + "shd.NUMBER_OF_SHARES, "
            + "shd.SHARE_CLASS_TYPE_ID, "
            + "shd.CURRENCY_TYPE_ID "
            + "FROM shareholder sh INNER JOIN shareholding shd ON sh.SHAREHOLDING_ID = shd.SHAREHOLDING_ID "
            + "JOIN corporate_body cb ON cb.CORPORATE_BODY_ID = shd.CORPORATE_BODY_ID "
            + "WHERE cb.INCORPORATION_NUMBER = ?", nativeQuery = true)
    Page<Shareholder> getShareholders(String incorporationNumber, Pageable pageable);


    @Query(value = "SELECT COUNT(*) "
            + "FROM shareholder sh INNER JOIN shareholding shd ON sh.SHAREHOLDING_ID = shd.SHAREHOLDING_ID "
            + "JOIN corporate_body cb on cb.CORPORATE_BODY_ID = shd.CORPORATE_BODY_ID "
            + "WHERE cb.INCORPORATION_NUMBER = ?", nativeQuery = true)
    int getShareholderCount(String incorporationNumber);

}
