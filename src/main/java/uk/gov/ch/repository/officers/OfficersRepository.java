package uk.gov.ch.repository.officers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OfficersRepository {

    private static final String GET_OFFICERS_SQL = "SELECT PKG_CHS_GET_DATA.F_GET_OFFICER_DATA('00327073') from dual";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public String getOfficers(String companyNumber) {
        return jdbcTemplate.queryForObject(GET_OFFICERS_SQL, String.class, companyNumber);
    }
}
