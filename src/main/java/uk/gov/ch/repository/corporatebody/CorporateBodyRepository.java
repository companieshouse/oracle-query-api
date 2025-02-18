package uk.gov.ch.repository.corporatebody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.CorporateBodyNotFoundException;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@Repository
public class CorporateBodyRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OracleQueryApplication.APPLICATION_NAME_SPACE);

    private static final String GET_TRADED_STATUS_SQL = "SELECT COMPANY_TRADED_TYPE_ID FROM corporate_body WHERE incorporation_number = ?";

    private static final String GET_ACTION_CODE_SQL = "SELECT action_code_type_id FROM corporate_body WHERE incorporation_number = ?";

    private static final String GET_COMPANY_PROFILE_SQL = "SELECT PKG_CHS_GET_DATA.F_GET_COMPANY_DATA(?) from dual";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Long getActionCode(String companyNumber) throws CorporateBodyNotFoundException {
        try {
            Long actionCode = jdbcTemplate.queryForObject(GET_ACTION_CODE_SQL, Long.class,
                    companyNumber);

            LOGGER.info(
                    "Returning action code " + actionCode + " for company number " + companyNumber);

            return actionCode;
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("No results were found when getting action code for company number "
                    + companyNumber, e);

            throw new CorporateBodyNotFoundException(e.getMessage());
        }
    }

    public Long getTradedStatus(String companyNumber) throws CorporateBodyNotFoundException {
        try {
            Long tradedStatus = jdbcTemplate.queryForObject(GET_TRADED_STATUS_SQL, Long.class,
                    companyNumber);

            LOGGER.info("Returning traded status " + tradedStatus + " for company number "
                    + companyNumber);

            return tradedStatus;
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("No results were found when getting traded status for company number "
                    + companyNumber, e);

            throw new CorporateBodyNotFoundException(e.getMessage());
        }
    }

    public String getCompanyProfile(String companyNumber) {
        return jdbcTemplate.queryForObject(GET_COMPANY_PROFILE_SQL, String.class, companyNumber);
    }
}
