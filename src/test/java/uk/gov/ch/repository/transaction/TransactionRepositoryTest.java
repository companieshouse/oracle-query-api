package uk.gov.ch.repository.transaction;

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
public class TransactionRepositoryTest {
	
	@Mock
	JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	TransactionRepository transactionRepository;
	
	@Test
	@DisplayName("Get Transaction Json string is returned")
	void testGetTransactionJson() {
		String companyNumber = "12345678";
		String query = "SELECT PKG_CHS_GET_DATA.f_get_filing_history(?) from dual";
		String queryResponse = "query response";
		
		when(jdbcTemplate.queryForObject(query, String.class, companyNumber)).thenReturn(queryResponse);
		String response = transactionRepository.getTransactionJson(companyNumber);
		assertEquals(queryResponse, response);
	}

}
