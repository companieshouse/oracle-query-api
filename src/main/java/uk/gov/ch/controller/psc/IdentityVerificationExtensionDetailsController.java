package uk.gov.ch.controller.psc;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.model.psc.IdentityVerificationExtensionDetailsCriteriaDto;
import uk.gov.ch.model.psc.IdentityVerificationExtensionDetailsDto;
import uk.gov.ch.service.psc.IdentityVerificationExtensionDetailsService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

import java.text.MessageFormat;

@RestController
@ConditionalOnProperty(prefix = "feature", name = "psc_verification_details_get", havingValue = "true")
public class IdentityVerificationExtensionDetailsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    private final IdentityVerificationExtensionDetailsService identityVerificationExtensionDetailsService;

    public IdentityVerificationExtensionDetailsController(
        @Autowired final IdentityVerificationExtensionDetailsService identityVerificationExtensionDetailsService) {
        this.identityVerificationExtensionDetailsService = identityVerificationExtensionDetailsService;

    }

    /**
     * Retrieves the Identity Verification Extension request Details for a specific Individual PSC.
     *
     * @param queryCriteria Criteria of PSC extension request (the internal extension request ID)
     * @return the {@code IdentityVerificationExtensionDetailsDto} data if found
     */
    @PostMapping("/corporate-body-appointments/persons-of-significant-control/identity-verification-extension-details")
    public ResponseEntity<IdentityVerificationExtensionDetailsDto> getIdentityVerificationExtensionRequestDetails(
        @RequestBody @Valid final IdentityVerificationExtensionDetailsCriteriaDto queryCriteria) {
        LOGGER.info(MessageFormat.format(
            "Calling service to obtain PSC identity verification extension request details, for ID {0,number,#}",
            queryCriteria.extensionRequestId()));

        final var detailsDto = identityVerificationExtensionDetailsService.findExtensionRequest(
            queryCriteria.extensionRequestId());

        LOGGER.info(MessageFormat.format("Found {0,choice,0#no matches|1#one match} for appointment ID {1,number,#}",
            detailsDto.stream().count(), queryCriteria.extensionRequestId()));

        return ResponseEntity.of(detailsDto);
    }
}
