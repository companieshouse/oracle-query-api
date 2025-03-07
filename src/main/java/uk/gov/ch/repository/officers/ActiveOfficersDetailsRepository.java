package uk.gov.ch.repository.officers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.officer.active.ActiveOfficerDetails;

public interface ActiveOfficersDetailsRepository extends
        PagingAndSortingRepository<ActiveOfficerDetails, Long> {


    @Query(value = "SELECT "
            + "cba.officer_detail_id, "
            + "cba.officer_forename_1 AS fore_name_1, "
            + "cba.officer_forename_2 AS fore_name_2, "
            + "cba.officer_surname AS surname, "
            + "cba.occupation_desc AS occupation, "
            + "apt.appointment_type_desc as role, "
            + "case when ofc.CORPORATE_OFFICER_IND='Y' then 1 else 0 end corporate, "
            + "case when cad.NON_EEA_REGISTER_LOCATION is not null then cad.NON_EEA_REGISTER_LOCATION else cad.EEA_REGISTER_LOCATION end place_registered, "
            + "case when cad.NON_EEA_REGISTRATION_NUMBER is not null then cad.NON_EEA_REGISTRATION_NUMBER else cad.EEA_REGISTRATION_NUMBER end registration_number, "
            + "cad.NON_EEA_LAW_GOVERNED as law_governed, "
            + "cad.NON_EEA_LEGAL_FORM as legal_form, "
            + "cad.CORPORATE_APPT_DETAIL_CODE as identification_type, "
            + "cba.APPOINTMENT_DATE AS date_of_appointment, "
            + "od.officer_nationality AS nationality, "
            + "od.officer_date_of_birth AS date_of_birth, "
            + "od.usual_residential_country AS country_of_residence, "
            + "adr.HOUSE_NAME_NUMBER as SERVICE_ADDRESS_line_1, "
            + "adr.STREET as SERVICE_ADDRESS_line_2, "
            + "adr.CARE_OF as SERVICE_ADDRESS_CARE_OF, "
            + "adr.COUNTRY_NAME as SERVICE_ADDRESS_country, "
            + "adr.POST_TOWN as SERVICE_ADDRESS_locality, "
            + "adr.PO_BOX as SERVICE_ADDRESS_PO_BOX, "
            + "adr.POST_CODE as SERVICE_ADDRESS_POST_CODE, "
            + "adr.REGION as SERVICE_ADDRESS_REGION, "
            + "CASE WHEN od.SECURE_DIRECTOR_SERVICE_IND = 'Y' THEN NULL ELSE ura.HOUSE_NAME_NUMBER end RESIDENTIAL_ADDRESS_LINE_1, "
            + "CASE WHEN od.SECURE_DIRECTOR_SERVICE_IND = 'Y' THEN NULL ELSE ura.STREET end RESIDENTIAL_ADDRESS_LINE_2, "
            + "CASE WHEN od.SECURE_DIRECTOR_SERVICE_IND = 'Y' THEN NULL ELSE ura.CARE_OF end RESIDENTIAL_ADDRESS_CARE_OF, "
            + "CASE WHEN od.SECURE_DIRECTOR_SERVICE_IND = 'Y' THEN NULL ELSE ura.COUNTRY_NAME end RESIDENTIAL_ADDRESS_COUNTRY, "
            + "CASE WHEN od.SECURE_DIRECTOR_SERVICE_IND = 'Y' THEN NULL ELSE ura.POST_TOWN end RESIDENTIAL_ADDRESS_LOCALITY, "
            + "CASE WHEN od.SECURE_DIRECTOR_SERVICE_IND = 'Y' THEN NULL ELSE ura.PO_BOX end RESIDENTIAL_ADDRESS_PO_BOX, "
            + "CASE WHEN od.SECURE_DIRECTOR_SERVICE_IND = 'Y' THEN NULL ELSE ura.POST_CODE end RESIDENTIAL_ADDRESS_POST_CODE, "
            + "CASE WHEN od.SECURE_DIRECTOR_SERVICE_IND = 'Y' THEN NULL ELSE ura.REGION end RESIDENTIAL_ADDRESS_REGION, "
            + "od.secure_director_service_ind AS secure_indicator "
            + "FROM CORPORATE_BODY_APPOINTMENT CBA "
            + "inner join OFFICER_DETAIL OD on OD.OFFICER_DETAIL_ID=CBA.OFFICER_DETAIL_ID "
            + "left outer join  USUAL_RESIDENTIAL_ADDRESS URA on OD.USUAL_RESIDENTIAL_ADDRESS_ID = URA.USUAL_RESIDENTIAL_ADDRESS_ID "
            + "left outer join  ADDRESS ADR on CBA.SERVICE_ADDRESS_ID = ADR.ADDRESS_ID "
            + "left outer join  officer ofc on ofc.officer_id= cba.officer_id "
            + "left outer join  CORPORATE_APPT_DETAIL CAD on CAD.CORPORATE_APPT_DETAIL_ID=CBA.CORPORATE_APPT_DETAIL_ID "
            + "left outer join  appointment_type apt on apt.appointment_type_id = cba.appointment_type_id "
            + "WHERE CBA.CORPORATE_BODY_ID = (select CORPORATE_BODY_ID from CORPORATE_BODY where INCORPORATION_NUMBER = ?) "
            + "AND cba.resignation_ind = 'N' AND cba.appointment_type_id in (1, 2) "
            + "group by "
            + "cba.officer_detail_id, "
            + "cba.officer_forename_1, "
            + "cba.officer_forename_2, "
            + "cba.officer_surname, "
            + "cba.occupation_desc, "
            + "apt.appointment_type_desc, "
            + "ofc.corporate_officer_ind, "
            + "cad.NON_EEA_REGISTER_LOCATION, "
            + "cad.EEA_REGISTER_LOCATION, "
            + "cad.NON_EEA_REGISTRATION_NUMBER, "
            + "cad.EEA_REGISTRATION_NUMBER, "
            + "cad.NON_EEA_LAW_GOVERNED, "
            + "cad.NON_EEA_LEGAL_FORM, "
            + "cad.CORPORATE_APPT_DETAIL_CODE, "
            + "cba.APPOINTMENT_DATE, "
            + "od.officer_nationality, "
            + "od.officer_date_of_birth, "
            + "od.usual_residential_country, "
            + "adr.HOUSE_NAME_NUMBER, "
            + "adr.STREET, "
            + "adr.CARE_OF, "
            + "adr.COUNTRY_NAME, "
            + "adr.POST_TOWN, "
            + "adr.PO_BOX, "
            + "adr.POST_CODE, "
            + "adr.REGION, "
            + "ura.HOUSE_NAME_NUMBER, "
            + "ura.STREET, "
            + "ura.CARE_OF, "
            + "ura.COUNTRY_NAME, "
            + "ura.POST_TOWN, "
            + "ura.PO_BOX, "
            + "ura.POST_CODE, "
            + "ura.REGION, "
            + "od.secure_director_service_ind",
            countQuery = "select count(*) FROM corporate_body cb, corporate_body_appointment cba "
                    + "WHERE cba.corporate_body_id=cb.corporate_body_id AND cb.incorporation_number = ?1 AND cba.resignation_ind = 'N'",
            nativeQuery = true)
    Page<ActiveOfficerDetails> getActiveOfficersDetails(String incorporationNumber,
            Pageable pageable);
}
