package uk.gov.ch.repository.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import uk.gov.ch.OracleQueryApplication;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@Repository
public class TransactionRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private JdbcTemplate jdbcTemplate;
	
	private static final String FILING_HISTORY_SQL = "SELECT PKG_CHS_GET_DATA.f_get_filing_history(?) from dual";
	
	public String getTransactionJson(String companyNumber) {
		return jdbcTemplate.queryForObject(FILING_HISTORY_SQL, String.class, companyNumber);
	}

}
