package uk.gov.ch.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.model.Shareholder;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@Repository
public class ShareholderDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static String SHAREHOLDER_COUNT_SQL = "SELECT COUNT(*) FROM shareholder sh INNER JOIN shareholding shd ON sh.shareholder_id = shd.shareholding_id JOIN corporate_body cb on cb.corporate_body_id = shd.corporate_body_id WHERE cb.incorporation_number = ?";
    public static String SHAREHOLDER_ELECTED_COUNT_SQL = "SELECT COUNT(*) FROM shareholder_elected sh JOIN sholdingelc_sholderelc_link shl ON sh.shareholder_elected_id = shl.shareholder_elected_id  INNER JOIN shareholding_elected shd ON shd.shareholding_elected_id = shl.shareholding_elected_id JOIN corporate_body cb on cb.corporate_body_id = shd.corporate_body_id WHERE cb.incorporation_number = ?";

    public List<Shareholder> getShareholders(String incorporationNumber) {
        List<Shareholder> list = jdbcTemplate.query(getShareholdersSql(incorporationNumber),
                new BeanPropertyRowMapper<>(Shareholder.class));

        LOGGER.info("Returned " + list.size() + " shareholders from SHAREHOLDER tables.");

        if (list.isEmpty()) {
            list = jdbcTemplate.query(getShareholdersElectedSql(incorporationNumber),
                    new BeanPropertyRowMapper<>(Shareholder.class));

            LOGGER.info("Returned " + list.size() + " shareholders from SHAREHOLDER_ELECTED tables.");
        }

        return list;
    }

    public int getShareholderCount(String incorporationNumber) {
        int count;
        count = jdbcTemplate.queryForObject(SHAREHOLDER_COUNT_SQL, Integer.class, incorporationNumber);
        LOGGER.info("Returned shareholders count of" + count + " shareholders from SHAREHOLDER tables.");
        if (count == 0) {
            count = jdbcTemplate.queryForObject(SHAREHOLDER_ELECTED_COUNT_SQL, Integer.class, incorporationNumber);
            LOGGER.info("Returned shareholders count of" + count + " shareholders from SHAREHOLDER_ELECTED tables.");
        }

        return count;
    }

    public static String getShareholdersSql(String incorporationNumber) {
        return "SELECT sh.shareholder_forename_1 as forename1, sh.shareholder_forename_2 as forename2, sh.shareholder_surname as surname, "
                + "sh.address_id as addressId, shd.number_of_shares as shares, shd.share_class_type_id as shareClassTypeId, shd.currency_type_id as currencyTypeId "
                + "FROM shareholder sh INNER JOIN shareholding shd ON sh.shareholder_id = shd.shareholding_id "
                + "JOIN corporate_body cb on cb.corporate_body_id = shd.corporate_body_id WHERE cb.incorporation_number = '" 
                + incorporationNumber +"'";
    }



    public static String getShareholdersElectedSql(String incorporationNumber) {
        return "SELECT sh.shareholder_elected_forename_1 as forename1, sh.shareholder_elected_forename_2 as forename2, sh.shareholder_elected_surname as surname, "
                + "sh.address_id as addressId, shd.number_of_shares as shares, shd.share_class_type_id as shareClassTypeId, shd.currency_type_id as currencyTypeId "
                + "FROM shareholder_elected sh JOIN sholdingelc_sholderelc_link shl ON sh.shareholder_elected_id = shl.shareholder_elected_id "
                + "INNER JOIN shareholding_elected shd ON shd.shareholding_elected_id = shl.shareholding_elected_id "
                + "JOIN corporate_body cb on cb.corporate_body_id = shd.corporate_body_id WHERE cb.incorporation_number = '"
                + incorporationNumber +"'";
    }

}
