package uk.gov.ch.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfirmationStatementPaymentJson {

    @JsonProperty("paid")
    private Boolean isPaid;

    public Boolean isPaid() {
        return isPaid;
    }

    public void setPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }
}
