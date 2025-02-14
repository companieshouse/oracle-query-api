package uk.gov.ch.model.psc;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public record AppointmentVerificationStateDto(@JsonProperty(
    "verification_status") VerificationStatusType verificationStatusType,
    @JsonProperty("verification_start_date") LocalDate verificationStartDate,
    @JsonProperty("verification_statement_due_date") LocalDate verificationStatementDueDate) {
}
