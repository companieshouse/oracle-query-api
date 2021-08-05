package uk.gov.ch.transformers.transaction;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import uk.gov.ch.model.transaction.jsondatamodels.FilingHistoryTransaction;
import uk.gov.ch.model.transaction.jsondatamodels.Gaz2Transaction;
import uk.gov.ch.model.transaction.sqldatamodels.Gaz2TransactionDataModel;
import uk.gov.companieshouse.api.model.filinghistory.AssociatedFilingsApi;
import uk.gov.companieshouse.api.model.filinghistory.FilingApi;

@Component
public class TransactionTransformer {

    public Gaz2Transaction convert(Gaz2TransactionDataModel gaz2TransactionDataModel) {
        Gaz2Transaction gaz2Transaction = new Gaz2Transaction();
        gaz2Transaction.setId(gaz2TransactionDataModel.getTransactionId().toString());
        gaz2Transaction.setStatusType(gaz2TransactionDataModel.getTransactionStatusTypeId().toString());
        gaz2Transaction.setType(gaz2TransactionDataModel.getTransactionTypeId().toString());

        return gaz2Transaction;
    }

    public FilingApi convert(FilingHistoryTransaction filingHistoryTransaction) {
        FilingApi filingApi = new FilingApi();
        filingApi.setDescription(filingHistoryTransaction.getDescription());
        filingApi.setType(filingHistoryTransaction.getFormType());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDate receivedDate = LocalDate.parse(filingHistoryTransaction.getReceiveDate(), dateTimeFormatter);
        filingApi.setActionDate(receivedDate);
        // if the barcode starts with an X OR the 4th character in the document id is an X then it is
        // electronically filed and paper filed is false
        if (filingHistoryTransaction.getBarcode() != null && (filingHistoryTransaction.getBarcode().startsWith("X")
                || (filingHistoryTransaction.getDocumentId().charAt(3) == 'X'))) {
            filingApi.setPaperFiled(false);
        } else {
            filingApi.setPaperFiled(true);
        }
        if (filingHistoryTransaction.getChild() != null) {
            List<AssociatedFilingsApi> associatedFilings = new ArrayList<>();
            for (FilingHistoryTransaction fht : filingHistoryTransaction.getChild()) {
                AssociatedFilingsApi associatedFiling = new AssociatedFilingsApi();
                associatedFiling.setDescription(fht.getDescription());
                associatedFiling.setType(fht.getFormType());
                LocalDate actionDate = LocalDate.parse(fht.getReceiveDate(), dateTimeFormatter);
                associatedFiling.setDate(actionDate);
                associatedFilings.add(associatedFiling);
            }
            filingApi.setAssociatedFilings(associatedFilings);
        }
        return filingApi;
    }
}
