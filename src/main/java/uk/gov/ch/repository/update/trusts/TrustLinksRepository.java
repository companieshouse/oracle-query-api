package uk.gov.ch.repository.update.trusts;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.update.trusts.TrustLinkData;

public interface TrustLinksRepository extends
        PagingAndSortingRepository<TrustLinkData, Long> {

    @Query(value =
              "SELECT trust_id, corporate_body_appointment_id "
            + "FROM corbod_appt_trust_link where corporate_body_appointment_id "
                      + "IN (SELECT corporate_body_appointment_id from corporate_body_appointment ca "
                          + "JOIN corporate_body cb on cb.corporate_body_id = ca.corporate_body_id "
                          + "WHERE cb.incorporation_number = '?')",
            nativeQuery = true)
    List<TrustLinkData> getTrustLinks(String oeNumber);
}
