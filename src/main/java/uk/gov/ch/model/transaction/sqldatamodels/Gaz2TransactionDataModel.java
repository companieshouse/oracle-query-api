package uk.gov.ch.model.transaction.sqldatamodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION")
public class Gaz2TransactionDataModel {

    @Id
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;

    @Column(name = "TRANSACTION_TYPE_ID")
    private Long transactionTypeId;

    @Column(name = "TRANSACTION_STATUS_TYPE_ID")
    private Long transactionStatusTypeId;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(Long transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public Long getTransactionStatusTypeId() {
        return transactionStatusTypeId;
    }

    public void setTransactionStatusTypeId(Long transactionStatusTypeId) {
        this.transactionStatusTypeId = transactionStatusTypeId;
    }
}
