package uk.gov.ch.model.corporatebody.sqldatamodels;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class RegisteredEmailAddress {

    @Column(name = "registered_email_address")
    private String emailAddress;

    public String getRegisteredEmailAddress() {
        return emailAddress;
    }

    public void setRegisteredEmailAddress(String registeredEmailAddress) {
        this.emailAddress = registeredEmailAddress;
    }
}