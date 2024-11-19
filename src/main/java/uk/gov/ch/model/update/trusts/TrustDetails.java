package uk.gov.ch.model.update.trusts;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Objects;

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
    @JsonProperty("creationDate")
    private String creationDate;

    @Column(name = "ceased_date")
    @JsonProperty("ceasedDate")
    private String ceasedDate;

    @Column(name = "unable_to_obtain_all_info_ind")
    @JsonProperty("unableToObtainAllInfoIndicator")
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCeasedDate() {
        return ceasedDate;
    }

    public void setCeasedDate(String ceasedDate) {
        this.ceasedDate = ceasedDate;
    }

    public String getUnableToObtainAllInfoIndicator() {
        return unableToObtainAllInfoIndicator;
    }

    public void setUnableToObtainAllInfoIndicator(String unableToObtainAllInfoIndicator) {
        this.unableToObtainAllInfoIndicator = unableToObtainAllInfoIndicator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TrustDetails that = (TrustDetails) o;
        return Objects.equals(trustId, that.trustId)
                && Objects.equals(trustName, that.trustName)
                && Objects.equals(creationDate, that.creationDate)
                && Objects.equals(ceasedDate, that.ceasedDate)
                && Objects.equals(unableToObtainAllInfoIndicator,
                that.unableToObtainAllInfoIndicator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trustId, trustName, creationDate, ceasedDate,
                unableToObtainAllInfoIndicator);
    }
}
