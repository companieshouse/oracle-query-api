package uk.gov.ch.corporatebody.controller.officer.bankrupt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.ch.controller.officer.bankrupt.ScottishBankruptOfficerController;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearch;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchResult;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchResults;
import uk.gov.ch.service.officer.bankrupt.impl.ScottishBankruptOfficerService;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ScottishBankruptOfficerControllerTest {

    @Mock
   private ScottishBankruptOfficerService service;

    @InjectMocks
    private ScottishBankruptOfficerController controller;



    @Test
    @DisplayName("No officer found")
    public void testNoOfficerFound(){
        ScottishBankruptOfficerSearch search = new ScottishBankruptOfficerSearch();
        ScottishBankruptOfficerSearchResults results = new ScottishBankruptOfficerSearchResults();
        results.setItems(new ArrayList<>());
        when(service.getScottishBankruptOfficers(search)).thenReturn(results);

        ResponseEntity<ScottishBankruptOfficerSearchResults> response = controller.search(search);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Officer found")
    public void testOfficerFound(){
        ScottishBankruptOfficerSearch search = new ScottishBankruptOfficerSearch();
        ScottishBankruptOfficerSearchResults results = new ScottishBankruptOfficerSearchResults();
        ArrayList<ScottishBankruptOfficerSearchResult> listOfOfficers = new ArrayList<>();
        ScottishBankruptOfficerSearchResult officer = new ScottishBankruptOfficerSearchResult();
        listOfOfficers.add(officer);
        results.setItems(listOfOfficers);
        when(service.getScottishBankruptOfficers(search)).thenReturn(results);

        ResponseEntity<ScottishBankruptOfficerSearchResults> response = controller.search(search);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(results);
        assertNotNull(results.getItems());
        assertEquals(officer,results.getItems().get(0));
    }


}
