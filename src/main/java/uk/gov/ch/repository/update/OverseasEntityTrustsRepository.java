package uk.gov.ch.repository.update;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.update.OverseasEntityTrust;

import java.util.List;

public interface OverseasEntityTrustsRepository extends PagingAndSortingRepository<OverseasEntityTrust, Long> {
    @Query(value = "select trust_id, trust_name, creation_date, unable_to_obtain_all_info_ind "
            + "from trust where trust_id in "
            + "(select trust_id from corbod_appt_trust_link where corporate_body_appointment_id in "
            + "(select corporate_body_appointment_id from corporate_body_appointment ca "
            + "join corporate_body cb on cb.corporate_body_id = ca.corporate_body_id "
            + "where cb.incorporation_number = ?))",
            nativeQuery = true)
    List<OverseasEntityTrust> getTrusts(String companyNumber);
}
