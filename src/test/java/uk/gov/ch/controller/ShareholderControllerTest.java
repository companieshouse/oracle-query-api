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

import uk.gov.ch.model.Shareholder;
import uk.gov.ch.service.ShareholderService;

@ExtendWith(MockitoExtension.class)
public class ShareholderControllerTest {

    @InjectMocks
    ShareholderController controller;

    @Mock
    ShareholderService service;

    private static final String CORPORATE_BODY_ID = "12345678";

    @Test
    @DisplayName(("Get shareholders - corporate body has shareholders"))
    public void testGetShareholdersFromCorporateBodyWithShareholders() {
        List<Shareholder> shareholders = new ArrayList<Shareholder>();
        shareholders.add(new Shareholder());
        
        when(service.getShareholders(CORPORATE_BODY_ID)).thenReturn(shareholders);

        ResponseEntity responseEntity = controller.getShareholders(CORPORATE_BODY_ID);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(shareholders, responseEntity.getBody());
        List<Shareholder> sharers = (List<Shareholder>) responseEntity.getBody();
        assertEquals(1, sharers.size());
    }

    @Test
    @DisplayName(("Get shareholders - corporate body has no shareholders"))
    public void testGetShareholdersFromCorporateBodyWithNoShareholders() {
        List<Shareholder> shareholders = new ArrayList<Shareholder>();
        
        when(service.getShareholders(CORPORATE_BODY_ID)).thenReturn(shareholders);

        ResponseEntity responseEntity = controller.getShareholders(CORPORATE_BODY_ID);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(shareholders, responseEntity.getBody());
        List<Shareholder> sharers = (List<Shareholder>) responseEntity.getBody();
        assertEquals(0, sharers.size());
    }

    @Test
    @DisplayName(("Get shareholders count - corporate body has no shareholders"))
    public void testGetShareholdersCountFromCorporateBodyWithNoShareholders() {
        
        when(service.getShareholderCount(CORPORATE_BODY_ID)).thenReturn(0);

        ResponseEntity responseEntity = controller.getShareholdersCount(CORPORATE_BODY_ID);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody());
    }

    @Test
    @DisplayName(("Get shareholders count - corporate body has shareholders"))
    public void testGetShareholdersCountFromCorporateBodyWithShareholders() {
        
        when(service.getShareholderCount(CORPORATE_BODY_ID)).thenReturn(2);

        ResponseEntity responseEntity = controller.getShareholdersCount(CORPORATE_BODY_ID);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody());
    }
    
}
