package uk.gov.ch.controller.officer.bankrupt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerDetails;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearch;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchResults;
import uk.gov.ch.service.officer.bankrupt.impl.ScottishBankruptOfficerService;

@Controller
public class ScottishBankruptOfficerController {

    @Autowired
    private ScottishBankruptOfficerService scottishBankruptOfficerService;

    @PostMapping("/officer-search/scottish-bankrupt-officers")
        public ResponseEntity<ScottishBankruptOfficerSearchResults> search(@RequestBody ScottishBankruptOfficerSearch scottishBankruptOfficerSearch){
       ScottishBankruptOfficerSearchResults results =   scottishBankruptOfficerService.getScottishBankruptOfficers(scottishBankruptOfficerSearch);
       if (results.getItems().isEmpty()){
           return new ResponseEntity(HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity(results, HttpStatus.OK);
    }

    @GetMapping("/officer-search/scottish-bankrupt-officers/{ephemeral_officer_key}")
    public ResponseEntity<ScottishBankruptOfficerDetails> getOfficerById(@PathVariable("ephemeral_officer_key") String ephemeralId){
        ScottishBankruptOfficerDetails officer = scottishBankruptOfficerService.getScottishBankruptOfficer(ephemeralId);
        if (officer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(officer);
    }


}
