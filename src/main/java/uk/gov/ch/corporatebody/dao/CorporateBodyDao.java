package uk.gov.ch.corporatebody.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.corporatebody.exception.CorporateBodyNotFoundException;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@Repository
public class CorporateBodyDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    private static final String GET_ACTION_CODE_SQL = "SELECT action_code_type_id FROM corporate_body WHERE incorporation_number = ?";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public long getActionCode(String companyNumber) throws CorporateBodyNotFoundException {
        try {
            long actionCode = jdbcTemplate.queryForObject(GET_ACTION_CODE_SQL, Long.class, companyNumber);
            
            LOGGER.info("Returning action code " + actionCode + " for company number " + companyNumber);
            
            return actionCode;
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("No results were found when getting action code for company number " + companyNumber, e);
            
            throw new CorporateBodyNotFoundException(e.getMessage());
        }
    }
}
