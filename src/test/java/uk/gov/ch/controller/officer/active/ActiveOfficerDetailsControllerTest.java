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
import uk.gov.ch.exception.InvalidActiveOfficersCountFoundException;
import uk.gov.ch.model.officer.active.ActiveOfficerDetails;
import uk.gov.ch.service.officer.active.ActiveOfficerDetailsService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActiveOfficerDetailsControllerTest {
    private static final String COMPANY_NUMBER = "12345678";
    private static final int START_INDEX = 0;
    private static final int ITEMS_PER_PAGE = 10;
    Pageable pageable = PageRequest.of(START_INDEX, ITEMS_PER_PAGE);
    @InjectMocks
    private ActiveOfficerDetailsController controller;
    @Mock
    private ActiveOfficerDetailsService service;

    @Test
    @DisplayName("Get Active Director - Company With Active Director")
    void testGetActiveDirectorDetailsForCompanyWithOneActiveDirector() throws InvalidActiveOfficersCountFoundException {

        ActiveOfficerDetails mockOfficer = new ActiveOfficerDetails();
        when(service.getActiveDirectorDetails(COMPANY_NUMBER, pageable)).thenReturn(mockOfficer);
        ResponseEntity<ActiveOfficerDetails> responseEntity = controller.getActiveDirectorDetails(COMPANY_NUMBER, START_INDEX, ITEMS_PER_PAGE);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockOfficer, responseEntity.getBody());
    }
    @Test
    @DisplayName("Get Active Director - Company With No Active Director")
    void testGetActiveDirectorDetailsForCompanyWithNoActiveDirector() throws InvalidActiveOfficersCountFoundException {
        when(service.getActiveDirectorDetails(COMPANY_NUMBER, pageable)).thenThrow(new InvalidActiveOfficersCountFoundException("No results were found when getting Active Directors."));
        ResponseEntity<ActiveOfficerDetails> responseEntity = controller.getActiveDirectorDetails(COMPANY_NUMBER, START_INDEX, ITEMS_PER_PAGE);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Get Active Director - Company With More Than One Active Director")
    void testGetActiveDirectorDetailsForCompanyWithMultipleActiveDirectors() throws InvalidActiveOfficersCountFoundException {
        when(service.getActiveDirectorDetails(COMPANY_NUMBER, pageable)).thenThrow(new InvalidActiveOfficersCountFoundException("Single result not returned"));
        ResponseEntity<ActiveOfficerDetails> responseEntity = controller.getActiveDirectorDetails(COMPANY_NUMBER, START_INDEX, ITEMS_PER_PAGE);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Get Active Officers - Company With No Active Officers")
    void testGetActiveOfficersDetailsForCompanyWithNoActiveOfficers() throws InvalidActiveOfficersCountFoundException {
        when(service.getActiveOfficersDetails(COMPANY_NUMBER, pageable)).thenThrow(new InvalidActiveOfficersCountFoundException("No results were found when getting Active Officers for company number "));
        ResponseEntity responseEntity = controller.getActiveOfficersDetails(COMPANY_NUMBER, START_INDEX, ITEMS_PER_PAGE);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Get Active Officers - Company With Active Officers")
    void testGetActiveOfficersDetailsForCompanyWithMultipleActiveOfficers() throws InvalidActiveOfficersCountFoundException {
        List<ActiveOfficerDetails> officers = new ArrayList();
        officers.add(new ActiveOfficerDetails());
        officers.add(new ActiveOfficerDetails());
        when(service.getActiveOfficersDetails(COMPANY_NUMBER, pageable)).thenReturn(officers);
        ResponseEntity responseEntity = controller.getActiveOfficersDetails(COMPANY_NUMBER, START_INDEX, ITEMS_PER_PAGE);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(officers, responseEntity.getBody());
    }
}