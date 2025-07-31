package uk.gov.ch.model.psc;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class IdentityVerificationDetails {
    @Id
    @Column(name = "CORPORATE_BODY_APPOINTMENT_ID")
    private Long corporateBodyAppointmentId;
    @Column(name = "APPT_VERIFICATION_EFF_DATE")
    private LocalDate appointmentVerificationStartOn;
    @Column(name = "APPT_VERIFICATION_END_DATE")
    private LocalDate appointmentVerificationEndOn;
    @Column(name = "VERIFICATION_STMT_DATE")
    private LocalDate appointmentVerificationStatementDate;
    @Column(name = "VERIFICATION_STMT_DUE_DATE")
    private LocalDate appointmentVerificationStatementDueDate;

    public Long getCorporateBodyAppointmentId() {
        return corporateBodyAppointmentId;
    }

    public void setCorporateBodyAppointmentId(final Long corporateBodyAppointmentId) {
        this.corporateBodyAppointmentId = corporateBodyAppointmentId;
    }

    public LocalDate getAppointmentVerificationStartOn() {
        return appointmentVerificationStartOn;
    }

    public void setAppointmentVerificationStartOn(final LocalDate appointmentVerificationStartOn) {
        this.appointmentVerificationStartOn = appointmentVerificationStartOn;
    }

    public LocalDate getAppointmentVerificationEndOn() {
        return appointmentVerificationEndOn;
    }

    public void setAppointmentVerificationEndOn(final LocalDate appointmentVerificationEndOn) {
        this.appointmentVerificationEndOn = appointmentVerificationEndOn;
    }

    public LocalDate getAppointmentVerificationStatementDate() {
        return appointmentVerificationStatementDate;
    }

    public void setAppointmentVerificationStatementDate(final LocalDate appointmentVerificationStatementDate) {
        this.appointmentVerificationStatementDate = appointmentVerificationStatementDate;
    }

    public LocalDate getAppointmentVerificationStatementDueDate() {
        return appointmentVerificationStatementDueDate;
    }

    public void setAppointmentVerificationStatementDueDate(final LocalDate appointmentVerificationStatementDueDate) {
        this.appointmentVerificationStatementDueDate = appointmentVerificationStatementDueDate;
    }
}

