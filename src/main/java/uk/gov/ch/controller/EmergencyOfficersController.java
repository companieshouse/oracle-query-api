package uk.gov.ch.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmergencyOfficersController {

    @GetMapping("/emergency-auth-code/company/{companyNumber}/eligible-officers")
    public ResponseEntity getListOfEligibleCompanyOfficers(
            @PathVariable String companyNumber) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/emergency-auth-code/company/{companyNumber}/eligible-officers/{officerId}")
    public ResponseEntity getCompanyOfficer(
            @PathVariable String companyNumber,
            @PathVariable String officerId) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
