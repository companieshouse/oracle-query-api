package uk.gov.ch.model.psc;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record IdentityVerificationExtensionDetailsCriteriaDto(
    @NotNull(message = "extension_request_id must not be blank/null")
    @Valid
    @JsonProperty("extension_request_id") Long extensionRequestId) {
}
