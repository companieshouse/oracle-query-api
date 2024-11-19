package uk.gov.ch.model.update.trusts;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class TrustLinkData {

    @Id
    @Column(name = "ROWNUM")
    private Long Id;

    @Column(name = "TRUST_ID")
    @JsonProperty("trustId")
    private String trustId;


    @Column(name = "CORPORATE_BODY_APPOINTMENT_ID")
    @JsonProperty("corporateBodyAppointmentId")
    private String corporateBodyAppointmentId;


    public String getTrustId() {
        return trustId;
    }

    public void setTrustId(String trustId) {
        this.trustId = trustId;
    }

    public String getCorporateBodyAppointmentId() {
        return corporateBodyAppointmentId;
    }

    public void setCorporateBodyAppointmentId(String corporateBodyAppointmentId) {
        this.corporateBodyAppointmentId = corporateBodyAppointmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TrustLinkData that = (TrustLinkData) o;
        return Objects.equals(trustId, that.trustId) && Objects.equals(
                corporateBodyAppointmentId, that.corporateBodyAppointmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trustId, corporateBodyAppointmentId);
    }
}
