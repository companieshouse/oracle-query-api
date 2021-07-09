package uk.gov.ch.repository.officers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.model.officer.active.ActiveOfficerDetails;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@Repository
public class ActiveOfficerDetailsRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    static final String OFFICER_DETAILS_SQL = "SELECT "
            + "cba.officer_forename_1 AS foreName1, "
            + "cba.officer_forename_2 AS foreName2, "
            + "cba.officer_surname AS surname, "
            + "cba.occupation_desc AS occupation, "
            + "od.officer_nationality AS nationality, "
            + "od.officer_date_of_birth AS dateOfBirth, "
            + "cba.service_address_line_1 AS serviceAddressLine1, "
            + "cba.service_address_post_town AS serviceAddressPostTown, "
            + "cba.service_address_post_code AS serviceAddressPostCode, "
            + "ura.address_line_1 AS residentialAddressLine1, "
            + "ura.post_town AS residentialAddressPostTown, "
            + "ura.post_code AS residentialAddressPostCode, "
            + "od.secure_director_service_ind AS secureIndicator "
            + "FROM usual_residential_address ura LEFT JOIN officer_detail od on ura.usual_residential_address_id = od.usual_residential_address_id JOIN corporate_body_appointment cba ON cba.officer_detail_id = od.officer_detail_id "
            + "WHERE cba.corporate_body_id IN( select corporate_body_id from corporate_body where incorporation_number = ?) AND cba.resignation_ind = 'N'";

    public ActiveOfficerDetails getActiveOfficerDetails(String incorporationNumber) {
        List<ActiveOfficerDetails> list = jdbcTemplate.query(OFFICER_DETAILS_SQL, getParam(incorporationNumber),
                new BeanPropertyRowMapper<>(ActiveOfficerDetails.class));

        LOGGER.info("Returned Active Officer Details for company: " + incorporationNumber);
        return list.get(0);
    }

    private PreparedStatementSetter getParam(String param) {
        return preparedStatement -> preparedStatement.setString(1, param);
    }
}
