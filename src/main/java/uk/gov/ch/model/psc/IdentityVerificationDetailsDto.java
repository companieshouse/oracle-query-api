package uk.gov.ch.model.psc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record IdentityVerificationDetailsDto(@JsonProperty("appointment_verification_start_on") LocalDate appointmentVerificationStartOn,
    @JsonProperty("appointment_verification_end_on") LocalDate appointmentVerificationEndOn,
    @JsonProperty("appointment_verification_statement_date") LocalDate appointmentVerificationStatementDate,
    @JsonProperty("appointment_verification_statement_due_date") LocalDate appointmentVerificationStatementDueDate) {
}
