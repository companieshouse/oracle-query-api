package uk.gov.ch.controller.officer;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import uk.gov.ch.exception.NoOfficersExistingException;
import uk.gov.ch.exception.OfficersMappingException;
import uk.gov.ch.service.officer.OfficerService;
import uk.gov.companieshouse.api.model.officers.OfficersApi;

@ExtendWith(MockitoExtension.class)
class OfficerControllerTest {

    @InjectMocks
    private OfficerController controller;

    @Mock
    private OfficerService officerService;

    private static final String COMP_NO = "12345678";

    @Test
    @DisplayName("Get officers returns an OfficerApi response with a Http OK status 200")
    void testGetOfficerReturnsSuccessfully() throws OfficersMappingException, NoOfficersExistingException {
        when(officerService.getOfficers(COMP_NO)).thenReturn(new OfficersApi());

        ResponseEntity<OfficersApi> response = controller.getOfficers(COMP_NO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Get officers throws a NoOfficersExistException Http NOT FOUND status 404")
    void testGetOfficerReturnsNotFound() throws OfficersMappingException, NoOfficersExistingException {
        when(officerService.getOfficers(COMP_NO)).thenThrow(NoOfficersExistingException.class);

        ResponseEntity<OfficersApi> response = controller.getOfficers(COMP_NO);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Get officers throws a OfficersMappingException so Http INTERNAL_SERVER_ERROR status 500")
    void testGetOfficerReturnsInternalServerError() throws OfficersMappingException, NoOfficersExistingException {
        when(officerService.getOfficers(COMP_NO)).thenThrow(OfficersMappingException.class);

        ResponseEntity<OfficersApi> response = controller.getOfficers(COMP_NO);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
