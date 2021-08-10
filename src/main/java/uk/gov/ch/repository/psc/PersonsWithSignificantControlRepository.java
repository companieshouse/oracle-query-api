package uk.gov.ch.repository.psc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.psc.PersonWithSignificantControl;

public interface PersonsWithSignificantControlRepository extends PagingAndSortingRepository<PersonWithSignificantControl, Long> {

    @Query(value = "select " +
            "CBA.CORPORATE_BODY_APPOINTMENT_ID," +
            "CBA.OFFICER_FORENAME_1, " +
            "CBA.OFFICER_FORENAME_2, " +
            "CBA.OFFICER_SURNAME, " +
            "CBA.APPOINTMENT_TYPE_ID, " +
            "CBA.SERVICE_ADDRESS_LINE_1, " +
            "CBA.SERVICE_ADDRESS_POST_CODE, " +
            "CBA.SERVICE_ADDRESS_POST_TOWN, " +
            "OD.OFFICER_NATIONALITY, " +
            "OD.OFFICER_DATE_OF_BIRTH, " +
            "CAD.PSC_REGISTRATION_NUMBER, " +
            "CAD.PSC_LAW_GOVERNED, " +
            "CAD.PSC_LEGAL_FORM, " +
            "CAD.PSC_COUNTRY, " +
            "case when OD.SECURE_DIRECTOR_SERVICE_IND='Y' then null " +
            "else  URA.HOUSE_NAME_NUMBER end HOUSE_NAME_NUMBER, " +
            "case when OD.SECURE_DIRECTOR_SERVICE_IND='Y' then null " +
            "else  URA.STREET end STREET, " +
            "case when OD.SECURE_DIRECTOR_SERVICE_IND='Y' then null " +
            "else  URA.AREA end  AREA, " +
            "case when OD.SECURE_DIRECTOR_SERVICE_IND='Y' then null " +
            "else  URA.POST_TOWN end POST_TOWN, " +
            "case when OD.SECURE_DIRECTOR_SERVICE_IND='Y' then null " +
            "else  URA.POST_CODE end POST_CODE, " +
            "case when OD.SECURE_DIRECTOR_SERVICE_IND='Y' then null " +
            "else  URA.REGION end REGION, " +
            "case when OD.SECURE_DIRECTOR_SERVICE_IND='Y' then null " +
            "else  URA.COUNTRY_NAME end COUNTRY_NAME, " +
            "case when OD.SECURE_DIRECTOR_SERVICE_IND='Y' then null " +
            "else  URA.SUPPLIED_COMPANY_NAME end  SUPPLIED_COMPANY_NAME, " +
            "case when OD.SECURE_DIRECTOR_SERVICE_IND='Y' then null " +
            "else  URA.PO_BOX end  PO_BOX, " +
            "case when OD.SECURE_DIRECTOR_SERVICE_IND='Y' then null " +
            "else  URA.ADDRESS_LINE_1 end  ADDRESS_LINE_1, " +
            "listagg(noct.DESCRIPTION, +'; ') within group (order by NOC.NATURE_OF_CONTROL_TYPE_ID )    nature_of_control " +
            "from CORPORATE_BODY_APPOINTMENT CBA " +
            "inner join OFFICER_DETAIL OD on OD.OFFICER_DETAIL_ID=CBA.OFFICER_DETAIL_ID " +
            "left outer join  USUAL_RESIDENTIAL_ADDRESS URA on OD.USUAL_RESIDENTIAL_ADDRESS_ID = URA.USUAL_RESIDENTIAL_ADDRESS_ID " +
            "left outer join CORPORATE_BODY_APPT_NOC_LINK noc on NOC.CORPORATE_BODY_APPOINTMENT_ID=CBA.CORPORATE_BODY_APPOINTMENT_ID " +
            "left outer join nature_of_control_type noct on NOCT.NATURE_OF_CONTROL_TYPE_ID=NOC.NATURE_OF_CONTROL_TYPE_ID " +
            "left outer join CORPORATE_APPT_DETAIL CAD on CAD.CORPORATE_APPT_DETAIL_ID=CBA.CORPORATE_APPT_DETAIL_ID " +
            "where " +
            "CBA.CORPORATE_BODY_ID = (select CORPORATE_BODY_ID from CORPORATE_BODY where INCORPORATION_NUMBER = ?) " +
            "AND CBA.RESIGNATION_IND = 'N' " +
            "AND CBA.SUPER_SECURE_PSC_IND = 'N' " +
            "AND CBA.APPOINTMENT_TYPE_ID IN (5007, 5008, 5009) " +
            "group by CBA.CORPORATE_BODY_APPOINTMENT_ID, CBA.OFFICER_FORENAME_1, CBA.OFFICER_FORENAME_2, CBA.OFFICER_SURNAME, CBA.APPOINTMENT_TYPE_ID, CBA.SERVICE_ADDRESS_LINE_1, " +
            "CBA.SERVICE_ADDRESS_POST_CODE, CBA.SERVICE_ADDRESS_POST_TOWN, OD.OFFICER_NATIONALITY, OD.OFFICER_DATE_OF_BIRTH, " +
            "CAD.PSC_REGISTRATION_NUMBER, CAD.PSC_LAW_GOVERNED, CAD.PSC_LEGAL_FORM, CAD.PSC_COUNTRY, " +
            "       case when OD.SECURE_DIRECTOR_SERVICE_IND='Y' then null else  URA.HOUSE_NAME_NUMBER end, " +
            "case when OD.SECURE_DIRECTOR_SERVICE_IND='Y' then null else  URA.STREET end  , " +
            "case when OD.SECURE_DIRECTOR_SERVICE_IND='Y' then null else  URA.AREA end  , " +
            "case when OD.SECURE_DIRECTOR_SERVICE_IND='Y' then null else  URA.POST_TOWN end  , " +
            "case when OD.SECURE_DIRECTOR_SERVICE_IND='Y' then null else  URA.POST_CODE end  , " +
            "case when OD.SECURE_DIRECTOR_SERVICE_IND='Y' then null else  URA.REGION end  , " +
            "case when OD.SECURE_DIRECTOR_SERVICE_IND='Y' then null else  URA.COUNTRY_NAME end  , " +
            "case when OD.SECURE_DIRECTOR_SERVICE_IND='Y' then null else  URA.SUPPLIED_COMPANY_NAME end  , " +
            "case when OD.SECURE_DIRECTOR_SERVICE_IND='Y' then null else  URA.PO_BOX end  , " +
            "case when OD.SECURE_DIRECTOR_SERVICE_IND='Y' then null else  URA.ADDRESS_LINE_1 end", nativeQuery = true)
    Page<PersonWithSignificantControl> findPersonsWithSignificantControl(String companyNumber, Pageable pageable);
}
