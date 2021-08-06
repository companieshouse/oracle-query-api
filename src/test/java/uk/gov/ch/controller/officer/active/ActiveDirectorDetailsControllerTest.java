package uk.gov.ch.controller.officer.active;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.ch.exception.InvalidActiveDirectorsCountFoundException;
import uk.gov.ch.model.officer.active.ActiveDirectorDetails;
import uk.gov.ch.service.officer.active.ActiveDirectorDetailsService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActiveDirectorDetailsControllerTest {
    private static final String COMPANY_NUMBER = "12345678";
    private static final int START_INDEX = 0;
    private static final int ITEMS_PER_PAGE = 10;
    Pageable pageable = PageRequest.of(START_INDEX, ITEMS_PER_PAGE);
    @InjectMocks
    private ActiveDirectorDetailsController controller;
    @Mock
    private ActiveDirectorDetailsService service;

    @Test
    @DisplayName("Get Active Officer - Company With Active Officers")
    void testGetActiveDirectorDetailsForCompanyWithActiveOfficer() throws InvalidActiveDirectorsCountFoundException {

        ActiveDirectorDetails mockOfficer = new ActiveDirectorDetails();
        when(service.getActiveDirectorDetails(COMPANY_NUMBER, pageable)).thenReturn(mockOfficer);
        ResponseEntity<ActiveDirectorDetails> responseEntity = controller.getActiveDirectorDetails(COMPANY_NUMBER, START_INDEX, ITEMS_PER_PAGE);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockOfficer, responseEntity.getBody());
    }
    @Test
    @DisplayName("Get Active Officer - Company With No Active Officers")
    void testGetActiveDirectorDetailsForCompanyWithNoActiveOfficers() throws InvalidActiveDirectorsCountFoundException {
        when(service.getActiveDirectorDetails(COMPANY_NUMBER, pageable)).thenThrow(new InvalidActiveDirectorsCountFoundException("No results were found when getting Active Officers for company number "));
        ResponseEntity<ActiveDirectorDetails> responseEntity = controller.getActiveDirectorDetails(COMPANY_NUMBER, START_INDEX, ITEMS_PER_PAGE);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Get Active Officer - Company With More Than One Active Officers")
    void testGetActiveDirectorDetailsForCompanyWithMultipleActiveOfficers() throws InvalidActiveDirectorsCountFoundException {
        when(service.getActiveDirectorDetails(COMPANY_NUMBER, pageable)).thenThrow(new InvalidActiveDirectorsCountFoundException("Single result not returned"));
        ResponseEntity<ActiveDirectorDetails> responseEntity = controller.getActiveDirectorDetails(COMPANY_NUMBER, START_INDEX, ITEMS_PER_PAGE);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}