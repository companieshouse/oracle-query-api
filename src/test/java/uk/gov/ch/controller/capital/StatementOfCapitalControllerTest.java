package uk.gov.ch.controller.capital;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.ch.exception.StatementOfCapitalNotFoundException;
import uk.gov.ch.model.capital.StatementOfCapital;
import uk.gov.ch.service.capital.StatementOfCapitalService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatementOfCapitalControllerTest {

    private static final String COMPANY_NUMBER = "12345678";

    @InjectMocks
    StatementOfCapitalController statementOfCapitalController;

    @Mock
    StatementOfCapitalService statementOfCapitalService;

    @Test
    @DisplayName("Get statement of capital code")
    void testGetStatementOfCapital() throws StatementOfCapitalNotFoundException {
        StatementOfCapital statementOfCapital = new StatementOfCapital();
        when(statementOfCapitalService.getStatementOfCapital(COMPANY_NUMBER)).thenReturn(statementOfCapital);

        ResponseEntity<StatementOfCapital> response =  statementOfCapitalController.getStatementOfCapital(COMPANY_NUMBER);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Get statement of capital code - not found")
    void testGetStatementOfCapitalNotFound() throws StatementOfCapitalNotFoundException {

        when(statementOfCapitalService.getStatementOfCapital(COMPANY_NUMBER)).thenThrow(new StatementOfCapitalNotFoundException("Test"));

        ResponseEntity<StatementOfCapital> response =  statementOfCapitalController.getStatementOfCapital(COMPANY_NUMBER);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
