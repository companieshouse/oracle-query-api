package uk.gov.ch.controller.transaction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.ch.model.transaction.jsondatamodels.Gaz2Transaction;
import uk.gov.ch.service.transaction.Gaz2RequestedService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Gaz2RequestedControllerTest {

    private static final String COMPANY_NUMBER = "12345678";

    @Mock
    private Gaz2RequestedService gaz2RequestedService;

    @InjectMocks
    private Gaz2RequestedController gaz2RequestedController;

    @Test
    @DisplayName("Get Requested Gaz2 for company - no transaction found")
    void testGetGaz2RequestedNoTransactionFound() {
        when(gaz2RequestedService.getRequestedGaz2(COMPANY_NUMBER)).thenReturn(null);

        ResponseEntity<Gaz2Transaction> controllerResult = gaz2RequestedController.getGaz2Requested(COMPANY_NUMBER);

        assertNull(controllerResult.getBody());
        assertEquals(HttpStatus.OK, controllerResult.getStatusCode());
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
}