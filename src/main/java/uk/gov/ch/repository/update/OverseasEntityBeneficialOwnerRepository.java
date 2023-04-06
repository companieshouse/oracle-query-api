package uk.gov.ch.repository.update;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.update.OverseasEntityBeneficialOwner;

import java.util.List;

public interface OverseasEntityBeneficialOwnerRepository extends PagingAndSortingRepository<OverseasEntityBeneficialOwner, Long> {
    @Query(value = "select "
            + "CBA.CORPORATE_BODY_APPOINTMENT_ID, "
            + "CBA.APPOINTMENT_DATE, "
            + "CBA.URA_SAME_AS_SERVICE_IND, "
            + "URA.STREET as RESIDENTIAL_ADDRESS_LINE_1, "
            + "URA.AREA as RESIDENTIAL_ADDRESS_LINE_2, "
            + "URA.CARE_OF as RESIDENTIAL_ADDRESS_CARE_OF, "
            + "URA.COUNTRY_NAME as RESIDENTIAL_ADDRESS_COUNTRY_NAME, "
            + "URA.POST_TOWN as RESIDENTIAL_ADDRESS_POST_TOWN, "
            + "URA.PO_BOX as RESIDENTIAL_ADDRESS_PO_BOX, "
            + "URA.POST_CODE as RESIDENTIAL_ADDRESS_POST_CODE, "
            + "URA.HOUSE_NAME_NUMBER as RESIDENTIAL_ADDRESS_HOUSE_NAME_NUMBER, "
            + "URA.REGION as RESIDENTIAL_ADDRESS_REGION, "
            + "OD.OFFICER_DATE_OF_BIRTH "
            + "from CORPORATE_BODY_APPOINTMENT CBA "
            + "inner join OFFICER_DETAIL OD on OD.OFFICER_DETAIL_ID=CBA.OFFICER_DETAIL_ID "
            + "left outer join USUAL_RESIDENTIAL_ADDRESS URA on OD.USUAL_RESIDENTIAL_ADDRESS_ID = URA.USUAL_RESIDENTIAL_ADDRESS_ID "
            + "where CBA.CORPORATE_BODY_ID = (select CORPORATE_BODY_ID from CORPORATE_BODY where INCORPORATION_NUMBER = ?)"
            + "and CBA.RESIGNATION_IND = 'N' AND CBA.SUPER_SECURE_PSC_IND = 'N' AND CBA.APPOINTMENT_TYPE_ID IN (5010, 5011, 5012)",
            nativeQuery = true)
    List<OverseasEntityBeneficialOwner> getBeneficialOwners(String incorporationNumber);
}
