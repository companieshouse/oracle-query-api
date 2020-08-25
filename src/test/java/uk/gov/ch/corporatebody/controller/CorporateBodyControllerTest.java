package uk.gov.ch.corporatebody.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import uk.gov.ch.corporatebody.exception.CorporateBodyNotFoundException;
import uk.gov.ch.corporatebody.service.CorporateBodyService;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CorporateBodyControllerTest {

    @Mock
    CorporateBodyService corporateBodyService;

    @InjectMocks
    CorporateBodyController controller;

    private static final String INCORPORATION_NUMBER = "12345678";

    @Test
    @DisplayName("Get action code - company not found")
    public void testGetActionCodeNoCompanyFound() throws CorporateBodyNotFoundException {

        when(corporateBodyService.getActionCode(INCORPORATION_NUMBER)).thenThrow(new CorporateBodyNotFoundException("No company found"));

        ResponseEntity<Integer> response = controller.getActionCode(INCORPORATION_NUMBER);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Get action code - company was found")
    public void testGetActionCodeCompanyFound() throws CorporateBodyNotFoundException {
        final int dummyActionCode = 99;
        
        when(corporateBodyService.getActionCode(INCORPORATION_NUMBER)).thenReturn(dummyActionCode);

        ResponseEntity<Integer> response = controller.getActionCode(INCORPORATION_NUMBER);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dummyActionCode, response.getBody());
    }
}
