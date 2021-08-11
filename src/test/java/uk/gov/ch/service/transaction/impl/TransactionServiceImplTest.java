package uk.gov.ch.service.transaction.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.gov.ch.exception.TransactionMappingException;
import uk.gov.ch.model.transaction.jsondatamodels.FilingHistoryTransaction;
import uk.gov.ch.repository.transaction.TransactionRepository;
import uk.gov.ch.transformers.transaction.TransactionTransformer;
import uk.gov.companieshouse.api.model.filinghistory.FilingApi;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    TransactionTransformer transactionTransformer;

    @Mock
    ObjectMapper objectMapper;

    @Mock
    JsonNode mockJsonNode;

    @InjectMocks
    TransactionServiceImpl transactionService;

    private static final String COMPANY_NUMBER = "12345678";

    @Test
    @DisplayName("Test get transactions returns successfully")
    void testGetTransactionReturnsSuccessfully() throws Exception {
        when(transactionRepository.getTransactionJson(COMPANY_NUMBER)).thenReturn(getResponseJson());
        when(objectMapper.readValue(getResponseJson(), JsonNode.class)).thenReturn(mockJsonNode);
        when(mockJsonNode.get("filing_history")).thenReturn(mockJsonNode);
        when(objectMapper.convertValue(any(JsonNode.class),
                ArgumentMatchers.<TypeReference<List<FilingHistoryTransaction>>>any()))
                        .thenReturn(getFilingHistoryTransactionList());
        List<FilingApi> response = transactionService.getTransactions(COMPANY_NUMBER);
        assertNotNull(response);
        assertEquals(1, response.size());

    }

    @Test
    @DisplayName("Test get transaction returns empty list")
    void testGetTransactionRepositoryEmptyResponse() throws Exception {
        when(transactionRepository.getTransactionJson(COMPANY_NUMBER)).thenReturn("");
        List<FilingApi> response = transactionService.getTransactions(COMPANY_NUMBER);
        assertNotNull(response);
        assertTrue(response.isEmpty());
    }

    @Test
    @DisplayName("Test get transaction returns null list")
    void testGetTransactionRepositoryNullResponse() throws Exception {
        when(transactionRepository.getTransactionJson(COMPANY_NUMBER)).thenReturn(null);
        List<FilingApi> response = transactionService.getTransactions(COMPANY_NUMBER);
        assertNotNull(response);
        assertTrue(response.isEmpty());
    }
    
    @Test
    @DisplayName("Test get transaction returns Company Not Found string")
    void testGetTransactionRepositoryCompanyNotFoundString() throws Exception {
        when(transactionRepository.getTransactionJson(COMPANY_NUMBER)).thenReturn("Company Not Found");
        List<FilingApi> response = transactionService.getTransactions(COMPANY_NUMBER);
        assertNotNull(response);
        assertTrue(response.isEmpty());
    }

    @Test
    @DisplayName("Test get transaction mapper throws a JsonMappingException")
    void testGetTransactionThrowsJsonMappingException() throws Exception {
        when(transactionRepository.getTransactionJson(COMPANY_NUMBER)).thenReturn(getResponseJson());
        when(objectMapper.readValue(getResponseJson(), JsonNode.class)).thenThrow(JsonMappingException.class);
        assertThrows(TransactionMappingException.class, () -> {
            transactionService.getTransactions(COMPANY_NUMBER);
        });
    }

    @Test
    @DisplayName("Test get transaction mapper throws a JsonProcessingException")
    void testGetTransactionThrowsJsonProcessingException() throws Exception {
        when(transactionRepository.getTransactionJson(COMPANY_NUMBER)).thenReturn(getResponseJson());
        when(objectMapper.readValue(getResponseJson(), JsonNode.class)).thenThrow(JsonProcessingException.class);
        assertThrows(TransactionMappingException.class, () -> {
            transactionService.getTransactions(COMPANY_NUMBER);
        });
    }

    private String getResponseJson() {
        return "{" + 
                    "\"filing_history\": [" + 
                        "{" + 
                            "\"entity_id\": \"3123724769\", "
                          + "\"receive_date\": \"20210102121314\", " 
                          + "\"category\": \"2\", " 
                          + "\"form_type\": \"AP01\", "
                          + "\"barcode\": \"AA1111AA\", " 
                          + "\"description\": \"DIRECTOR APPOINTED NAME GOES HERE\", "
                          + "\"document_id\": \"123456789012345\" " 
                     + "}, " 
                     + "{ " 
                         + "\"entity_id\": \"345678912\", "
                         + "\"receive_date\": \"20210203121314\", " 
                         + "\"category\": \"1\", " 
                         + "\"form_type\": \"AR01\", "
                         + "\"barcode\": \"BB2222BB\", " 
                         + "\"description\": \"01/02/12 FULL LIST\", "
                         + "\"document_id\": \"000X424FB3V5834\", " 
                         + "\"child\": [ " 
                             + "{" + "\"entity_id\": \"345678912\", "
                                   + "\"receive_date\": \"20210203121314\", " 
                                   + "\"category\": \"1\", " 
                                   + "\"form_type\": \"SH01\", "
                                   + "\"barcode\": \"CC3333CC\", " 
                                   + "\"description\": \"01/02/03 STATEMENT OF CAPITAL;GBP LOTS\", "
                                   + "\"document_id\": \"234567890123456\" " 
                             + "} " 
                          + "] " 
                    + "}"
                + "]"
            + "}";
    }

    private List<FilingHistoryTransaction> getFilingHistoryTransactionList() {
        List<FilingHistoryTransaction> filingHistoryTransactionList = new ArrayList<>();
        FilingHistoryTransaction fht = new FilingHistoryTransaction();
        fht.setBarcode("AAAAAAAA");
        fht.setCategory("1");
        fht.setDescription("DESCRIPTION");
        fht.setDocumentId("11111111");
        fht.setEntityId(123456789L);
        fht.setFormType("FORM_TYPE");
        fht.setReceiveDate("20210102111213");
        filingHistoryTransactionList.add(fht);
        return filingHistoryTransactionList;
    }
}
