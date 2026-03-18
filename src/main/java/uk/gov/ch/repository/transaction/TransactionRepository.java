package uk.gov.ch.repository.transaction;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepository {


    private static final String FILING_HISTORY_SQL = "SELECT PKG_CHS_GET_DATA.f_get_filing_history(?) from dual";

    private JdbcTemplate jdbcTemplate;

    public TransactionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getTransactionJson(String companyNumber) {
        return jdbcTemplate.queryForObject(FILING_HISTORY_SQL, String.class, companyNumber);
    }

}
