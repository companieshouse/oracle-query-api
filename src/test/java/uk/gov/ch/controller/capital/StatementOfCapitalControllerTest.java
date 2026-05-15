package uk.gov.ch.controller.capital;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import uk.gov.ch.exception.ServiceException;
import uk.gov.ch.exception.StatementOfCapitalNotFoundException;
import uk.gov.ch.model.capital.StatementOfCapital;
import uk.gov.ch.service.capital.StatementOfCapitalService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(StatementOfCapitalController.class)
class StatementOfCapitalControllerTest {

    private static final String COMPANY_NUMBER = "12345678";
    private static final String INVALID_COMPANY_NUMBER = "123#5678!";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    StatementOfCapitalService statementOfCapitalService;

    StatementOfCapitalController statementOfCapitalController;

    @BeforeEach
    void setUp() {
        statementOfCapitalController = new StatementOfCapitalController(statementOfCapitalService);
    }

    @Test
    @DisplayName("Get statement of capital code")
    void testGetStatementOfCapital() throws StatementOfCapitalNotFoundException, ServiceException {
        StatementOfCapital statementOfCapital = new StatementOfCapital();
        when(statementOfCapitalService.getStatementOfCapital(COMPANY_NUMBER)).thenReturn(statementOfCapital);

        ResponseEntity<StatementOfCapital> response =  statementOfCapitalController.getStatementOfCapital(COMPANY_NUMBER);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Get statement of capital code - not found")
    void testGetStatementOfCapitalNotFound() throws StatementOfCapitalNotFoundException, ServiceException {

        when(statementOfCapitalService.getStatementOfCapital(COMPANY_NUMBER)).thenThrow(new StatementOfCapitalNotFoundException("Test"));

        ResponseEntity<StatementOfCapital> response =  statementOfCapitalController.getStatementOfCapital(COMPANY_NUMBER);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Get statement of capital code - too many results found")
    void testGetStatementOfCapitalTooManyFound() throws StatementOfCapitalNotFoundException, ServiceException {

        when(statementOfCapitalService.getStatementOfCapital(COMPANY_NUMBER)).thenThrow(new ServiceException("Test"));

        ResponseEntity<StatementOfCapital> response = statementOfCapitalController.getStatementOfCapital(COMPANY_NUMBER);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    @DisplayName("Get statement of capital code - invalid company number")
    void testGetStatementOfCapitalInvalidCompanyNumber() throws Exception {

        mockMvc.perform(get("/company/{companyNumber}/statement-of-capital", INVALID_COMPANY_NUMBER))
            .andExpect(status().isBadRequest());
    }

}
