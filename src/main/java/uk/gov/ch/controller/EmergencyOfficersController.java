package uk.gov.ch.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmergencyOfficersController {

    @GetMapping("/emergency-auth-code/company/{company_number}/eligible-officers")
    public ResponseEntity getListOfEligibleCompanyOfficers(
            @PathVariable String company_number) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/emergency-auth-code/company/{company_number}/eligible-officers/{officer_id}")
    public ResponseEntity getCompanyOfficer(
            @PathVariable String company_number,
            @PathVariable String officer_id) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
