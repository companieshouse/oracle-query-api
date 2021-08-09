package uk.gov.ch.repository.officers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.officer.active.ActiveDirectorDetails;

public interface ActiveDirectorDetailsRepository extends PagingAndSortingRepository<ActiveDirectorDetails, Long> {

    @Query(value = "SELECT "
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
            + "FROM usual_residential_address ura "
            + "RIGHT JOIN officer_detail od on ura.usual_residential_address_id = od.usual_residential_address_id "
            + "JOIN corporate_body_appointment cba ON cba.officer_detail_id = od.officer_detail_id "
            + "WHERE cba.corporate_body_id IN( select corporate_body_id from corporate_body where incorporation_number = ?)"
            + "AND cba.resignation_ind = 'N' "
            + "AND cba.appointment_type_id = 2"
            + "AND cba.corporate_body_id in (SELECT corporate_body_id FROM corporate_body_appointment WHERE resignation_ind = 'N' AND appointment_type_id = 2 GROUP BY corporate_body_id HAVING COUNT(corporate_body_id) = 1)", nativeQuery = true)
    Page<ActiveDirectorDetails> getActiveDirectorDetails(String incorporationNumber, Pageable pageable);
}
