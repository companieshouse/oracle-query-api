package uk.gov.ch.repository.update.trusts;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.update.trusts.CorporateTrusteeData;

public interface CorporateTrusteesRepository extends
        PagingAndSortingRepository<CorporateTrusteeData, Long> {
    @Query(value = "SELECT t.trustee_id, "
            + "       t.trustee_type_id, "
            + "       t.appointment_date, "
            + "       t.ceased_date, "
            + "       t.corporate_ind, "
            + "       ctd.trustee_name, "
            + "       ctd.register_location, "
            + "       ctd.registration_number, "
            + "       ctd.law_governed, "
            + "       ctd.legal_form, "
            + "       ctd.country, "
            + "       ctd.on_register_in_country_formed, "
            + "       ctd.service_same_as_roa_ind, "
            + "       tal_oa.appointment_address_type_id, "
            + "       sa.house_name_number AS service_address_house_name_number, "
            + "       sa.street AS service_address_street, "
            + "       sa.area AS service_address_area, "
            + "       sa.post_town AS service_address_post_town, "
            + "       sa.region AS service_address_region, "
            + "       sa.country_name AS service_address_country_name, "
            + "       sa.post_code AS service_address_post_code, "
            + "       oa.house_name_number AS res_office_house_name_number,  "
            + "       oa.street AS res_office_street, "
            + "       oa.area AS res_office_area, "
            + "       oa.post_town AS res_office_post_town, "
            + "       oa.region AS res_office_region, "
            + "       oa.country_name AS res_office_country_name, "
            + "       oa.post_code AS res_office_post_code "
            + "FROM trustee t "
            + "   JOIN corporate_trustee_details ctd "
            + "     ON t.trustee_id = ctd.trustee_id "
            + "   LEFT JOIN trustee_address_link tal_oa "
            + "     ON t.trustee_id = tal_oa.trustee_id AND tal_oa.appointment_address_type_id = '5001' "
            + "   LEFT JOIN address oa "
            + "     ON oa.address_id = tal_oa.address_id "
            + "   LEFT JOIN trustee_address_link tal_sa "
            + "     ON t.trustee_id = tal_sa.trustee_id AND tal_sa.appointment_address_type_id = '5003' "
            + "   LEFT JOIN address sa "
            + "     ON sa.address_id = tal_sa.address_id "
            + "WHERE t.trust_id = ?",
            nativeQuery = true)
    List<CorporateTrusteeData> getCorporateTrustees(String trustId);
}