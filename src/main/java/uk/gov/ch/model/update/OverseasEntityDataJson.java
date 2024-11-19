package uk.gov.ch.model.update;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OverseasEntityDataJson {

    @JsonProperty("email_address")
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}