package uk.gov.ch.repository.corporatebody;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import uk.gov.ch.exception.CorporateBodyNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CorporateBodyRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    private CorporateBodyRepository repository;

    private static final String INCORPORATION_NUMBER = "12345678";

    @BeforeEach
    void setUp() {
        repository = new CorporateBodyRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("Get action code - company not found")
    void testGetActionCodeNoCompanyFound() {
        when(jdbcTemplate.queryForObject(any(String.class), eq(Long.class), eq(INCORPORATION_NUMBER))).thenThrow(new EmptyResultDataAccessException(1));

        Assertions.assertThrows(CorporateBodyNotFoundException.class,
                () -> repository.getActionCode(INCORPORATION_NUMBER));
    }

    @Test
    @DisplayName("Get action code - company was found")
    void testGetActionCodeCompanyFound() throws CorporateBodyNotFoundException {
        final long dummyActionCode = 99;

        when(jdbcTemplate.queryForObject(any(String.class), eq(Long.class), eq(INCORPORATION_NUMBER))).thenReturn(dummyActionCode);

        long response = repository.getActionCode(INCORPORATION_NUMBER);
        assertEquals(dummyActionCode, response);
    }

    @Test
    @DisplayName("Get traded status - company not found")
    void testGetTradedStatusNoCompanyFound() {
        when(jdbcTemplate.queryForObject(any(String.class), eq(Long.class), eq(INCORPORATION_NUMBER))).thenThrow(new EmptyResultDataAccessException(1));

        Assertions.assertThrows(CorporateBodyNotFoundException.class,
                () -> repository.getTradedStatus(INCORPORATION_NUMBER));
    }

    @Test
    @DisplayName("Get traded status - company was found")
    void testGetTradedStatusCompanyFound() throws CorporateBodyNotFoundException {
        final long dummyActionCode = 99;

        when(jdbcTemplate.queryForObject(any(String.class), eq(Long.class), eq(INCORPORATION_NUMBER))).thenReturn(dummyActionCode);

        long response = repository.getTradedStatus(INCORPORATION_NUMBER);
        assertEquals(dummyActionCode, response);
    }

}
