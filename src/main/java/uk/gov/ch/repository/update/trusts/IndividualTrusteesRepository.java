package uk.gov.ch.repository.update.trusts;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.update.trusts.IndividualTrusteeData;

public interface IndividualTrusteesRepository extends
        PagingAndSortingRepository<IndividualTrusteeData, Long> {

    @Query(value = "SELECT t.trustee_id,"
            + "       t.trustee_type_id,"
            + "       t.appointment_date,"
            + "       t.ceased_date,"
            + "       t.corporate_ind,"
            + "       itd.trustee_forename_1,"
            + "       itd.trustee_forename_2,"
            + "       itd.trustee_surname,"
            + "       itd.date_of_birth,"
            + "       itd.nationality,"
            + "       itd.ura_same_as_service_ind,"
            + "       sa.house_name_number AS  serv_addr_name_number,"
            + "       sa.street AS  serv_addr_street,"
            + "       sa.area AS  serv_addr_area,"
            + "       sa.post_town AS  serv_addr_post_town,"
            + "       sa.region AS  serv_addr_region,"
            + "       sa.country_name AS  serv_addr_country_name,"
            + "       sa.post_code AS  serv_addr_post_code,"
            + "       ura.house_name_number AS res_addr_name_number,"
            + "       ura.street AS res_addr_street,"
            + "       ura.area AS res_addr_area,"
            + "       ura.post_town AS res_addr_post_town,"
            + "       ura.region AS res_addr_region,"
            + "       ura.country_name AS res_addr_country_name,"
            + "       ura.post_code AS res_addr_post_code "
            + "FROM trustee t"
            + "   JOIN individual_trustee_details itd"
            + "     ON t.trustee_id = itd.trustee_id"
            + "   LEFT JOIN trustee_address_link tal_ura"
            + "     ON t.trustee_id = tal_ura.trustee_id AND tal_ura.appointment_address_type_id = '5002'"
            + "   LEFT JOIN address ura"
            + "     ON ura.address_id = tal_ura.address_id"
            + "   LEFT JOIN trustee_address_link tal_sa"
            + "     ON t.trustee_id = tal_sa.trustee_id AND tal_sa.appointment_address_type_id = '5003'"
            + "   LEFT JOIN address sa"
            + "     ON sa.address_id = tal_sa.address_id "
            + "WHERE t.trust_id = ?",
            nativeQuery = true)
    List<IndividualTrusteeData> getIndividualTrustees(String trustId);
}
