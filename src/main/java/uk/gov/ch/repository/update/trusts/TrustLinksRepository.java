package uk.gov.ch.repository.update.trusts;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.update.trusts.TrustLinkData;

public interface TrustLinksRepository extends
        PagingAndSortingRepository<TrustLinkData, Long> {

    @Query(value =
              "SELECT ROWNUM, t.trust_id, "
                + "ca.corporate_body_appointment_id "
            + "FROM corporate_body cb "
              + "join corporate_body_appointment ca "
                + "ON ca.corporate_body_id = cb.corporate_body_id "
              + "join corbod_appt_trust_link cat "
                + "ON ca.corporate_body_appointment_id = cat.corporate_body_appointment_id "
              + "join trust t "
                + "ON t.trust_id = cat.trust_id "
            + "WHERE  cb.incorporation_number = ? ",
            nativeQuery = true)
    List<TrustLinkData> getTrustLinks(String oeNumber);
}
