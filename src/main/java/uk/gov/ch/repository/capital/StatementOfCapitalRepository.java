package uk.gov.ch.repository.capital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.StatementOfCapitalNotFoundException;
import uk.gov.ch.model.capital.StatementOfCapital;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

import java.util.List;

@Repository
public class StatementOfCapitalRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    static final String STATEMENT_OF_CAPITAL_SQL = "SELECT coalesce(ssc.non_standard_share_class,sct.share_class_type_desc) as classOfShares, ctp.currency_type_code as currency,"
            + "ssce.number_of_shares_allotted as numberAllotted, ssce.aggregate_nominal_value as aggregateNominalValue,"
            + "ssce.desc_of_rights as prescribedParticulars, ct.tot_number_shares_for_currency as totalNumberOfShares,"
            + "ct.tot_agg_nom_value_for_currency as totalAggregateNominalValue, ct.agg_amount_unpaid_for_currency as totalAmountUnpaidForCurrency "
            + "FROM corporate_body cb "
            + "LEFT JOIN statement_of_capital soc ON cb.corporate_body_id = soc.corporate_body_id "
            + "LEFT JOIN soc_currency_totals ct ON soc.statement_of_capital_id = ct.statement_of_capital_id "
            + "LEFT JOIN currency_type ctp ON ct.currency_type_id = ctp.currency_type_id "
            + "LEFT JOIN soc_share_class ssc ON ct.soc_currency_totals_id = ssc.soc_currency_totals_id "
            + "LEFT JOIN share_class_type sct ON sct.share_class_type_id = ssc.share_class_type_id "
            + "LEFT JOIN soc_share_class_entry ssce ON ssc.soc_share_class_id = ssce.soc_share_class_id "
            + "WHERE soc.is_current_ind = 'Y' and cb.incorporation_number = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<StatementOfCapital> getStatementOfCapital(String incorporationNumber) throws StatementOfCapitalNotFoundException {
        try {
            List<StatementOfCapital> statementOfCapitalList = jdbcTemplate.query(STATEMENT_OF_CAPITAL_SQL, getParam(incorporationNumber), new BeanPropertyRowMapper<>(StatementOfCapital.class));
            LOGGER.info("Returned statement of capital list " + statementOfCapitalList.size() + " for company number " + incorporationNumber);
            return statementOfCapitalList;
        } catch(EmptyResultDataAccessException e) {
            LOGGER.error("No results were found when getting statement of capital for company number " + incorporationNumber, e);
            throw new StatementOfCapitalNotFoundException(e.getMessage());
        }
    }

    private PreparedStatementSetter getParam(String param) {
        return preparedStatement -> preparedStatement.setString(1, param);
    }
}
