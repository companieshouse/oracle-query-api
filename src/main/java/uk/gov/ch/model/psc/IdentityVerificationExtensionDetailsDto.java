package uk.gov.ch.model.psc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record IdentityVerificationExtensionDetailsDto(@JsonProperty("extension_request_date") LocalDate extensionRequestedDate,
                                                      @JsonProperty("previous_appointment_verification_statement_date") LocalDate previousAppointmentVerificationStatementDate,
                                                      @JsonProperty("new_appointment_verification_statement_due_on") LocalDate newAppointmentVerificationStatementDueOn) {
}


// TODO Update with IdentityVerificationExtensionDetails