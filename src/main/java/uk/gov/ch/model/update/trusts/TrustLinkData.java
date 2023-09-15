package uk.gov.ch.model.update.trusts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import javax.persistence.Column;

public class TrustLinkData {

    @Column(name = "TRUST_ID")
    @JsonProperty("trusteeId")
    private Long trusteeId;

    @Column(name = "CORPORATE_BODY_APPOINTMENT_ID")
    @JsonProperty("corporateBodyAppointmentId")
    private Long corporateBodyAppointmentId;


    public Long getTrusteeId() {
        return trusteeId;
    }

    public void setTrusteeId(Long trusteeId) {
        this.trusteeId = trusteeId;
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
        return Objects.equals(trusteeId, that.trusteeId) && Objects.equals(
                corporateBodyAppointmentId, that.corporateBodyAppointmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trusteeId, corporateBodyAppointmentId);
    }
}
