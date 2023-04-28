package uk.gov.ch.repository.update;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import uk.gov.ch.model.update.OverseasEntityManagingOfficerData;


public interface OverseasEntityManagingOfficersRepository extends PagingAndSortingRepository<OverseasEntityManagingOfficerData, Long> {
    static final String OVERSEAS_ENTITY_MANAGING_OFFICERS_TYPE_ID = "5013";
    @Query(value = "SELECT cba.officer_id AS MO_ID, " +
        "od.officer_date_of_birth AS DOB, " +
        "uadd.house_name_number AS RESIDENTIAL_ADDRESS_HOUSE_NAME_NUMBER, " +
        "uadd.street AS RESIDENTIAL_ADDRESS_LINE_1, " +
        "uadd.area AS RESIDENTIAL_ADDRESS_LINE_2, " +
        "uadd.post_town AS RESIDENTIAL_ADDRESS_POST_TOWN, " +
        "uadd.region AS RESIDENTIAL_ADDRESS_REGION, " +
        "uadd.country_name AS RESIDENTIAL_ADDRESS_COUNTRY_NAME, " +
        "uadd.post_code AS RESIDENTIAL_ADDRESS_POST_CODE, " +
        "uadd.po_box AS RESIDENTIAL_ADDRESS_PO_BOX, " +
        "uadd.care_of AS RESIDENTIAL_ADDRESS_CARE_OF, " +
        "addr.house_name_number AS PRINCIPAL_ADDRESS_HOUSE_NAME_NUMBER, " +
        "addr.street AS PRINCIPAL_ADDRESS_LINE_1, " +
        "addr.area AS PRINCIPAL_ADDRESS_LINE_2, " +
        "addr.post_town AS PRINCIPAL_ADDRESS_POST_TOWN, " +
        "addr.region AS PRINCIPAL_ADDRESS_REGION, " +
        "addr.country_name AS PRINCIPAL_ADDRESS_COUNTRY_NAME, " +
        "addr.post_code AS PRINCIPAL_ADDRESS_POST_CODE, " +
        "addr.po_box AS PRINCIPAL_ADDRESS_PO_BOX, " +
        "addr.care_of AS PRINCIPAL_ADDRESS_CARE_OF, " +
        "caad.contact_name AS CONTACT_NAME_FULL, " +
        "caad.contact_email AS CONTACT_EMAIL_ADDRESS " +
        "FROM corporate_body_appointment cba " +
        "INNER JOIN officer_detail od ON cba.officer_detail_id = od.officer_detail_id " +
        "LEFT JOIN usual_residential_address uadd ON od.usual_residential_address_id = uadd.usual_residential_address_id "
        +
        "LEFT JOIN corbod_appt_other_addr_link cbaoa ON cba.corporate_body_appointment_id = cbaoa.corporate_body_appointment_id "
        +
        "LEFT JOIN address addr ON cbaoa.address_id = addr.address_id " +
        "LEFT JOIN corbod_appt_associated_data caad ON cba.corporate_body_appointment_id = caad.corporate_body_appointment_id "
        +
        "WHERE CBA.CORPORATE_BODY_ID = (select CORPORATE_BODY_ID from CORPORATE_BODY where INCORPORATION_NUMBER = ?) "
        +
        "AND CBA.RESIGNATION_IND = 'N' AND CBA.SUPER_SECURE_PSC_IND = 'N' AND CBA.APPOINTMENT_TYPE_ID IN (" + OVERSEAS_ENTITY_MANAGING_OFFICERS_TYPE_ID
        + ")", nativeQuery = true)

    List<OverseasEntityManagingOfficerData> getOverseasEntityManagingOfficers(String companyNumber);
}
