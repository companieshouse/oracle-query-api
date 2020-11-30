package uk.gov.ch.transformers.transaction;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.ch.model.transaction.jsondatamodels.Gaz2Transaction;
import uk.gov.ch.model.transaction.sqldatamodels.Gaz2TransactionDataModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}