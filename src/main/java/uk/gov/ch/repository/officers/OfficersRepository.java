package uk.gov.ch.repository.officers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OfficersRepository {

    private static final String GET_OFFICERS_SQL = "SELECT PKG_CHS_GET_DATA.F_GET_OFFICER_DATA(?) from dual";

    private JdbcTemplate jdbcTemplate;

    public OfficersRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getOfficers(String companyNumber) {
        return jdbcTemplate.queryForObject(GET_OFFICERS_SQL, String.class, companyNumber);
    }
}
