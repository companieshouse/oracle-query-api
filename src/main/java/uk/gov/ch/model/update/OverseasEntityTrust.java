package uk.gov.ch.model.update;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OverseasEntityTrust {
    @Id
    @Column(name = "trust_id")
    @JsonProperty("trust_id")
    private String trustId;

    @Column(name = "trust_name")
    @JsonProperty("trust_name")
    private String trustName;

    @Column(name = "creation_date")
    @JsonProperty("trust_creation_date")
    private String trustCreationDate;

    @Column(name = "unable_to_obtain_all_info_ind")
    @JsonProperty("unable_to_obtain_all_info_indicator")
    private String unableToObtainAllInfoIndicator;

    public String getTrustId() {
        return trustId;
    }

    public void setTrustId(String trustId) {
        this.trustId = trustId;
    }

    public String getTrustName() {
        return trustName;
    }

    public void setTrustName(String trustName) {
        this.trustName = trustName;
    }

    public String getTrustCreationDate() {
        return trustCreationDate;
    }

    public void setTrustCreationDate(String trustCreationDate) {
        this.trustCreationDate = trustCreationDate;
    }

    public String getUnableToObtainAllInfoIndicator() {
        return unableToObtainAllInfoIndicator;
    }

    public void setUnableToObtainAllInfoIndicator(String unableToObtainAllInfoIndicator) {
        this.unableToObtainAllInfoIndicator = unableToObtainAllInfoIndicator;
    }
}
