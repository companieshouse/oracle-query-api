package uk.gov.ch.model.update.trusts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TrustLinkData {

    @Column(name = "TRUST_ID")
    @JsonProperty("trustId")
    private Long trustId;

    @Id
    @Column(name = "CORPORATE_BODY_APPOINTMENT_ID")
    @JsonProperty("corporateBodyAppointmentId")
    private Long corporateBodyAppointmentId;


    public Long getTrustId() {
        return trustId;
    }

    public void setTrustId(Long trustId) {
        this.trustId = trustId;
    }

    public Long getCorporateBodyAppointmentId() {
        return corporateBodyAppointmentId;
    }

    public void setCorporateBodyAppointmentId(Long corporateBodyAppointmentId) {
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
