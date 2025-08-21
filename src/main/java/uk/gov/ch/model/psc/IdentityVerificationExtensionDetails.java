package uk.gov.ch.model.psc;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class IdentityVerificationExtensionDetails {
    @Id
    @Column(name = "EXTENSION_REQUEST_ID")
    private Long extensionRequestId;
    @Column(name = "APPT_VERIFICATION_PERIOD_ID")
    private Long appointmentVerificationId;
    @Column(name = "EXTENSION_STATUS_TYPE_ID")
    private Long extensionTypeId;
    @Column(name = "EXTENSION_REASON_TYPE_ID")
    private Long extensionReasonId;
    @Column(name = "CHS_USER_ID")
    private Long chsUserId;
    @Column(name = "MODIFICATION_ID")
    private Long modificationId;
    @Column(name = "EXTENSION_REQUEST_DATE")
    private LocalDate extensionRequestedDate;
    @Column(name = "VERIFICATION_STMT_DUE_DATE_OLD")
    private LocalDate previousAppointmentVerificationStatementDate;
    @Column(name = "VERIFICATION_STMT_DUE_DATE_NEW")
    private LocalDate newAppointmentVerificationStatementDueOn;

    public Long getExtensionReasonId() {
        return extensionReasonId;
    }

    public void setExtensionReasonId(Long extensionReasonId) {
        this.extensionReasonId = extensionReasonId;
    }

    public Long getAppointmentVerificationId() {
        return appointmentVerificationId;
    }

    public void setAppointmentVerificationId(Long appointmentVerificationId) {
        this.appointmentVerificationId = appointmentVerificationId;
    }

    public Long getExtensionRequestId() {
        return extensionRequestId;
    }

    public void setExtensionRequestId(Long extensionRequestId) {
        this.extensionRequestId = extensionRequestId;
    }

    public Long getExtensionTypeId() {
        return extensionTypeId;
    }

    public void setExtensionTypeId(Long extensionTypeId) {
        this.extensionTypeId = extensionTypeId;
    }

    public Long getChsUserId() {
        return chsUserId;
    }

    public void setChsUserId(Long chsUserId) {
        this.chsUserId = chsUserId;
    }

    public Long getModificationId() {
        return modificationId;
    }

    public void setModificationId(Long modificationId) {
        this.modificationId = modificationId;
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
}

