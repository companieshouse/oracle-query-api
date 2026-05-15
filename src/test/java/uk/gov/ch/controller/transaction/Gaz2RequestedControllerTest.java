package uk.gov.ch.controller.transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
import uk.gov.ch.model.transaction.jsondatamodels.Gaz2Transaction;
import uk.gov.ch.service.transaction.Gaz2RequestedService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(Gaz2RequestedController.class)
class Gaz2RequestedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private Gaz2RequestedService gaz2RequestedService;

    private Gaz2RequestedController gaz2RequestedController;

    private static final String COMPANY_NUMBER = "12345678";

    private static final String INVALID_COMPANY_NUMBER = "123#5678!";

    @BeforeEach
    void setUp() {
        gaz2RequestedController = new Gaz2RequestedController(gaz2RequestedService);
    }

    @Test
    @DisplayName("Get Requested Gaz2 for company - no transaction found")
    void testGetGaz2RequestedNoTransactionFound() {
        when(gaz2RequestedService.getRequestedGaz2(COMPANY_NUMBER)).thenReturn(null);

        ResponseEntity<Gaz2Transaction> controllerResult = gaz2RequestedController.getGaz2Requested(COMPANY_NUMBER);

        assertNull(controllerResult.getBody());
        assertEquals(HttpStatus.NO_CONTENT, controllerResult.getStatusCode());
    }

    @Test
    @DisplayName("Get Requested Gaz2 for company - success path")
    void testGetGaz2Requested() {
        Gaz2Transaction gaz2Transaction = gaz2Transaction();
        when(gaz2RequestedService.getRequestedGaz2(COMPANY_NUMBER)).thenReturn(gaz2Transaction);

        ResponseEntity<Gaz2Transaction> controllerResult = gaz2RequestedController.getGaz2Requested(COMPANY_NUMBER);

        assertEquals(gaz2Transaction, controllerResult.getBody());
        assertEquals(HttpStatus.OK, controllerResult.getStatusCode());
    }

    private Gaz2Transaction gaz2Transaction() {
        Gaz2Transaction gaz2Transaction = new Gaz2Transaction();
        gaz2Transaction.setType("type");
        gaz2Transaction.setStatusType("status_type");
        gaz2Transaction.setId("id");

        return gaz2Transaction;
    }

    @Test
    @DisplayName("Get Gaz2 request - invalid company number")
    void testGetGaz2RequestedInvalidCompanyNumber() throws Exception {

        mockMvc.perform(get("/company/{companyNumber}/gaz2-requested", INVALID_COMPANY_NUMBER))
            .andExpect(status().isBadRequest());
    }

}
