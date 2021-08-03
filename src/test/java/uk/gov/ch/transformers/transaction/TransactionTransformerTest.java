package uk.gov.ch.transformers.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

@ExtendWith(MockitoExtension.class)
class TransactionTransformerTest {

    @InjectMocks
    TransactionTransformer transactionTransformer;

    @Test
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
        assertFilingHistoryApi(filingHistoryTransaction, filingApi, TransactionCategory.INCORPORATION);
        assertNull(filingApi.getAssociatedFilings());
    }

    @Test
    @DisplayName("Transform the FilingApi with an associated filing")
    void convertFilingHistoryWithChildTransaction() {
        FilingHistoryTransaction filingHistoryTransaction = setUpFilingHistoryTransaction(true);
        FilingApi filingApi = transactionTransformer.convert(filingHistoryTransaction);
        assertFilingHistoryApi(filingHistoryTransaction, filingApi, TransactionCategory.INCORPORATION);
        List<AssociatedFilingsApi> associatedFilings = filingApi.getAssociatedFilings();
        AssociatedFilingsApi associatedFiling = associatedFilings.get(0);
        FilingHistoryTransaction childTransaction = filingHistoryTransaction.getChild().get(0);
        assertEquals(1, associatedFilings.size());
        assertEquals(childTransaction.getDescription(), associatedFiling.getDescription());
        assertEquals(childTransaction.getFormType(), associatedFiling.getType());
        assertEquals(2021, associatedFiling.getDate().getYear());
        assertEquals(1, associatedFiling.getDate().getMonth().getValue());
        assertEquals(22, associatedFiling.getDate().getDayOfMonth());
        assertEquals(TransactionCategory.CHANGE_OF_NAME.getDescription(), associatedFiling.getCategory());
    }

    private void assertFilingHistoryApi(FilingHistoryTransaction filingHistoryTransaction, FilingApi filingApi,
            TransactionCategory tc) {
        assertEquals(filingHistoryTransaction.getBarcode(), filingApi.getBarcode());
        assertEquals(filingHistoryTransaction.getDescription(), filingApi.getDescription());
        assertEquals(filingHistoryTransaction.getEntityId().toString(), filingApi.getTransactionId());
        assertEquals(tc.getDescription(), filingApi.getCategory());
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