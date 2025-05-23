package uk.gov.ch.model.corporatebody.sqldatamodels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CorporateBodyDetails {

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