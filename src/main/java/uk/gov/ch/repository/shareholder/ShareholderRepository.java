package uk.gov.ch.repository.shareholder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.shareholder.Shareholder;

public interface ShareholderRepository extends PagingAndSortingRepository<Shareholder, Long> {

    @Query(value = "SELECT "
            + "sh.SHAREHOLDER_ID, "
            + "sh.SHAREHOLDER_FORENAME_1 AS fore_name_1, "
            + "sh.SHAREHOLDER_FORENAME_2 AS fore_name_2, "
            + "sh.SHAREHOLDER_SURNAME AS surname, "
            + "shd.NUMBER_OF_SHARES AS shares, "
            + "coalesce(shd.NON_STANDARD_SHARE_CLASS, sct.SHARE_CLASS_TYPE_DESC) AS class_of_shares, "
            + "ctp.CURRENCY_TYPE_CODE as currency "
            + "FROM shareholder sh INNER JOIN shareholding shd ON sh.SHAREHOLDING_ID = shd.SHAREHOLDING_ID "
            + "JOIN corporate_body cb ON cb.CORPORATE_BODY_ID = shd.CORPORATE_BODY_ID "
            + "LEFT JOIN currency_type ctp ON shd.currency_type_id = ctp.currency_type_id "
            + "LEFT JOIN share_class_type sct on sct.SHARE_CLASS_TYPE_ID = shd.SHARE_CLASS_TYPE_ID "
            + "WHERE cb.INCORPORATION_NUMBER = ?1 "
            + "AND shd.NUMBER_OF_SHARES > 0 "
            + "AND shd.NUMBER_OF_SHARES IS NOT NULL ",
            countQuery =
                    "select count(*) FROM shareholder sh, shareholding shd, corporate_body cb, currency_type ctp, share_class_type sct "
                            + "WHERE sh.SHAREHOLDING_ID = shd.SHAREHOLDING_ID "
                            + "AND cb.CORPORATE_BODY_ID = shd.CORPORATE_BODY_ID "
                            + "AND shd.currency_type_id = ctp.currency_type_id "
                            + "AND shd.NUMBER_OF_SHARES > 0 "
                            + "AND shd.NUMBER_OF_SHARES IS NOT NULL "
                            + "AND sct.SHARE_CLASS_TYPE_ID = shd.SHARE_CLASS_TYPE_ID "
                            + "AND cb.INCORPORATION_NUMBER = ?1",
            nativeQuery = true)
    Page<Shareholder> getShareholders(String incorporationNumber, Pageable pageable);

    @Query(value = "SELECT COUNT(*) "
            + "FROM shareholder sh INNER JOIN shareholding shd ON sh.SHAREHOLDING_ID = shd.SHAREHOLDING_ID "
            + "JOIN corporate_body cb on cb.CORPORATE_BODY_ID = shd.CORPORATE_BODY_ID "
            + "WHERE cb.INCORPORATION_NUMBER = ? "
            + "AND shd.NUMBER_OF_SHARES > 0 "
            + "AND shd.NUMBER_OF_SHARES IS NOT NULL"
            , nativeQuery = true)
    int getShareholderCount(String incorporationNumber);

}
