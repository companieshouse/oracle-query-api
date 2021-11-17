package uk.gov.ch.repository.officers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.officer.active.ActiveDirectorDetails;

public interface ActiveDirectorDetailsRepository extends PagingAndSortingRepository<ActiveDirectorDetails, Long> {

    @Query(value = "select * from ( SELECT "
            + "cba.officer_detail_id, "
            + "cba.officer_forename_1 AS fore_name_1, "
            + "cba.officer_forename_2 AS fore_name_2, "
            + "cba.officer_surname AS surname, "
            + "cba.occupation_desc AS occupation, "
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
            + "ura.HOUSE_NAME_NUMBER as RESIDENTIAL_ADDRESS_line_1, "
            + "ura.STREET as RESIDENTIAL_ADDRESS_line_2, "
            + "ura.CARE_OF as RESIDENTIAL_ADDRESS_CARE_OF, "
            + "ura.COUNTRY_NAME as RESIDENTIAL_ADDRESS_country, "
            + "ura.POST_TOWN as RESIDENTIAL_ADDRESS_locality, "
            + "ura.PO_BOX as RESIDENTIAL_ADDRESS_PO_BOX, "
            + "ura.POST_CODE as RESIDENTIAL_ADDRESS_POST_CODE, "
            + "ura.REGION as RESIDENTIAL_ADDRESS_REGION, "
            + "od.secure_director_service_ind AS secure_indicator "
            + "FROM corporate_body cb inner join corporate_body_appointment cba ON cba.corporate_body_id=cb.corporate_body_id "
            + "inner join officer_detail od on od.officer_detail_id=cba.officer_detail_id "
            + "inner join address adr on cba.service_address_id = adr.address_id "
            + "inner join usual_residential_address ura on ura.usual_residential_address_id = od.usual_residential_address_id "
            + "WHERE cb.incorporation_number = ? AND cba.resignation_ind = 'N' AND cba.appointment_type_id = 2 "
            + "group by "
            + "cba.officer_detail_id, "
            + "cba.officer_forename_1, "
            + "cba.officer_forename_2, "
            + "cba.officer_surname, "
            + "cba.occupation_desc, "
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
            + "od.secure_director_service_ind "
            + "having count(1)=1 "
            + ")", nativeQuery = true)
    Page<ActiveDirectorDetails> getActiveDirectorDetails(String incorporationNumber, Pageable pageable);

}
