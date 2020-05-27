package uk.gov.ch.controller.emergencyauthcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyAppointment;
import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyAppointments;
import uk.gov.ch.service.emergencyauthcode.EmergencyOfficersService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@RestController
public class EmergencyOfficersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private EmergencyOfficersService emergencyOfficersService;

    @GetMapping("/emergency-auth-code/company/{incorporationNumber}/eligible-officers")
    public ResponseEntity getListOfEligibleCompanyOfficers(
            @PathVariable String incorporationNumber) {
        LOGGER.info("Calling service to retrieve eligible officers for company number " + incorporationNumber);
        CorporateBodyAppointments eligibleOfficers = emergencyOfficersService.getEligibleOfficersEmergencyAuthCode(incorporationNumber);

        if (eligibleOfficers == null || eligibleOfficers.getItems().isEmpty()) {
            LOGGER.info("No company directors found for company number " + incorporationNumber);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        LOGGER.info("Returning " + eligibleOfficers.getTotalResults() + " eligible officers for company " + incorporationNumber);
        return ResponseEntity.status(HttpStatus.OK).body(eligibleOfficers);
    }

    @GetMapping("/emergency-auth-code/company/{companyNumber}/eligible-officers/{officerId}")
    public ResponseEntity getCompanyOfficer(
            @PathVariable String companyNumber,
            @PathVariable String officerId) {

        LOGGER.info("Calling service to retrieve officer " + officerId + " for company number " + companyNumber);
        CorporateBodyAppointment eligibleOfficer = emergencyOfficersService.getEligibleOfficer(companyNumber, officerId);

        if (eligibleOfficer == null) {
            LOGGER.info("No valid company director found for officer_id " + officerId + " on company number " + companyNumber);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        LOGGER.info("Returning details for officer " + officerId + " on company " + companyNumber);
        return ResponseEntity.status(HttpStatus.OK).body(eligibleOfficer);
    }
}
