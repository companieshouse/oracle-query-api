package uk.gov.ch.controller.psc;

import jakarta.validation.Valid;
import java.text.MessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.model.psc.IdentityVerificationDetailsCriteriaDto;
import uk.gov.ch.model.psc.IdentityVerificationDetailsDto;
import uk.gov.ch.service.psc.IdentityVerificationDetailsService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@RestController
@ConditionalOnProperty(prefix = "feature", name = "psc_verification_details_get", havingValue = "true")
public class IdentityVerificationDetailsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    private final IdentityVerificationDetailsService identityVerificationDetailsService;

    public IdentityVerificationDetailsController(
        @Autowired final IdentityVerificationDetailsService identityVerificationDetailsService) {
        this.identityVerificationDetailsService = identityVerificationDetailsService;

    }


    /**
     * Retrieves the Identity Verification Details for a specific Individual PSC appointment.
     * <p>
     * NOTE: HTTP POST request with a request body containing the appointment ID is needed to prevent exposure of the
     * sensitive internal appointment ID in a path or query parameter.
     * </p>
     *
     * @param queryCriteria Criteria of PSC appointment (the internal appointment ID)
     * @return the {@code IdentityVerificationDetailsDto} data if found
     */
    @PostMapping("/corporate-body-appointments/persons-of-significant-control/identity-verification-details")
    public ResponseEntity<IdentityVerificationDetailsDto> getIdentityVerificationDetails(
        @RequestBody @Valid final IdentityVerificationDetailsCriteriaDto queryCriteria) {
        LOGGER.info(MessageFormat.format(
            "Calling service to obtain PSC identity verification details, for ID {0,number,#}",
            queryCriteria.appointmentId()));

        final var detailsDto = identityVerificationDetailsService.findIdentityVerificationDetails(
            queryCriteria.appointmentId());

        LOGGER.info(MessageFormat.format("Found {0,choice,0#no matches|1#one match} for appointment ID {1,number,#}",
            detailsDto.stream().count(), queryCriteria.appointmentId()));

        return ResponseEntity.of(detailsDto);
    }
}
