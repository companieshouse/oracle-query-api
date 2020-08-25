package uk.gov.ch.corporatebody.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import uk.gov.ch.corporatebody.exception.CorporateBodyNotFoundException;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CorporateBodyDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    CorporateBodyDao dao;

    private static final String INCORPORATION_NUMBER = "12345678";

    @Test
    @DisplayName("Get action code - company not found")
    public void testGetActionCodeNoCompanyFound() throws CorporateBodyNotFoundException {
        when(jdbcTemplate.queryForObject(any(String.class), eq(Integer.class), eq(INCORPORATION_NUMBER))).thenThrow(new EmptyResultDataAccessException(1));

        Assertions.assertThrows(CorporateBodyNotFoundException.class, () -> {
            dao.getActionCode(INCORPORATION_NUMBER);
          });
    }

    @Test
    @DisplayName("Get action code - company was found")
    public void testGetActionCodeCompanyFound() throws CorporateBodyNotFoundException {
        final int dummyActionCode = 99;
        
        when(jdbcTemplate.queryForObject(any(String.class), eq(Integer.class), eq(INCORPORATION_NUMBER))).thenReturn(dummyActionCode);

        int response = dao.getActionCode(INCORPORATION_NUMBER);
        assertEquals(dummyActionCode, response);
    }

}
