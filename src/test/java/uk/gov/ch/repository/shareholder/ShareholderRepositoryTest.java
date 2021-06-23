package uk.gov.ch.repository.shareholder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import uk.gov.ch.model.shareholder.Shareholder;

@ExtendWith(MockitoExtension.class)
class ShareholderRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ShareholderRepository shareholderRepository;

    private static final String COMPANY_NUMBER = "12345678";

    @Test
    @DisplayName("Get shareholders - company with shareholders")
    void getShareholdersFromShareholdersTableTest() {
        List<Shareholder> expectedList = new ArrayList<Shareholder>();
        expectedList.add(new Shareholder());

        when(jdbcTemplate.query(eq(ShareholderRepository.SHAREHOLDER_LIST_SQL), any(PreparedStatementSetter.class), any(BeanPropertyRowMapper.class))).thenReturn(expectedList);

        List<Shareholder> resultList = shareholderRepository.getShareholders(COMPANY_NUMBER);

        assertEquals(expectedList, resultList);
        assertEquals(1, resultList.size());

    }

    @Test
    @DisplayName("Get shareholders - company with no shareholders")
    void getShareholdersForCorporateBodyWithNoneTest() {
        List<Shareholder> expectedList = new ArrayList<Shareholder>();

        when(jdbcTemplate.query(anyString(), any(PreparedStatementSetter.class), any(BeanPropertyRowMapper.class))).thenReturn(expectedList);

        List<Shareholder> resultList = shareholderRepository.getShareholders(COMPANY_NUMBER);

        assertEquals(expectedList, resultList);
        assertEquals(0, resultList.size());
    }

    @Test
    @DisplayName("Get shareholders count - company with shareholders")
    void getShareholderCountFromShareholdersTableTest() {
        when(jdbcTemplate.queryForObject(eq(ShareholderRepository.SHAREHOLDER_COUNT_SQL), eq(Integer.class),
                eq(COMPANY_NUMBER))).thenReturn(1);

        int result = shareholderRepository.getShareholderCount(COMPANY_NUMBER);

        assertEquals(1, result);
    }

    @Test
    @DisplayName("Get shareholders count - company with no shareholders")
    void getShareholderCountForCorporateBodyWithNoneTest() {
        when(jdbcTemplate.queryForObject(any(), eq(Integer.class), eq(COMPANY_NUMBER))).thenReturn(0);

        int result = shareholderRepository.getShareholderCount(COMPANY_NUMBER);

        assertEquals(0, result);
    }

}
