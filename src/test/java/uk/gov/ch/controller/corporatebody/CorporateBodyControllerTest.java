package uk.gov.ch.controller.corporatebody;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import uk.gov.ch.exception.CompanyProfileMappingException;
import uk.gov.ch.exception.CorporateBodyNotFoundException;
import uk.gov.ch.service.corporatebody.CorporateBodyService;
import uk.gov.companieshouse.api.model.company.CompanyProfileApi;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CorporateBodyController.class)
class CorporateBodyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    CorporateBodyService corporateBodyService;

    private CorporateBodyController controller;

    @BeforeEach
    void setUp() {
        controller = new CorporateBodyController(corporateBodyService);
    }

    private static final String INCORPORATION_NUMBER = "12345678";
    private static final String INVALID_COMPANY_NUMBER = "123#5678";

    @Test
    @DisplayName("Get action code - company not found")
    void testGetActionCodeNoCompanyFound() throws CorporateBodyNotFoundException {

        when(corporateBodyService.getActionCode(INCORPORATION_NUMBER))
                .thenThrow(new CorporateBodyNotFoundException("No company found"));

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
    @DisplayName("Get action code - invalid company number")
    void testGetActionCodeInvalidCompanyNumber() throws Exception {

        mockMvc.perform(get("/company/{companyNumber}/action-code", INVALID_COMPANY_NUMBER))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Get traded status - company not found")
    void testGetTradedStatusNoCompanyFound() throws CorporateBodyNotFoundException {

        when(corporateBodyService.getTradedStatus(INCORPORATION_NUMBER))
                .thenThrow(new CorporateBodyNotFoundException("No company found"));

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

    @Test
    @DisplayName("Get traded status - invalid company number")
    void testGetTradedStatusInvalidCompanyNumber() throws Exception {

        mockMvc.perform(get("/company/{companyNumber}/traded-status", INVALID_COMPANY_NUMBER))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Get company profile - profile successfully returned from service")
    void testGetCompanyProfileSuccessful() throws CorporateBodyNotFoundException, CompanyProfileMappingException {
        CompanyProfileApi companyProfileApi = new CompanyProfileApi();
        when(corporateBodyService.getCompanyProfile(INCORPORATION_NUMBER)).thenReturn(companyProfileApi);

        ResponseEntity<CompanyProfileApi> response = controller.getCompanyProfile(INCORPORATION_NUMBER);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(companyProfileApi, response.getBody());
    }

    @Test
    @DisplayName("Get company profile - corporate body not found")
    void testGetCompanyProfileNotFoundException()
            throws CorporateBodyNotFoundException, CompanyProfileMappingException {
        when(corporateBodyService.getCompanyProfile(INCORPORATION_NUMBER))
                .thenThrow(CorporateBodyNotFoundException.class);

        ResponseEntity<CompanyProfileApi> response = controller.getCompanyProfile(INCORPORATION_NUMBER);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Get company profile - exception when mapping company profile")
    void testGetCompanyProfileMappingException() throws CorporateBodyNotFoundException, CompanyProfileMappingException {
        when(corporateBodyService.getCompanyProfile(INCORPORATION_NUMBER))
                .thenThrow(CompanyProfileMappingException.class);

        ResponseEntity<CompanyProfileApi> response = controller.getCompanyProfile(INCORPORATION_NUMBER);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    @DisplayName("Get company profile - invalid company number")
    void testGetCompanyProfileInvalidCompanyNumber() throws Exception {

        mockMvc.perform(get("/company/{companyNumber}", INVALID_COMPANY_NUMBER))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Get registered email address - invalid company number")
    void testGetRegisteredEmailAddressInvalidCompanyNumber() throws Exception {

        mockMvc.perform(get("/company/{companyNumber}/registered-email-address", INVALID_COMPANY_NUMBER))
            .andExpect(status().isBadRequest());
    }
}
