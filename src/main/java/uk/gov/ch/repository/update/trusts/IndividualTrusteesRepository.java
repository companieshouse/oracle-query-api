package uk.gov.ch.repository.update.trusts;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.update.trusts.IndividualTrusteeData;

import java.util.List;

public interface IndividualTrusteesRepository extends PagingAndSortingRepository<IndividualTrusteeData, Long> {
    @Query(value = "select itd.trustee_id, itd.trustee_forename_1, itd.trustee_forename_2, "
            + "itd.trustee_surname, itd.date_of_birth, itd.nationality, "
            + "t.corporate_ind, t.trustee_type_id, t.appointment_date, t.ceased_date "
            + "from trustee t "
            + "join individual_trustee_details itd on t.trustee_id = itd.trustee_id "
            + "where t.trust_id = ?",
            nativeQuery = true)
    List<IndividualTrusteeData> getIndividualTrustees(String trustId);
}
