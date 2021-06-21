package uk.gov.ch.repository.shareholder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.model.shareholder.Shareholder;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@Repository
public class ShareholderRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    static final String SHAREHOLDER_COUNT_SQL = "SELECT COUNT(*) FROM shareholder sh INNER JOIN shareholding shd ON sh.shareholding_id = shd.shareholding_id JOIN corporate_body cb on cb.corporate_body_id = shd.corporate_body_id WHERE cb.incorporation_number = ?";
    
    static final String SHAREHOLDER_LIST_SQL = "SELECT sh.shareholder_forename_1 as forename1, sh.shareholder_forename_2 as forename2, sh.shareholder_surname as surname, "
        + "sh.address_id as addressId, shd.number_of_shares as shares, shd.share_class_type_id as shareClassTypeId, shd.currency_type_id as currencyTypeId "
        + "FROM shareholder sh INNER JOIN shareholding shd ON sh.shareholding_id = shd.shareholding_id "
        + "JOIN corporate_body cb on cb.corporate_body_id = shd.corporate_body_id WHERE cb.incorporation_number = ?";

    static final String SHAREHOLDER_ELECTED_LIST_SQL = "SELECT sh.shareholder_elected_forename_1 as forename1, sh.shareholder_elected_forename_2 as forename2, sh.shareholder_elected_surname as surname, "
        + "sh.address_id as addressId, shd.number_of_shares as shares, shd.share_class_type_id as shareClassTypeId, shd.currency_type_id as currencyTypeId "
        + "FROM shareholder_elected sh JOIN sholdingelc_sholderelc_link shl ON sh.shareholder_elected_id = shl.shareholder_elected_id "
        + "INNER JOIN shareholding_elected shd ON shd.shareholding_elected_id = shl.shareholding_elected_id "
        + "JOIN corporate_body cb on cb.corporate_body_id = shd.corporate_body_id WHERE cb.incorporation_number = ?";

    public List<Shareholder> getShareholders(String incorporationNumber) {
        List<Shareholder> list = jdbcTemplate.query(SHAREHOLDER_LIST_SQL, getParam(incorporationNumber), new BeanPropertyRowMapper<>(Shareholder.class));

        LOGGER.info("Returned " + list.size() + " shareholders from SHAREHOLDER tables.");

        if (list.isEmpty()) {
            list = jdbcTemplate.query(SHAREHOLDER_ELECTED_LIST_SQL, getParam(incorporationNumber), new BeanPropertyRowMapper<>(Shareholder.class));

            LOGGER.info("Returned " + list.size() + " shareholders from SHAREHOLDER_ELECTED tables.");
        }

        return list;
    }

    public int getShareholderCount(String incorporationNumber) {
        int count = jdbcTemplate.queryForObject(SHAREHOLDER_COUNT_SQL, Integer.class, incorporationNumber);
        
        LOGGER.info("Returned shareholders count of " + count + " shareholders from SHAREHOLDER tables.");

        return count;
    }

    private PreparedStatementSetter getParam(String param) {
        return  new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, param);
            }
        };
    }

}
