package uk.gov.ch.repository.update;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.update.OverseasEntityManagingOfficerData;


public interface OverseasEntityManagingOfficersRepository extends
        PagingAndSortingRepository<OverseasEntityManagingOfficerData, Long> {

    String OVERSEAS_ENTITY_MANAGING_OFFICERS_TYPE_ID = "5013";

    @Query(value = "SELECT DISTINCT cba.corporate_body_appointment_id AS MO_APPOINTMENT_ID, " +
            "od.officer_date_of_birth AS DOB, " +
            "uadd.house_name_number AS RES_ADDR_HOUSE_NAME_NUMBER, " +
            "uadd.street AS RES_ADDR_LINE_1, " +
            "uadd.area AS RES_ADDR_LINE_2, " +
            "uadd.post_town AS RES_ADDR_POST_TOWN, " +
            "uadd.region AS RES_ADDR_REGION, " +
            "uadd.country_name AS RES_ADDR_COUNTRY_NAME, " +
            "uadd.post_code AS RES_ADDR_POST_CODE, " +
            "uadd.po_box AS RES_ADDR_PO_BOX, " +
            "uadd.care_of AS RES_ADDR_CARE_OF, " +
            "addr.house_name_number AS PRINC_ADDR_HOUSE_NAME_NUMBER, " +
            "addr.street AS PRINC_ADDR_LINE_1, " +
            "addr.area AS PRINC_ADDR_LINE_2, " +
            "addr.post_town AS PRINC_ADDR_POST_TOWN, " +
            "addr.region AS PRINC_ADDR_REGION, " +
            "addr.country_name AS PRINC_ADDR_COUNTRY_NAME, " +
            "addr.post_code AS PRINC_ADDR_POST_CODE, " +
            "addr.po_box AS PRINC_ADDR_PO_BOX, " +
            "addr.care_of AS PRINC_ADDR_CARE_OF, " +
            "caad.contact_name AS CONTACT_NAME_FULL, " +
            "caad.contact_email AS CONTACT_EMAIL_ADDRESS " +
            "FROM corporate_body_appointment cba " +
            "INNER JOIN officer_detail od ON cba.officer_detail_id = od.officer_detail_id "
            + "LEFT JOIN ( "
            + "    SELECT OD.USUAL_RESIDENTIAL_ADDRESS_ID, MAX(URA.USUAL_RESIDENTIAL_ADDRESS_ID) AS MAX_URA_ID "
            + "    FROM OFFICER_DETAIL OD "
            + "    JOIN USUAL_RESIDENTIAL_ADDRESS URA ON URA.USUAL_RESIDENTIAL_ADDRESS_ID = OD.USUAL_RESIDENTIAL_ADDRESS_ID "
            + "    GROUP BY OD.USUAL_RESIDENTIAL_ADDRESS_ID "
            + ") URA_MAX ON URA_MAX.USUAL_RESIDENTIAL_ADDRESS_ID = OD.USUAL_RESIDENTIAL_ADDRESS_ID "
            + "LEFT JOIN USUAL_RESIDENTIAL_ADDRESS UADD ON UADD.USUAL_RESIDENTIAL_ADDRESS_ID = URA_MAX.MAX_URA_ID "
            + "LEFT JOIN corbod_appt_other_addr_link cbaoa ON cba.corporate_body_appointment_id = cbaoa.corporate_body_appointment_id "
            + "LEFT JOIN ( "
            + "    SELECT CBAOA.CORPORATE_BODY_APPOINTMENT_ID, MAX(A.ADDRESS_ID) AS MAX_ADDR_ID "
            + "    FROM CORBOD_APPT_OTHER_ADDR_LINK CBAOA "
            + "    JOIN ADDRESS A ON CBAOA.ADDRESS_ID = A.ADDRESS_ID "
            + "    GROUP BY CBAOA.CORPORATE_BODY_APPOINTMENT_ID "
            + ") ADDR_MAX ON ADDR_MAX.CORPORATE_BODY_APPOINTMENT_ID = CBA.CORPORATE_BODY_APPOINTMENT_ID "
            + "LEFT JOIN ADDRESS ADDR ON ADDR.ADDRESS_ID = ADDR_MAX.MAX_ADDR_ID "
            + "LEFT JOIN corbod_appt_associated_data caad ON cba.corporate_body_appointment_id = caad.corporate_body_appointment_id "
            + "WHERE CBA.CORPORATE_BODY_ID = (select CORPORATE_BODY_ID from CORPORATE_BODY where INCORPORATION_NUMBER = ?) "
            + "AND CBA.RESIGNATION_IND = 'N' AND CBA.SUPER_SECURE_PSC_IND = 'N' AND CBA.APPOINTMENT_TYPE_ID IN ("
            + OVERSEAS_ENTITY_MANAGING_OFFICERS_TYPE_ID
            + ")", nativeQuery = true)
    List<OverseasEntityManagingOfficerData> getOverseasEntityManagingOfficers(String companyNumber);
}
