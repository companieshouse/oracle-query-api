package uk.gov.ch.model.psc;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record VerificationStateCriteriaDto(
    @NotNull(message = "appointment_id must not be blank/null")
    @Valid
    @JsonProperty("appointment_id") Long appointmentId) {
}
