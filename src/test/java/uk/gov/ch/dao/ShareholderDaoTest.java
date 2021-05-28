package uk.gov.ch.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
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

import uk.gov.ch.model.Shareholder;

@ExtendWith(MockitoExtension.class)
public class ShareholderDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    ShareholderDao dao;

    private static final String CORPORATE_BODY_ID = "12345678";

    @Test
    @DisplayName("Get shareholders - corporate body with shareholders")
    public void getShareholdersFromShareholdersTableTest() {
        List<Shareholder> expectedList = new ArrayList<Shareholder>();
        expectedList.add(new Shareholder());

        when(jdbcTemplate.query(eq(ShareholderDao.getCompanyShareholdersSql(CORPORATE_BODY_ID)),
                any(BeanPropertyRowMapper.class))).thenReturn(expectedList);

        List<Shareholder> resultList = dao.getShareholders(CORPORATE_BODY_ID);

        verify(jdbcTemplate, times(0)).query(eq(ShareholderDao.getCompanyShareholdersElectedSql(CORPORATE_BODY_ID)),
                any(BeanPropertyRowMapper.class));

        assertEquals(expectedList, resultList);
        assertEquals(1, resultList.size());

    }

    @Test
    @DisplayName("Get shareholders - corporate body with elected shareholders")
    public void getShareholdersFromShareholdersElectedTableTest() {
        List<Shareholder> expectedList = new ArrayList<Shareholder>();
        expectedList.add(new Shareholder());

        when(jdbcTemplate.query(eq(ShareholderDao.getCompanyShareholdersSql(CORPORATE_BODY_ID)),
                any(BeanPropertyRowMapper.class))).thenReturn(new ArrayList<Shareholder>());

        when(jdbcTemplate.query(eq(ShareholderDao.getCompanyShareholdersElectedSql(CORPORATE_BODY_ID)),
                any(BeanPropertyRowMapper.class))).thenReturn(expectedList);

        List<Shareholder> resultList = dao.getShareholders(CORPORATE_BODY_ID);

        assertEquals(expectedList, resultList);
        assertEquals(1, resultList.size());
    }

    @Test
    @DisplayName("Get shareholders - corporate body with neither shareholders nor elected shareholders")
    public void getShareholdersForCorporateBodyWithNoneTest() {
        List<Shareholder> expectedList = new ArrayList<Shareholder>();

        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(expectedList);

        List<Shareholder> resultList = dao.getShareholders(CORPORATE_BODY_ID);

        assertEquals(expectedList, resultList);
        assertEquals(0, resultList.size());
    }

    @Test
    @DisplayName("Get shareholders count - corporate body with shareholders")
    public void getShareholderCountFromShareholdersTableTest() {
        when(jdbcTemplate.queryForObject(eq(ShareholderDao.SHAREHOLDER_COUNT_SQL), eq(Integer.class),
                eq(CORPORATE_BODY_ID))).thenReturn(1);

        int result = dao.getShareholderCount(CORPORATE_BODY_ID);

        assertEquals(1, result);
    }

    @Test
    @DisplayName("Get shareholders count - corporate body with elected shareholders")
    public void getShareholderCountFromShareholdersElectedTableTest() {
        when(jdbcTemplate.queryForObject(eq(ShareholderDao.SHAREHOLDER_COUNT_SQL), eq(Integer.class),
                eq(CORPORATE_BODY_ID))).thenReturn(0);

        when(jdbcTemplate.queryForObject(eq(ShareholderDao.SHAREHOLDER_ELECTED_COUNT_SQL), eq(Integer.class),
                eq(CORPORATE_BODY_ID))).thenReturn(2);

        int result = dao.getShareholderCount(CORPORATE_BODY_ID);

        assertEquals(2, result);
    }

    @Test
    @DisplayName("Get shareholders count - corporate body with neither shareholders nor elected shareholders")
    public void getShareholderCountForCorporateBodyWithNoneTest() {
        when(jdbcTemplate.queryForObject(any(), eq(Integer.class), eq(CORPORATE_BODY_ID))).thenReturn(0);

        int result = dao.getShareholderCount(CORPORATE_BODY_ID);

        assertEquals(0, result);
    }

}
