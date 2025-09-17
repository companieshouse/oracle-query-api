package uk.gov.ch.model.psc;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class IdentityVerificationExtensionDetails {
    @Id
    @Column(name = "CORPORATE_BODY_APPOINTMENT_ID")
    private Long corporateBodyAppointmentId;

    @Column(name = "CHS_USER_ID")
    private Long chsUserId;

    @Column(name = "EXTENSION_REQUEST_DATE")
    private LocalDate extensionRequestedDate;

    @Column(name = "EXTENSION_REASON_TYPE_DESCRIPT")
    private String extensionReasonDescription;

    @Column(name = "VERIFICATION_STMT_DUE_DATE_OLD")
    private LocalDate previousAppointmentVerificationStatementDate;

    @Column(name = "VERIFICATION_STMT_DUE_DATE_NEW")
    private LocalDate newAppointmentVerificationStatementDueOn;

    public Long getCorporateBodyAppointmentId() {
        return corporateBodyAppointmentId;
    }

    public void setCorporateBodyAppointmentId(Long appointmentId) {
        this.corporateBodyAppointmentId = appointmentId;
    }

    public Long getChsUserId() {
        return chsUserId;
    }

    public void setChsUserId(Long chsUserId) {
        this.chsUserId = chsUserId;
    }

    public LocalDate getExtensionRequestedDate() {
        return extensionRequestedDate;
    }

    public void setExtensionRequestedDate(LocalDate extensionRequestedDate) {
        this.extensionRequestedDate = extensionRequestedDate;
    }

    public LocalDate getPreviousAppointmentVerificationStatementDate() {
        return previousAppointmentVerificationStatementDate;
    }

    public void setPreviousAppointmentVerificationStatementDate(LocalDate previousAppointmentVerificationStatementDate) {
        this.previousAppointmentVerificationStatementDate = previousAppointmentVerificationStatementDate;
    }

    public LocalDate getNewAppointmentVerificationStatementDueOn() {
        return newAppointmentVerificationStatementDueOn;
    }

    public void setNewAppointmentVerificationStatementDueOn(LocalDate newAppointmentVerificationStatementDueOn) {
        this.newAppointmentVerificationStatementDueOn = newAppointmentVerificationStatementDueOn;
    }

    public String getExtensionReasonDescription() {
        return extensionReasonDescription;
    }

    public void setExtensionReasonDescription(String extensionReasonDescription) {
        this.extensionReasonDescription = extensionReasonDescription;
    }
}

