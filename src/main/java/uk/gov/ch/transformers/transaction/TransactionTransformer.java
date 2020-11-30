package uk.gov.ch.transformers.transaction;

import org.springframework.stereotype.Component;
import uk.gov.ch.model.transaction.jsondatamodels.Gaz2Transaction;
import uk.gov.ch.model.transaction.sqldatamodels.Gaz2TransactionDataModel;

@Component
public class TransactionTransformer {

    public Gaz2Transaction convert(Gaz2TransactionDataModel gaz2TransactionDataModel) {
        Gaz2Transaction gaz2Transaction = new Gaz2Transaction();
        gaz2Transaction.setId(gaz2TransactionDataModel.getTransactionId().toString());
        gaz2Transaction.setStatusType(gaz2TransactionDataModel.getTransactionStatusTypeId().toString());
        gaz2Transaction.setType(gaz2TransactionDataModel.getTransactionTypeId().toString());

        return gaz2Transaction;
    }
}
