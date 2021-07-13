package uk.gov.ch.controller.officer.active;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.ch.exception.InvalidActiveOfficersCountFoundException;
import uk.gov.ch.model.officer.active.ActiveOfficerDetails;
import uk.gov.ch.service.officer.active.ActiveOfficerDetailsService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActiveOfficerDetailsControllerTest {
    @InjectMocks
    private ActiveOfficerDetailsController controller;
    @Mock
    private ActiveOfficerDetailsService service;
    private static final String COMPANY_NUMBER = "12345678";
    @Test
    @DisplayName("Get Active Officer - Company With Active Officers")
    void testGetActiveOfficerDetailsForCompanyWithActiveOfficer() throws InvalidActiveOfficersCountFoundException {
        ActiveOfficerDetails mockOfficer = new ActiveOfficerDetails();
        when(service.getActiveOfficerDetails(COMPANY_NUMBER)).thenReturn(mockOfficer);
        ResponseEntity<ActiveOfficerDetails> responseEntity = controller.getActiveOfficerDetails(COMPANY_NUMBER);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockOfficer, responseEntity.getBody());
    }
    @Test
    @DisplayName("Get Active Officer - Company With No Active Officers")
    void testGetActiveOfficerDetailsForCompanyWithNoActiveOfficers() throws InvalidActiveOfficersCountFoundException {
        when(service.getActiveOfficerDetails(COMPANY_NUMBER)).thenThrow(new InvalidActiveOfficersCountFoundException("No results were found when getting Active Officers for company number "));
        ResponseEntity<ActiveOfficerDetails> responseEntity = controller.getActiveOfficerDetails(COMPANY_NUMBER);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Get Active Officer - Company With More Than One Active Officers")
    void testGetActiveOfficerDetailsForCompanyWithMultipleActiveOfficers() throws InvalidActiveOfficersCountFoundException {
        when(service.getActiveOfficerDetails(COMPANY_NUMBER)).thenThrow(new InvalidActiveOfficersCountFoundException("Single result not returned"));
        ResponseEntity<ActiveOfficerDetails> responseEntity = controller.getActiveOfficerDetails(COMPANY_NUMBER);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}