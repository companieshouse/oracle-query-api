package uk.gov.ch.model.update;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OverseasEntityData {

    @Id
    @Column(name = "corporate_body_id")
    @JsonIgnore
    private Long corporateBodyId;

    @Column(name = "email_address")
    private String emailAddress;

    public Long getCorporateBodyId() {
        return corporateBodyId;
    }

    public void setCorporateBodyId(Long corporateBodyId) {
        this.corporateBodyId = corporateBodyId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}