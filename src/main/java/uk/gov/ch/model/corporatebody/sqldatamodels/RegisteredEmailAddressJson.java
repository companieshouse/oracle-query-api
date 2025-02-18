package uk.gov.ch.model.corporatebody.sqldatamodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisteredEmailAddressJson {

    @JsonProperty("registered_email_address")
    private String registeredEmailAddress;

    public String getRegisteredEmailAddress() {
        return registeredEmailAddress;
    }

    public void setRegisteredEmailAddress(String registeredEmailAddress) {
        this.registeredEmailAddress = registeredEmailAddress;
    }
}