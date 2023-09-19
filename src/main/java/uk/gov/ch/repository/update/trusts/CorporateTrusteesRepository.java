package uk.gov.ch.repository.update.trusts;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.update.trusts.CorporateTrusteeData;

public interface CorporateTrusteesRepository extends
        PagingAndSortingRepository<CorporateTrusteeData, Long> {
    @Query(value = "SELECT ctd.trustee_id, ctd.trustee_name, "
            + "ctd.on_register_in_country_formed, ctd.register_location, ctd.registration_number, "
            + "ctd.law_governed, ctd.legal_form, ctd.country, "
            + "t.corporate_ind, t.trustee_type_id, "
            + "t.appointment_date, t.ceased_date"
            + "FROM trustee t"
            + "JOIN corporate_trustee_details ctd "
            + "ON t.trustee_id = ctd.trustee_id "
            + "WHERE t.trust_id = ?",
            nativeQuery = true)
    List<CorporateTrusteeData> getCorporateTrustees(String trustId);
}