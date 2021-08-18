package uk.gov.ch.transformers.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import uk.gov.ch.model.transaction.jsondatamodels.FilingHistoryTransaction;
import uk.gov.ch.model.transaction.jsondatamodels.Gaz2Transaction;
import uk.gov.ch.model.transaction.sqldatamodels.Gaz2TransactionDataModel;
import uk.gov.companieshouse.api.model.filinghistory.AssociatedFilingsApi;
import uk.gov.companieshouse.api.model.filinghistory.FilingApi;
import uk.gov.companieshouse.api.model.filinghistory.FilingHistoryApi;

@ExtendWith(MockitoExtension.class)
class TransactionTransformerTest {

    @InjectMocks
    TransactionTransformer transactionTransformer;

    @Test
    @DisplayName("Test the successful conversion of a Gaz2TransactionDataModel to Gaz2Transaction")
    void convertTest() {
        Gaz2TransactionDataModel gaz2TransactionDataModel = new Gaz2TransactionDataModel();
        gaz2TransactionDataModel.setTransactionId(1234L);
        gaz2TransactionDataModel.setTransactionStatusTypeId(5678L);
        gaz2TransactionDataModel.setTransactionTypeId(4321L);

        Gaz2Transaction gaz2Transaction = transactionTransformer.convert(gaz2TransactionDataModel);

        assertEquals(gaz2TransactionDataModel.getTransactionId().toString(), gaz2Transaction.getId());
        assertEquals(gaz2TransactionDataModel.getTransactionStatusTypeId().toString(), gaz2Transaction.getStatusType());
        assertEquals(gaz2TransactionDataModel.getTransactionTypeId().toString(), gaz2Transaction.getType());
    }

    @Test
    @DisplayName("Transform a filing api with no associated filing")
    void convertFilingHistoryTestWithoutChildTransaction() {
        FilingHistoryTransaction filingHistoryTransaction = setUpFilingHistoryTransaction(false);
        FilingApi filingApi = transactionTransformer.convert(filingHistoryTransaction);
        assertFilingHistoryApi(filingHistoryTransaction, filingApi);
        assertNull(filingApi.getAssociatedFilings());
    }

    @Test
    @DisplayName("Transform the FilingApi with an associated filing")
    void convertFilingHistoryWithChildTransaction() {
        FilingHistoryTransaction filingHistoryTransaction = setUpFilingHistoryTransaction(true);
        FilingApi filingApi = transactionTransformer.convert(filingHistoryTransaction);
        assertFilingHistoryApi(filingHistoryTransaction, filingApi);
        List<AssociatedFilingsApi> associatedFilings = filingApi.getAssociatedFilings();
        AssociatedFilingsApi associatedFiling = associatedFilings.get(0);
        FilingHistoryTransaction childTransaction = filingHistoryTransaction.getChild().get(0);
        assertEquals(1, associatedFilings.size());
        assertEquals(childTransaction.getDescription(), associatedFiling.getDescription());
        assertEquals(childTransaction.getFormType(), associatedFiling.getType());
        assertEquals(2021, associatedFiling.getDate().getYear());
        assertEquals(1, associatedFiling.getDate().getMonth().getValue());
        assertEquals(22, associatedFiling.getDate().getDayOfMonth());
    }
    
    @Test
    @DisplayName("Transform the where the Barcode is null defaults to paper_filed false")
    void convertFilingHistoryWithNullBarcode() {
        FilingHistoryTransaction filingHistoryTransaction = setUpFilingHistoryTransaction(false);
        filingHistoryTransaction.setBarcode(null);
        FilingApi filingApi = transactionTransformer.convert(filingHistoryTransaction);
        assertFilingHistoryApi(filingHistoryTransaction, filingApi);
        assertTrue(filingApi.isPaperFiled());
    }
    
    @Test
    @DisplayName("Transform the FilingApi when barcode and document id indicate an electronic filing so paper_filed is false")
    void convertFilingHistoryWithAnElectronicFiling() {
        FilingHistoryTransaction filingHistoryTransaction = setUpFilingHistoryTransaction(false);
        filingHistoryTransaction.setBarcode("X1234567");
        filingHistoryTransaction.setDocumentId("000X1234567ABCD");
        FilingApi filingApi = transactionTransformer.convert(filingHistoryTransaction);
        assertFilingHistoryApi(filingHistoryTransaction, filingApi);
        assertFalse(filingApi.isPaperFiled());
    }
    
    @Test
    @DisplayName("Transform the FilingApi when barcode could be electronic filed but document id cannot")
    void convertFilingHistoryWithElectronicFiledBarcodeReturnsPaperFiled() {
        FilingHistoryTransaction filingHistoryTransaction = setUpFilingHistoryTransaction(false);
        filingHistoryTransaction.setBarcode("X1234567");
        filingHistoryTransaction.setDocumentId("000A1234567ABCD");
        FilingApi filingApi = transactionTransformer.convert(filingHistoryTransaction);
        assertFilingHistoryApi(filingHistoryTransaction, filingApi);
        assertFalse(filingApi.isPaperFiled());
    }
    
