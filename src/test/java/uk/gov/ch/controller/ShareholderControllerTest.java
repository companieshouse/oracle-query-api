package uk.gov.ch.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import uk.gov.ch.controller.shareholder.ShareholderController;
import uk.gov.ch.model.shareholder.Shareholder;
import uk.gov.ch.service.shareholder.ShareholderService;

@ExtendWith(MockitoExtension.class)
class ShareholderControllerTest {

    @InjectMocks
    private ShareholderController controller;

    @Mock
    private ShareholderService service;

    private static final String COMPANY_NUMBER = "12345678";

    @Test
    @DisplayName(("Get shareholders - company with shareholders"))
    void testGetShareholdersFromCorporateBodyWithShareholders() {
        List<Shareholder> shareholders = new ArrayList<Shareholder>();
        shareholders.add(new Shareholder());
        
        when(service.getShareholders(COMPANY_NUMBER)).thenReturn(shareholders);

        ResponseEntity<List<Shareholder>> responseEntity = controller.getShareholders(COMPANY_NUMBER);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(shareholders, responseEntity.getBody());
        List<Shareholder> sharers = responseEntity.getBody();
        assertEquals(1, sharers.size());
    }

    @Test
    @DisplayName(("Get shareholders - company with no shareholders"))
    void testGetShareholdersFromCorporateBodyWithNoShareholders() {
        List<Shareholder> shareholders = new ArrayList<Shareholder>();
        
        when(service.getShareholders(COMPANY_NUMBER)).thenReturn(shareholders);

        ResponseEntity<List<Shareholder>>  responseEntity = controller.getShareholders(COMPANY_NUMBER);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(shareholders, responseEntity.getBody());
        List<Shareholder> sharers = responseEntity.getBody();
        assertEquals(0, sharers.size());
    }

    @Test
    @DisplayName(("Get shareholders count - company with no shareholders"))
    void testGetShareholdersCountFromCorporateBodyWithNoShareholders() {
        
        when(service.getShareholderCount(COMPANY_NUMBER)).thenReturn(0);

        ResponseEntity<Integer> responseEntity = controller.getShareholdersCount(COMPANY_NUMBER);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody());
    }

    @Test
    @DisplayName(("Get shareholders count - company with shareholders"))
    void testGetShareholdersCountFromCorporateBodyWithShareholders() {
        
        when(service.getShareholderCount(COMPANY_NUMBER)).thenReturn(2);

        ResponseEntity<Integer> responseEntity = controller.getShareholdersCount(COMPANY_NUMBER);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody());
    }
    
}
