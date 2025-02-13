package uk.gov.ch.model.psc;

import java.time.LocalDate;

public record AppointmentVerificationStateDto(VerificationStatusType verificationStatusType,
    LocalDate verificationStartDate,
    LocalDate verificationStatementDueDate) {
}
