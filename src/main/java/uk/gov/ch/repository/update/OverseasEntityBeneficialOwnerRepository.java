package uk.gov.ch.repository.update;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.update.OverseasEntityBeneficialOwner;

public interface OverseasEntityBeneficialOwnerRepository extends
        PagingAndSortingRepository<OverseasEntityBeneficialOwner, Long> {

    String OE_INDIVIDUAL_BO_APPOINTMENT_ID = "5010";
    String OE_OLE_BO_APPOINTMENT_ID = "5011";
    String OE_GPA_BO_APPOINTMENT_ID = "5012";

    @Query(value = "SELECT DISTINCT "
            + "CBA.CORPORATE_BODY_APPOINTMENT_ID, "
            + "CBA.APPOINTMENT_DATE, "
            + "CBA.URA_SAME_AS_SERVICE_IND, "
            + "URA.STREET AS RES_ADDR_LINE_1, "
            + "URA.AREA AS RES_ADDR_LINE_2, "
            + "URA.CARE_OF AS RES_ADDR_CARE_OF, "
            + "URA.COUNTRY_NAME AS RES_ADDR_COUNTRY_NAME, "
            + "URA.POST_TOWN AS RES_ADDR_POST_TOWN, "
            + "URA.PO_BOX AS RES_ADDR_PO_BOX, "
            + "URA.POST_CODE AS RES_ADDR_POST_CODE, "
            + "URA.HOUSE_NAME_NUMBER AS RES_ADDR_HOUSE_NAME_NUMBER, "
            + "URA.REGION AS RES_ADDR_REGION, "
            + "AD.STREET AS PRINC_ADDR_LINE_1, "
            + "AD.AREA AS PRINC_ADDR_LINE_2, "
            + "AD.CARE_OF AS PRINC_ADDR_CARE_OF, "
            + "AD.COUNTRY_NAME AS PRINC_ADDR_COUNTRY_NAME, "
            + "AD.POST_TOWN AS PRINC_ADDR_POST_TOWN, "
            + "AD.PO_BOX AS PRINC_ADDR_PO_BOX, "
            + "AD.POST_CODE AS PRINC_ADDR_POST_CODE, "
            + "AD.HOUSE_NAME_NUMBER AS PRINC_ADDR_HOUSE_NAME_NUMBER, "
            + "AD.REGION AS PRINC_ADDR_REGION, "
            + "OD.OFFICER_DATE_OF_BIRTH "
            + "FROM CORPORATE_BODY_APPOINTMENT CBA "
            + "INNER JOIN OFFICER_DETAIL OD ON OD.OFFICER_DETAIL_ID = CBA.OFFICER_DETAIL_ID "
            + "LEFT JOIN ( "
            + "    SELECT OD.USUAL_RESIDENTIAL_ADDRESS_ID, MAX(URA2.USUAL_RESIDENTIAL_ADDRESS_ID) AS MAX_URA_ID "
            + "    FROM OFFICER_DETAIL OD "
            + "    JOIN USUAL_RESIDENTIAL_ADDRESS URA2 ON URA2.USUAL_RESIDENTIAL_ADDRESS_ID = OD.USUAL_RESIDENTIAL_ADDRESS_ID "
            + "    GROUP BY OD.USUAL_RESIDENTIAL_ADDRESS_ID "
            + ") URA_MAX ON URA_MAX.USUAL_RESIDENTIAL_ADDRESS_ID = OD.USUAL_RESIDENTIAL_ADDRESS_ID "
            + "LEFT JOIN USUAL_RESIDENTIAL_ADDRESS URA ON URA.USUAL_RESIDENTIAL_ADDRESS_ID = URA_MAX.MAX_URA_ID "
            + "LEFT JOIN ( "
            + "    SELECT CBAOA2.CORPORATE_BODY_APPOINTMENT_ID, MAX(A2.ADDRESS_ID) AS MAX_ADDR_ID "
            + "    FROM CORBOD_APPT_OTHER_ADDR_LINK CBAOA2 "
            + "    JOIN ADDRESS A2 ON CBAOA2.ADDRESS_ID = A2.ADDRESS_ID "
            + "    GROUP BY CBAOA2.CORPORATE_BODY_APPOINTMENT_ID "
            + ") ADDR_MAX ON ADDR_MAX.CORPORATE_BODY_APPOINTMENT_ID = CBA.CORPORATE_BODY_APPOINTMENT_ID "
            + "LEFT JOIN ADDRESS AD ON AD.ADDRESS_ID = ADDR_MAX.MAX_ADDR_ID "
            + "WHERE CBA.CORPORATE_BODY_ID = ( "
            + "    SELECT CORPORATE_BODY_ID FROM CORPORATE_BODY WHERE INCORPORATION_NUMBER = ? "
            + ") "
            + "AND CBA.RESIGNATION_IND = 'N' "
            + "AND CBA.SUPER_SECURE_PSC_IND = 'N' "
            + "AND CBA.APPOINTMENT_TYPE_ID IN ("
            + OE_INDIVIDUAL_BO_APPOINTMENT_ID + ", "
            + OE_OLE_BO_APPOINTMENT_ID + ", "
            + OE_GPA_BO_APPOINTMENT_ID + ")",
            nativeQuery = true)
    List<OverseasEntityBeneficialOwner> getBeneficialOwners(String companyNumber);
}
