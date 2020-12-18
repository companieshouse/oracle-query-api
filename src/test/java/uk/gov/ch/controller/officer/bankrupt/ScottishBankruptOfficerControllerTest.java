package uk.gov.ch.controller.officer.bankrupt;

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
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerDetails;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearch;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchResult;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchResults;
import uk.gov.ch.service.officer.bankrupt.impl.ScottishBankruptOfficerServiceImpl;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class ScottishBankruptOfficerControllerTest {

    private static final String EPHEMERAL_KEY = "0123456";

    @Mock
   private ScottishBankruptOfficerServiceImpl service;

    @InjectMocks
    private ScottishBankruptOfficerController controller;



    @Test
    @DisplayName("No officers found")
    public void testNoOfficersFound(){
        ScottishBankruptOfficerSearch search = new ScottishBankruptOfficerSearch();
        ScottishBankruptOfficerSearchResults results = new ScottishBankruptOfficerSearchResults();
        results.setItems(new ArrayList<>());
        when(service.getScottishBankruptOfficers(search)).thenReturn(results);

        ResponseEntity<ScottishBankruptOfficerSearchResults> response = controller.search(search);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Officers found")
    public void testOfficersFound(){
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

    @Test
    @DisplayName("Officer found by id")
    public void testOfficerFoundById(){
        ScottishBankruptOfficerDetails result = new ScottishBankruptOfficerDetails();

        when(service.getScottishBankruptOfficer(EPHEMERAL_KEY)).thenReturn(result);
        ResponseEntity<ScottishBankruptOfficerDetails> response = controller.getOfficerById(EPHEMERAL_KEY);

        assertEquals(result,response.getBody());

    }

    @Test
    @DisplayName("No Officer found by id")
    public void testNoOfficerFoundById(){
        ScottishBankruptOfficerDetails search = new ScottishBankruptOfficerDetails();


        when(service.getScottishBankruptOfficer(search.getEphemeralKey())).thenReturn(null);
        ResponseEntity<ScottishBankruptOfficerDetails> response = controller.getOfficerById(search.getEphemeralKey());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }



}
