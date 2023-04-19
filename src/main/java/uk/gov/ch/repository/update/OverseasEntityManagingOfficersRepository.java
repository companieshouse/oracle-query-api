package uk.gov.ch.repository.update;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import uk.gov.ch.model.update.OverseasEntityManagingOfficerData;


public interface OverseasEntityManagingOfficersRepository extends PagingAndSortingRepository<OverseasEntityManagingOfficerData, Long> {
    static final String OVERSEAS_ENTITY_APPOINTMENT_TYPE_ID = "5013";
    @Query(value = "SELECT cba.officer_id AS MO_ID, " +
        "od.officer_date_of_birth AS DOB, " +
        "uadd.house_name_number AS premises, " +
        "uadd.street AS address_line_1, " +
        "uadd.area AS address_line_2, " +
        "uadd.post_town AS locality, " +
        "uadd.region AS region, " +
        "uadd.country_name AS country_name, " +
        "uadd.post_code AS postal_code, " +
        "uadd.po_box AS po_box, " +
        "uadd.care_of AS care_of, " +
        "caad.contact_name AS CONTACT_NAME_FULL, " +
        "caad.contact_email AS CONTACT_EMAIL_ADDRESS " +
        "FROM corporate_body_appointment cba " +
        "inner join corporate_body cb ON cba.corporate_body_id = cb.corporate_body_id " +
        "inner join officer_detail od ON cba.officer_detail_id = od.officer_detail_id " +
        "inner join usual_residential_address uadd ON od.usual_residential_address_id = uadd.usual_residential_address_id "
        +
        "inner join appointment_type at ON cba.appointment_type_id = at.appointment_type_id " +
        "left outer join corbod_appt_associated_data caad ON cba.corporate_body_appointment_id = caad.corporate_body_appointment_id "
        +
        "WHERE CBA.CORPORATE_BODY_ID = (select CORPORATE_BODY_ID from CORPORATE_BODY where INCORPORATION_NUMBER = ?) "
        +
        "AND CBA.RESIGNATION_IND = 'N' AND CBA.SUPER_SECURE_PSC_IND = 'N' AND CBA.APPOINTMENT_TYPE_ID IN ("+OVERSEAS_ENTITY_APPOINTMENT_TYPE_ID+")", nativeQuery = true)
    List<OverseasEntityManagingOfficerData> getOverseasEntityManagingOfficers(String companyNumber);
}
