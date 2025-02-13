package uk.gov.ch.model.psc;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class AppointmentVerificationState {
    @Id
    @Column(name="CORPORATE_BODY_APPOINTMENT_ID")
    private Long corporateBodyAppointmentId;
    @Column(name="APPT_VERIF_STATUS_TYPE_ID")
    private Long verificationStatusType;
    @Column(name="IDV_START_DATE")
    private LocalDate verificationStartDate;
    @Column(name="IDV_VERIFI_STATEMENT_DUE_DATE")
    private LocalDate verificationStatementDueDate;

    public Long getCorporateBodyAppointmentId() {
        return corporateBodyAppointmentId;
    }

    public void setCorporateBodyAppointmentId(final Long corporateBodyAppointmentId) {
        this.corporateBodyAppointmentId = corporateBodyAppointmentId;
    }

    public Long getVerificationStatusType() {
        return verificationStatusType;
    }

    public void setVerificationStatusType(final Long verificationStatusType) {
        this.verificationStatusType = verificationStatusType;
    }

    public LocalDate getVerificationStartDate() {
        return verificationStartDate;
    }

    public void setVerificationStartDate(final LocalDate verificationStartDate) {
        this.verificationStartDate = verificationStartDate;
    }

    public LocalDate getVerificationStatementDueDate() {
        return verificationStatementDueDate;
    }

    public void setVerificationStatementDueDate(final LocalDate verificationStatementDueDate) {
        this.verificationStatementDueDate = verificationStatementDueDate;
    }
}
