package uk.gov.ch.repository.update.trusts;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.update.trusts.TrustDetails;

import java.util.List;

public interface TrustDetailsRepository extends PagingAndSortingRepository<TrustDetails, Long> {
    @Query(value = "select trust_id, trust_name, creation_date, ceased_date, unable_to_obtain_all_info_ind "
            + "from trust where trust_id in "
            + "(select trust_id from corbod_appt_trust_link where corporate_body_appointment_id in "
            + "(select corporate_body_appointment_id from corporate_body_appointment ca "
            + "join corporate_body cb on cb.corporate_body_id = ca.corporate_body_id "
            + "where cb.incorporation_number = ?))",
            nativeQuery = true)
    List<TrustDetails> getTrustDetails(String companyNumber);
}
