package uk.gov.ch.repository.officers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.model.officer.active.ActiveOfficerDetails;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;


public class ActiveOfficerDetailsRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String OFFICER_DETAILS_SQL = "SELECT cba.occupation_desc, cba.officer_forename_1, cba.officer_forename_2, cba.officer_surname, cba.service_address_line_1, cba.service_address_post_town, cba.service_address_post_code, ura.address_line_1, ura.post_town, ura.post_code, od.officer_nationality, od.officer_date_of_birth, od.secure_director_service_ind "
    + "FROM USUAL_RESIDENTIAL_ADDRESS ura LEFT JOIN OFFICER_DETAIL od on ura.USUAL_RESIDENTIAL_ADDRESS_ID = od.USUAL_RESIDENTIAL_ADDRESS_IDJOIN CORPORATE_BODY_APPOINTMENT cba ON cba.OFFICER_DETAIL_ID = od.officer_detail_id "
    + "WHERE cba.corporate_body_id IN( select corporate_body_id from corporate_body where incorporation_number = ?) "
    + "AND cba.resignation_ind = N";

    public ActiveOfficerDetails getActiveOfficerDetails(String incorporationNumber) {
        ActiveOfficerDetails details = jdbcTemplate.queryForObject(OFFICER_DETAILS_SQL, ActiveOfficerDetails.class, incorporationNumber);

        LOGGER.info("Returned Active Officer Details for company: " + incorporationNumber );

        return details;
    }
}
