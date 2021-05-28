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

    public static String SHAREHOLDER_COUNT_SQL = "SELECT COUNT(*) FROM shareholder sh INNER JOIN shareholding shd ON sh.shareholder_id = shd.shareholding_id WHERE sh.shareholding_id = ?";
    public static String SHAREHOLDER_ELECTED_COUNT_SQL = "SELECT COUNT(*) FROM shareholder_elected sh JOIN sholdingelc_sholderelc_link shl ON sh.shareholder_elected_id = shl.shareholder_elected_id  INNER JOIN shareholding_elected shd ON shd.shareholding_elected_id = shl.shareholding_elected_id WHERE shd.corporate_body_id =  ?";

    public List<Shareholder> getShareholders(String corporateBodyId) {
        List<Shareholder> list = jdbcTemplate.query(getCompanyShareholdersSql(corporateBodyId),
                new BeanPropertyRowMapper<>(Shareholder.class));

        if (list.isEmpty()) {
            list = jdbcTemplate.query(getCompanyShareholdersElectedSql(corporateBodyId),
                    new BeanPropertyRowMapper<>(Shareholder.class));
        }

        return list;
    }

    public int getShareholderCount(String corporateBodyId) {
        int count;
        count = jdbcTemplate.queryForObject(SHAREHOLDER_COUNT_SQL, Integer.class, corporateBodyId);
        if (count == 0) {
            count = jdbcTemplate.queryForObject(SHAREHOLDER_ELECTED_COUNT_SQL, Integer.class, corporateBodyId);
        }

        return count;
    }

    public static String getCompanyShareholdersSql(String id) {
        return "SELECT sh.shareholder_forename_1 as forename1, sh.shareholder_forename_2 as forename2, sh.shareholder_surname as surname, "
                + "sh.address_id as addressId, shd.number_of_shares as shares, shd.share_class_type_id as shareClassTypeId, shd.currency_type_id as currencyTypeId "
                + "FROM shareholder sh INNER JOIN shareholding shd ON sh.shareholder_id = shd.shareholding_id WHERE sh.shareholding_id = "
                + id;
    }

    public static String getCompanyShareholdersElectedSql(String id) {
        return "SELECT sh.shareholder_elected_forename_1 as forename1, sh.shareholder_elected_forename_2 as forename2, sh.shareholder_elected_surname as surname, "
                + "sh.address_id as addressId, shd.number_of_shares as shares, shd.share_class_type_id as shareClassTypeId, shd.currency_type_id as currencyTypeId "
                + "FROM shareholder_elected sh JOIN sholdingelc_sholderelc_link shl ON sh.shareholder_elected_id = shl.shareholder_elected_id "
                + "INNER JOIN shareholding_elected shd ON shd.shareholding_elected_id = shl.shareholding_elected_id WHERE shd.corporate_body_id ="
                + id;
    }

}
