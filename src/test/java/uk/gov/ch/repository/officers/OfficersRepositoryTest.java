package uk.gov.ch.repository.officers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

@ExtendWith(MockitoExtension.class)
class OfficersRepositoryTest {
    
    @Mock
    JdbcTemplate jdbcTemplate;
    
    @InjectMocks
    OfficersRepository repository; 
    
    @Test
    @DisplayName("Test get officers calls and returns a String")
    void testGetOfficers() {
        String expectedSql = "SELECT PKG_CHS_GET_DATA.F_GET_OFFICER_DATA(?) from dual";
        String companyNumber = "12345678";
        String queryResponse = "query response string";
        
        when(jdbcTemplate.queryForObject(expectedSql, String.class, companyNumber)).thenReturn(queryResponse);
        String response = repository.getOfficers(companyNumber);
        assertEquals(queryResponse, response);
    }

}
