package uk.gov.ch.service.transaction.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
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
import uk.gov.companieshouse.api.model.filinghistory.FilingHistoryApi;

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
        List<FilingHistoryTransaction> transactionList = getFilingHistoryTransactionList();
        when(transactionRepository.getTransactionJson(COMPANY_NUMBER)).thenReturn(
                getResponseJson());
        when(objectMapper.readValue(getResponseJson(), JsonNode.class)).thenReturn(mockJsonNode);
        when(mockJsonNode.get("filing_history")).thenReturn(mockJsonNode);
        when(objectMapper.convertValue(any(JsonNode.class),
                ArgumentMatchers.<TypeReference<List<FilingHistoryTransaction>>>any()))
                .thenReturn(transactionList);
        when(transactionTransformer.convertToFilingHistoryApi(transactionList)).thenReturn(
                getFilingHistoryApi());
        FilingHistoryApi response = transactionService.getTransactions(COMPANY_NUMBER);
        assertNotNull(response);
        assertEquals(1, response.getItems().size());

    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
            "Company has no transactions",
            "Company Not Found"})
    @DisplayName("Test NoOfficersExistingException with transactionService.getTransactions")
    void testNoOfficersExistingExceptionOnConditions(String condition)
            throws TransactionMappingException {
        when(transactionRepository.getTransactionJson(COMPANY_NUMBER)).thenReturn(condition);
        FilingHistoryApi response = transactionService.getTransactions(COMPANY_NUMBER);
        assertAll(
                () -> assertNotNull(response),
                () -> assertNull(response.getItems())
        );
    }

    @Test
    @DisplayName("Test get transaction returns empty list")
    void testGetTransactionRepositoryEmptyResponse() throws Exception {
        when(transactionRepository.getTransactionJson(COMPANY_NUMBER)).thenReturn("");
        FilingHistoryApi response = transactionService.getTransactions(COMPANY_NUMBER);
        assertNotNull(response);
        assertNull(response.getItems());
    }

    @Test
    @DisplayName("Test get transaction returns null list")
    void testGetTransactionRepositoryNullResponse() throws Exception {
        when(transactionRepository.getTransactionJson(COMPANY_NUMBER)).thenReturn(null);
        FilingHistoryApi response = transactionService.getTransactions(COMPANY_NUMBER);
        assertNotNull(response);
        assertNull(response.getItems());
    }

    @Test
    @DisplayName("Test get transaction mapper throws a JsonMappingException")
    void testGetTransactionThrowsJsonMappingException() throws Exception {
        when(transactionRepository.getTransactionJson(COMPANY_NUMBER)).thenReturn(
                getResponseJson());
        when(objectMapper.readValue(getResponseJson(), JsonNode.class)).thenThrow(
                JsonMappingException.class);
        assertThrows(TransactionMappingException.class, () ->
                transactionService.getTransactions(COMPANY_NUMBER)
        );
    }

    @Test
    @DisplayName("Test get transaction mapper throws a JsonProcessingException")
    void testGetTransactionThrowsJsonProcessingException() throws Exception {
        when(transactionRepository.getTransactionJson(COMPANY_NUMBER)).thenReturn(
                getResponseJson());
        when(objectMapper.readValue(getResponseJson(), JsonNode.class)).thenThrow(
                JsonProcessingException.class);
        assertThrows(TransactionMappingException.class, () ->
                transactionService.getTransactions(COMPANY_NUMBER)
        );
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

    private FilingHistoryApi getFilingHistoryApi() {
        FilingHistoryApi filingHistoryApi = new FilingHistoryApi();
        filingHistoryApi.setItems(getFilingApiList());
        return filingHistoryApi;
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

    private List<FilingApi> getFilingApiList() {
        List<FilingApi> filingApiList = new ArrayList<>();
        FilingApi filingApi = new FilingApi();
        filingApi.setActionDate(LocalDate.of(2021, 8, 8));
        filingApi.setDescription("DESCRIPTION");
        filingApi.setPaperFiled(false);
        filingApi.setBarcode("AAAAAAAA");
        filingApi.setTransactionId("1234567890");
        filingApi.setType("FORM_TYPE");
        filingApiList.add(filingApi);
        return filingApiList;
    }
}
