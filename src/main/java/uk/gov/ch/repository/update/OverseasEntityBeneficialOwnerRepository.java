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

    @Query(value = "select "
            + "CBA.CORPORATE_BODY_APPOINTMENT_ID, "
            + "CBA.APPOINTMENT_DATE, "
            + "CBA.URA_SAME_AS_SERVICE_IND, "
            + "URA.STREET as RES_ADDR_LINE_1, "
            + "URA.AREA as RES_ADDR_LINE_2, "
            + "URA.CARE_OF as RES_ADDR_CARE_OF, "
            + "URA.COUNTRY_NAME as RES_ADDR_COUNTRY_NAME, "
            + "URA.POST_TOWN as RES_ADDR_POST_TOWN, "
            + "URA.PO_BOX as RES_ADDR_PO_BOX, "
            + "URA.POST_CODE as RES_ADDR_POST_CODE, "
            + "URA.HOUSE_NAME_NUMBER as RES_ADDR_HOUSE_NAME_NUMBER, "
            + "URA.REGION as RES_ADDR_REGION, "
            + "AD.STREET as PRINC_ADDR_LINE_1, "
            + "AD.AREA as PRINC_ADDR_LINE_2, "
            + "AD.CARE_OF as PRINC_ADDR_CARE_OF, "
            + "AD.COUNTRY_NAME as PRINC_ADDR_COUNTRY_NAME, "
            + "AD.POST_TOWN as PRINC_ADDR_POST_TOWN, "
            + "AD.PO_BOX as PRINC_ADDR_PO_BOX, "
            + "AD.POST_CODE as PRINC_ADDR_POST_CODE, "
            + "AD.HOUSE_NAME_NUMBER as PRINC_ADDR_HOUSE_NAME_NUMBER, "
            + "AD.REGION as PRINC_ADDR_REGION, "
            + "OD.OFFICER_DATE_OF_BIRTH "
            + "from CORPORATE_BODY_APPOINTMENT CBA "
            + "inner join OFFICER_DETAIL OD on OD.OFFICER_DETAIL_ID=CBA.OFFICER_DETAIL_ID "
            + "left join USUAL_RESIDENTIAL_ADDRESS URA on OD.USUAL_RESIDENTIAL_ADDRESS_ID = URA.USUAL_RESIDENTIAL_ADDRESS_ID "
            + "left join CORBOD_APPT_OTHER_ADDR_LINK CBAOA on CBA.CORPORATE_BODY_APPOINTMENT_ID = CBAOA.CORPORATE_BODY_APPOINTMENT_ID "
            + "left join ADDRESS AD on CBAOA.ADDRESS_ID=AD.ADDRESS_ID "
            + "where CBA.CORPORATE_BODY_ID = (select CORPORATE_BODY_ID from CORPORATE_BODY where INCORPORATION_NUMBER = ?)"
            + "and CBA.RESIGNATION_IND = 'N' "
            + "and CBA.SUPER_SECURE_PSC_IND = 'N' "
            + "and CBA.APPOINTMENT_TYPE_ID IN ("
            + OE_INDIVIDUAL_BO_APPOINTMENT_ID + ", "
            + OE_OLE_BO_APPOINTMENT_ID + ", "
            + OE_GPA_BO_APPOINTMENT_ID + ")",
            nativeQuery = true)
    List<OverseasEntityBeneficialOwner> getBeneficialOwners(String companyNumber);
}
