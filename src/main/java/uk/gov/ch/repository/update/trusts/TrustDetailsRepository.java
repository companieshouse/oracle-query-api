package uk.gov.ch.repository.update.trusts;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.update.trusts.TrustDetails;

import java.util.List;

public interface TrustDetailsRepository extends PagingAndSortingRepository<TrustDetails, Long> {
    @Query(value = "select distinct t.trust_id, t.trust_name, t.creation_date, t.ceased_date, t.unable_to_obtain_all_info_ind "
            + "from corporate_body cb "
            + "join corporate_body_appointment ca on ca.corporate_body_id = cb.corporate_body_id "
            + "join corbod_appt_trust_link cat on ca.corporate_body_appointment_id = cat.corporate_body_appointment_id "
            + "join trust t on t.trust_id = cat.trust_id "
            + "where cb.incorporation_number = ?",
            nativeQuery = true)
    List<TrustDetails> getTrustDetails(String companyNumber);
}
