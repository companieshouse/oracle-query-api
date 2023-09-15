package uk.gov.ch.model.update.trusts;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TrustDetails {
    @Id
    @Column(name = "trust_id")
    @JsonProperty("trustId")
    private String trustId;

    @Column(name = "trust_name")
    @JsonProperty("trustName")
    private String trustName;

    @Column(name = "creation_date")
    @JsonProperty("trustCreationDate")
    private String trustCreationDate;

    @Column(name = "ceased_date")
    @JsonProperty("ceasedDate")
    private String ceasedDate;

    @Column(name = "unable_to_obtain_all_info_ind")
    @JsonProperty("unableToObtainAllInfoInd")
    private String unableToObtainAllInfoInd;

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

    public String getCeasedDate() {
        return ceasedDate;
    }

    public void setCeasedDate(String ceasedDate) {
        this.ceasedDate = ceasedDate;
    }

    public String getUnableToObtainAllInfoInd() {
        return unableToObtainAllInfoInd;
    }

    public void setUnableToObtainAllInfoInd(String unableToObtainAllInfoInd) {
        this.unableToObtainAllInfoInd = unableToObtainAllInfoInd;
    }
}
