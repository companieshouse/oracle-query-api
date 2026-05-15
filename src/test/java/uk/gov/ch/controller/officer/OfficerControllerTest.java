package uk.gov.ch.controller.officer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import uk.gov.ch.exception.NoOfficersExistingException;
import uk.gov.ch.exception.OfficersMappingException;
import uk.gov.ch.service.officer.OfficerService;
import uk.gov.companieshouse.api.model.officers.OfficersApi;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(OfficerController.class)
class OfficerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OfficerService officerService;

    private OfficerController controller;

    @BeforeEach
    void setUp() {
        controller = new OfficerController(officerService);
    }

    private static final String COMPANY_NUMBER = "12345678";
    private static final String INVALID_COMPANY_NUMBER = "12345#78";

    @Test
    @DisplayName("Get officers returns an OfficerApi response with a Http OK status 200")
    void testGetOfficerReturnsSuccessfully() throws OfficersMappingException, NoOfficersExistingException {
        when(officerService.getOfficers(COMPANY_NUMBER)).thenReturn(new OfficersApi());

        ResponseEntity<OfficersApi> response = controller.getOfficers(COMPANY_NUMBER);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Get officers throws a NoOfficersExistException Http NOT FOUND status 404")
    void testGetOfficerReturnsNotFound() throws OfficersMappingException, NoOfficersExistingException {
        when(officerService.getOfficers(COMPANY_NUMBER)).thenThrow(NoOfficersExistingException.class);

        ResponseEntity<OfficersApi> response = controller.getOfficers(COMPANY_NUMBER);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Get officers throws a OfficersMappingException so Http INTERNAL_SERVER_ERROR status 500")
    void testGetOfficerReturnsInternalServerError() throws OfficersMappingException, NoOfficersExistingException {
        when(officerService.getOfficers(COMPANY_NUMBER)).thenThrow(OfficersMappingException.class);

        ResponseEntity<OfficersApi> response = controller.getOfficers(COMPANY_NUMBER);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    @DisplayName("Get officer - invalid company number")
    void testGetOfficerInvalidCompanyNumber() throws Exception {

        mockMvc.perform(get("/company/{companyNumber}/officers", INVALID_COMPANY_NUMBER))
            .andExpect(status().isBadRequest());
    }
}