    @Test
    @DisplayName("Transform the FilingApi when document id could be electronic filed but barcode cannot")
    void convertFilingHistoryWithElectronicFiledDocumentIdReturnsPaperFiled() {
        FilingHistoryTransaction filingHistoryTransaction = setUpFilingHistoryTransaction(false);
        filingHistoryTransaction.setBarcode("A1234567");
        filingHistoryTransaction.setDocumentId("000X1234567ABCD");
        FilingApi filingApi = transactionTransformer.convert(filingHistoryTransaction);
        assertFilingHistoryApi(filingHistoryTransaction, filingApi);
        assertFalse(filingApi.isPaperFiled());
    }
    
    @Test
    @DisplayName("Transform the FilingApi when barcode and document id indicate paper filed")
    void convertFilingHistoryWithPaperFiledBarcodeAndDocumentIdReturnsPaperFiled() {
        FilingHistoryTransaction filingHistoryTransaction = setUpFilingHistoryTransaction(false);
        FilingApi filingApi = transactionTransformer.convert(filingHistoryTransaction);
        assertFilingHistoryApi(filingHistoryTransaction, filingApi);
        assertTrue(filingApi.isPaperFiled());
    }
    
    @Test
    @DisplayName("Transform a list of filing transactions to return a completed FilingHistoryApi object")
    void convertFilingHistoryWithListOfFilingTransactions() {
    	List<FilingHistoryTransaction> filingHistoryTransactionList = new ArrayList<>();
    	filingHistoryTransactionList.add(setUpFilingHistoryTransaction(false));
    	filingHistoryTransactionList.add(setUpFilingHistoryTransaction(false));
    	FilingHistoryApi filingHistoryApi = transactionTransformer.convertToFilingHistoryApi(filingHistoryTransactionList);
    	assertEquals("filing-history-available", filingHistoryApi.getFilingHistoryStatus());
    	assertEquals(filingHistoryTransactionList.size(), filingHistoryApi.getItems().size());
    	assertEquals(filingHistoryTransactionList.size(), filingHistoryApi.getItemsPerPage());
    	assertEquals(0l, filingHistoryApi.getStartIndex());
    	assertEquals(filingHistoryTransactionList.size(), filingHistoryApi.getTotalCount());
    	assertEquals("filing-history", filingHistoryApi.getKind());
    }
    
    @Test
    @DisplayName("Transform an empty list of filing transactions to return a completed FilingHistoryApi object")
    void convertFilingHistoryWithEmptyListOfFilingTransactions() {
    	List<FilingHistoryTransaction> filingHistoryTransactionList = new ArrayList<>();
    	FilingHistoryApi filingHistoryApi = transactionTransformer.convertToFilingHistoryApi(filingHistoryTransactionList);
    	assertEquals("filing-history-unavailable", filingHistoryApi.getFilingHistoryStatus());
    	assertEquals(0l, filingHistoryApi.getItems().size());
    	assertEquals(0l, filingHistoryApi.getItemsPerPage());
    	assertEquals(0l, filingHistoryApi.getStartIndex());
    	assertEquals(0l, filingHistoryApi.getTotalCount());
    	assertEquals("filing-history", filingHistoryApi.getKind());
    }
    
    @Test
    @DisplayName("Transform an null to return a completed FilingHistoryApi object")
    void convertFilingHistoryWithNullListOfFilingTransactions() {
    	FilingHistoryApi filingHistoryApi = transactionTransformer.convertToFilingHistoryApi(null);
    	assertEquals("filing-history-unavailable", filingHistoryApi.getFilingHistoryStatus());
    	assertEquals(0l, filingHistoryApi.getItems().size());
    	assertEquals(0l, filingHistoryApi.getItemsPerPage());
    	assertEquals(0l, filingHistoryApi.getStartIndex());
    	assertEquals(0l, filingHistoryApi.getTotalCount());
    	assertEquals("filing-history", filingHistoryApi.getKind());
    }

    private void assertFilingHistoryApi(FilingHistoryTransaction filingHistoryTransaction, FilingApi filingApi) {
        assertEquals(filingHistoryTransaction.getFormType(), filingApi.getType());
        assertEquals(filingHistoryTransaction.getDescription(), filingApi.getDescription());
        assertEquals(2021, filingApi.getActionDate().getYear());
        assertEquals(1, filingApi.getActionDate().getMonth().getValue());
        assertEquals(22, filingApi.getActionDate().getDayOfMonth());
    }

    private FilingHistoryTransaction setUpFilingHistoryTransaction(boolean includeChild) {
        // date string of 2021year 1month 22day 16h 15min 14sec
        String receiveDate = "20210122161514";
        FilingHistoryTransaction parentTransaction = new FilingHistoryTransaction();
        parentTransaction.setBarcode("AAAAAAAAAA");
        parentTransaction.setCategory("6");
        parentTransaction.setDescription("A description");
        parentTransaction.setDocumentId("1234567890");
        parentTransaction.setEntityId(1234567890L);
        parentTransaction.setFormType("FORM TYPE");
        parentTransaction.setReceiveDate(receiveDate);
        if (includeChild) {
            FilingHistoryTransaction childTransaction = new FilingHistoryTransaction();
            childTransaction.setBarcode("BBBBBBBB");
            childTransaction.setCategory("7");
            childTransaction.setDescription("Child description");
            childTransaction.setDocumentId("2345678901");
            childTransaction.setEntityId(2345678901L);
            childTransaction.setFormType("CHILD");
            childTransaction.setReceiveDate(receiveDate);
            List<FilingHistoryTransaction> childTransactions = new ArrayList<>();
            childTransactions.add(childTransaction);
            parentTransaction.setChild(childTransactions);
        }
        return parentTransaction;
    }

}