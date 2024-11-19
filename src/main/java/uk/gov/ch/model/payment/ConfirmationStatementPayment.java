package uk.gov.ch.model.payment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

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
