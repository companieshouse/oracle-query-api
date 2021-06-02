package uk.gov.ch.controller.corporatebody;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.ch.exception.CorporateBodyNotFoundException;
import uk.gov.ch.service.corporatebody.CorporateBodyService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CorporateBodyControllerTest {

    @Mock
    CorporateBodyService corporateBodyService;

    @InjectMocks
    CorporateBodyController controller;

    private static final String INCORPORATION_NUMBER = "12345678";

    @Test
    @DisplayName("Get action code - company not found")
    void testGetActionCodeNoCompanyFound() throws CorporateBodyNotFoundException {

        when(corporateBodyService.getActionCode(INCORPORATION_NUMBER)).thenThrow(new CorporateBodyNotFoundException("No company found"));

        ResponseEntity<Long> response = controller.getActionCode(INCORPORATION_NUMBER);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Get action code - company was found")
    void testGetActionCodeCompanyFound() throws CorporateBodyNotFoundException {
        final long dummyActionCode = 99;

        when(corporateBodyService.getActionCode(INCORPORATION_NUMBER)).thenReturn(dummyActionCode);

        ResponseEntity<Long> response = controller.getActionCode(INCORPORATION_NUMBER);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dummyActionCode, response.getBody());
    }

    @Test
    @DisplayName("Get traded status - company not found")
    void testGetTradedStatusNoCompanyFound() throws CorporateBodyNotFoundException {

        when(corporateBodyService.getTradedStatus(INCORPORATION_NUMBER)).thenThrow(new CorporateBodyNotFoundException("No company found"));

        ResponseEntity<Long> response = controller.getTradedStatus(INCORPORATION_NUMBER);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Get traded status - company was found")
    void testGetTradedStatusCompanyFound() throws CorporateBodyNotFoundException {
        final Long dummyTradedStatus = 99L;

        when(corporateBodyService.getTradedStatus(INCORPORATION_NUMBER)).thenReturn(dummyTradedStatus);

        ResponseEntity<Long> response = controller.getTradedStatus(INCORPORATION_NUMBER);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dummyTradedStatus, response.getBody());
    }
}
