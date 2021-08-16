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
            + "od.officer_nationality AS nationality, "
            + "od.officer_date_of_birth AS date_of_birth, "
            + "cba.service_address_line_1 AS service_address_line_1, "
            + "cba.service_address_post_town AS service_address_post_town, "
            + "cba.service_address_post_code AS service_address_post_code, "
            + "ura.address_line_1 AS ura_line_1, "
            + "ura.post_town AS ura_post_town, "
            + "ura.post_code AS ura_post_code, "
            + "od.secure_director_service_ind AS secure_indicator "
            + "FROM corporate_body cb inner join corporate_body_appointment cba ON cba.corporate_body_id=cb.corporate_body_id "
            + "inner join officer_detail od on od.officer_detail_id=cba.officer_detail_id "
            + "inner join usual_residential_address ura on ura.usual_residential_address_id = od.usual_residential_address_id "
            + "WHERE cb.incorporation_number = ? AND cba.resignation_ind = 'N' AND cba.appointment_type_id = 2 "
            + "group by cba.officer_detail_id, cba.officer_forename_1, cba.officer_forename_2, cba.officer_surname, cba.occupation_desc, od.officer_nationality, od.officer_date_of_birth, cba.service_address_line_1, cba.service_address_post_town, cba.service_address_post_code, ura.address_line_1, ura.post_town, ura.post_code, od.secure_director_service_ind having count(1)=1 "
            + ") where rownum <= 1", nativeQuery = true)
    Page<ActiveDirectorDetails> getActiveDirectorDetails(String incorporationNumber, Pageable pageable);
}
