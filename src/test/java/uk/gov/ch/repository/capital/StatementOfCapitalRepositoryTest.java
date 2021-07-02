package uk.gov.ch.repository.capital;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import uk.gov.ch.exception.StatementOfCapitalNotFoundException;
import uk.gov.ch.model.capital.StatementOfCapital;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatementOfCapitalRepositoryTest {

    private static final String COMPANY_NUMBER = "12345678";

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private StatementOfCapitalRepository statementOfCapitalRepository;

    @Test
    @DisplayName("Get statement of capital")
    void getStatementOfCapitalTest() throws StatementOfCapitalNotFoundException {
        List<StatementOfCapital> expectedList = new ArrayList<StatementOfCapital>();
        expectedList.add(new StatementOfCapital());

        when(jdbcTemplate.query(eq(StatementOfCapitalRepository.STATEMENT_OF_CAPITAL_SQL),
                any(PreparedStatementSetter.class), any(BeanPropertyRowMapper.class))).thenReturn(expectedList);

        List<StatementOfCapital> resultList = statementOfCapitalRepository.getStatementOfCapital(COMPANY_NUMBER);
        assertEquals(expectedList, resultList);
        assertEquals(1, resultList.size());
    }

    @Test
    @DisplayName("Get statement of capital no results")
    void getStatementOfCapitalTestWithNoResults() throws StatementOfCapitalNotFoundException {
        List<StatementOfCapital> expectedList = new ArrayList<StatementOfCapital>();

        when(jdbcTemplate.query(eq(StatementOfCapitalRepository.STATEMENT_OF_CAPITAL_SQL),
                any(PreparedStatementSetter.class), any(BeanPropertyRowMapper.class))).thenReturn(expectedList);

        List<StatementOfCapital> resultList = statementOfCapitalRepository.getStatementOfCapital(COMPANY_NUMBER);
        assertEquals(expectedList, resultList);
        assertEquals(0, resultList.size());
    }

    @Test
    @DisplayName("Get statement of capital empty data access result")
    void getStatementOfCapitalTestEmptyDataAccessResult() throws StatementOfCapitalNotFoundException {
        List<StatementOfCapital> expectedList = new ArrayList<StatementOfCapital>();

        when(jdbcTemplate.query(eq(StatementOfCapitalRepository.STATEMENT_OF_CAPITAL_SQL),
                any(PreparedStatementSetter.class), any(BeanPropertyRowMapper.class))).thenThrow(EmptyResultDataAccessException.class);

        assertThrows(StatementOfCapitalNotFoundException.class, () -> statementOfCapitalRepository.getStatementOfCapital(COMPANY_NUMBER));
    }
}
