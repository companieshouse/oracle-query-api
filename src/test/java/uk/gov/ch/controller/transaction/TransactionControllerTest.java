package uk.gov.ch.controller.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import uk.gov.ch.exception.TransactionMappingException;
import uk.gov.ch.service.transaction.TransactionService;
import uk.gov.companieshouse.api.model.filinghistory.FilingApi;
import uk.gov.companieshouse.api.model.filinghistory.FilingHistoryApi;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @Mock
    TransactionService transactionService;

    @InjectMocks
    TransactionController transactionController;

    private final static String COMPANY_NUMBER = "12345678";

    @Test
    @DisplayName("Get transaction history returns a 200 and a list of FilingApi")
    void testGetTransactionHistoryReturnsSuccessfully() throws Exception {
        List<FilingApi> filingApiList = new ArrayList<>();
        FilingHistoryApi filingHistoryApi = new FilingHistoryApi();
        filingApiList.add(new FilingApi());
        filingHistoryApi.setItems(filingApiList);
        when(transactionService.getTransactions(COMPANY_NUMBER)).thenReturn(filingHistoryApi);
        ResponseEntity<FilingHistoryApi> response = transactionController.getTransactionHistory(COMPANY_NUMBER);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(filingHistoryApi.getItems().size(), response.getBody().getItems().size());
    }

    @Test
    @DisplayName("Get transaction history with no company number returns a Http Bad Request")
    void testGetTransactionHistoryNullCompanyNumberReturnsBadRequest() {
        ResponseEntity<FilingHistoryApi> response = transactionController.getTransactionHistory(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("Get transaction history with no company number returns a Http Bad Request")
    void testGetTransactionHistoryEmptyCompanyNumberReturnsBadRequest() {
        ResponseEntity<FilingHistoryApi> response = transactionController.getTransactionHistory("");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("Get transaction history returns a Server Error if TransactionMappingException thrown")
    void testGetTransactionHistoryTransactionMappingExceptionReturnsServerError() throws Exception {
        when(transactionService.getTransactions(COMPANY_NUMBER)).thenThrow(TransactionMappingException.class);
        ResponseEntity<FilingHistoryApi> response = transactionController.getTransactionHistory(COMPANY_NUMBER);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    @DisplayName("Get transaction history no results found returns a Http Not Found")
    void testGetTransactionHistoryNoResultsReturnsNotFound() throws Exception {
        List<FilingApi> filingApiList = new ArrayList<>();
        FilingHistoryApi filingHistoryApi = new FilingHistoryApi();
        filingHistoryApi.setItems(filingApiList);
        when(transactionService.getTransactions(COMPANY_NUMBER)).thenReturn(filingHistoryApi);
        ResponseEntity<FilingHistoryApi> response = transactionController.getTransactionHistory(COMPANY_NUMBER);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    
    @Test
    @DisplayName("Get transaction history items array null returns a Http Not Found")
    void testGetTransactionHistoryNullItemsReturnsNotFound() throws Exception {
    	FilingHistoryApi filingHistoryApi = new FilingHistoryApi();
        when(transactionService.getTransactions(COMPANY_NUMBER)).thenReturn(filingHistoryApi);
        ResponseEntity<FilingHistoryApi> response = transactionController.getTransactionHistory(COMPANY_NUMBER);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
