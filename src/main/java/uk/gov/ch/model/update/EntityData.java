package uk.gov.ch.model.update;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EntityData {

    @Id
    @Column(name = "incorporation_number")
    private String incorporationNumber;

    @Column(name = "email_address")
    private String emailAddress;

    public String getIncorporationNumber() {
        return incorporationNumber;
    }

    public void setIncorporationNumber(String incorporationNumber) {
        this.incorporationNumber = incorporationNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}