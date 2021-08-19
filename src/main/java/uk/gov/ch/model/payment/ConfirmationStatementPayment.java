package uk.gov.ch.model.payment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConfirmationStatementPayment {
    @Id
    @Column(name = "PAID_BY_TRANSACTION_ID")
    private Long paidByTransactionId;

    public Long getPaidByTransactionId() {
        return paidByTransactionId;
    }

    public void setPaidByTransactionId(Long paidByTransactionId) {
        this.paidByTransactionId = paidByTransactionId;
    }
}
