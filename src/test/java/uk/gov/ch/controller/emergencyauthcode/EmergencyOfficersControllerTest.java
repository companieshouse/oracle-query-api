package uk.gov.ch.controller.emergencyauthcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyAppointment;
import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyAppointments;
import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyEFilingStatus;
import uk.gov.ch.service.emergencyauthcode.EmergencyOfficersService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(EmergencyOfficersController.class)
class EmergencyOfficersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EmergencyOfficersService mockEmergencyOfficersService;

    @InjectMocks
    EmergencyOfficersController controller;

    private static final String INCORPORATION_NUMBER = "12345678";
    private static final String INVALID_INCORPORATION_NUMBER = "12345678!";
    private static final String OFFICER_ID = "87654321";
    private static final String INVALID_OFFICER_ID = "8765432!";
    private static final int START_INDEX = 0;
    private static final int ITEMS_PER_PAGE = 15;

    Pageable pageable = PageRequest.of(START_INDEX, ITEMS_PER_PAGE);

    @Test
    @DisplayName("Get list of eligible officers - no company not found")
    void testGetEligibleOfficersNoCompanyFound() {

        when(mockEmergencyOfficersService.getEligibleOfficersEmergencyAuthCode(INCORPORATION_NUMBER, pageable)).thenReturn(null);

        ResponseEntity<CorporateBodyAppointments> returnedEligibleOfficers = controller.getListOfEligibleCompanyOfficers(INCORPORATION_NUMBER, START_INDEX, ITEMS_PER_PAGE);
        assertEquals(HttpStatus.NOT_FOUND, returnedEligibleOfficers.getStatusCode());
    }

    @Test
    @DisplayName("Get list of eligible officers - no eligible officers found")
    void testGetEligibleOfficersNoOfficersFound() {
        when(mockEmergencyOfficersService.getEligibleOfficersEmergencyAuthCode(INCORPORATION_NUMBER, pageable)).thenReturn(corporateBodyAppointmentsNoOfficers());

        ResponseEntity<CorporateBodyAppointments> returnedEligibleOfficers = controller.getListOfEligibleCompanyOfficers(INCORPORATION_NUMBER, START_INDEX, ITEMS_PER_PAGE);
        assertEquals(HttpStatus.NOT_FOUND, returnedEligibleOfficers.getStatusCode());
    }

    @Test
    @DisplayName("Get list of eligible officers - success path")
    void testGetEligibleOfficersForCompanySuccess() {

        when(mockEmergencyOfficersService.getEligibleOfficersEmergencyAuthCode(INCORPORATION_NUMBER, pageable)).thenReturn(corporateBodyAppointments());

        ResponseEntity<CorporateBodyAppointments> returnedEligibleOfficers = controller.getListOfEligibleCompanyOfficers(INCORPORATION_NUMBER, START_INDEX, ITEMS_PER_PAGE);
        CorporateBodyAppointments body = returnedEligibleOfficers.getBody();
        assertEquals(HttpStatus.OK, returnedEligibleOfficers.getStatusCode());
        assertEquals(2, body.getTotalResults());
    }

    @Test
    @DisplayName("Get eligible officer - no eligible officer found")
    void testGetEligibleOfficerNoOfficerFound() {
        when(mockEmergencyOfficersService.getEligibleOfficer(INCORPORATION_NUMBER, OFFICER_ID)).thenReturn(null);

        ResponseEntity<CorporateBodyAppointment> returnedEligibleOfficer = controller.getCompanyOfficer(INCORPORATION_NUMBER, OFFICER_ID);
        assertEquals(HttpStatus.NOT_FOUND, returnedEligibleOfficer.getStatusCode());
    }

    @Test
    @DisplayName("Get eligible officer - invalid officer ID")
    void testGetEligibleOfficerInvalidOfficerId() throws Exception {

        mockMvc.perform(get("/emergency-auth-code/company/{companyNumber}/eligible-officers/{officerId}",
                           INCORPORATION_NUMBER, INVALID_OFFICER_ID))
               .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Get eligible officer - invalid company number")
    void testGetEligibleOfficerInvalidCompanyNumber() throws Exception {

        mockMvc.perform(get("/emergency-auth-code/company/{companyNumber}/eligible-officers/{officerId}",
                           INVALID_INCORPORATION_NUMBER, OFFICER_ID))
               .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(("Get eligible officer - success path"))
    void testGetEligibleOfficerSuccess() {

        when(mockEmergencyOfficersService.getEligibleOfficer(INCORPORATION_NUMBER, OFFICER_ID)).thenReturn(new CorporateBodyAppointment());

        ResponseEntity<CorporateBodyAppointment> returnedEligibleOfficer = controller.getCompanyOfficer(INCORPORATION_NUMBER, OFFICER_ID);
        assertEquals(HttpStatus.OK, returnedEligibleOfficer.getStatusCode());
    }

    @Test
    @DisplayName(("Get filing history - has not filed in past 30 days"))
    void testGetFilingHistoryHasNotFiled() {
        CorporateBodyEFilingStatus corporateBodyEFilingStatus = new CorporateBodyEFilingStatus();
        corporateBodyEFilingStatus.setEfilingFoundInPeriod(false);
        when(mockEmergencyOfficersService.checkIfEFiledLastThirtyDays(INCORPORATION_NUMBER)).thenReturn(corporateBodyEFilingStatus);

        ResponseEntity<CorporateBodyEFilingStatus> returnedFilingHistory = controller.getHasFiledLastThirtyDays(INCORPORATION_NUMBER);
        assertEquals(HttpStatus.OK, returnedFilingHistory.getStatusCode());
        assertEquals(false, returnedFilingHistory.getBody().getEfilingFoundInPeriod());
    }

    @Test
    @DisplayName(("Get filing history - has filed in past 30 days"))
    void testGetFilingHistoryHasFiled() {
        CorporateBodyEFilingStatus corporateBodyEFilingStatus = new CorporateBodyEFilingStatus();
        corporateBodyEFilingStatus.setEfilingFoundInPeriod(true);
        when(mockEmergencyOfficersService.checkIfEFiledLastThirtyDays(INCORPORATION_NUMBER)).thenReturn(corporateBodyEFilingStatus);

        ResponseEntity<CorporateBodyEFilingStatus> returnedFilingHistory = controller.getHasFiledLastThirtyDays(INCORPORATION_NUMBER);
        assertEquals(HttpStatus.OK, returnedFilingHistory.getStatusCode());
        assertEquals(true, returnedFilingHistory.getBody().getEfilingFoundInPeriod());
    }

    private CorporateBodyAppointments corporateBodyAppointments() {
        CorporateBodyAppointments corporateBodyAppointments = new CorporateBodyAppointments();
        corporateBodyAppointments.setStartIndex(0);
        corporateBodyAppointments.setTotalResults(2);
        corporateBodyAppointments.setItemsPerPage(2);
        corporateBodyAppointments.setItems(corporateBodyAppointmentList());

        return corporateBodyAppointments;

    }

    private CorporateBodyAppointments corporateBodyAppointmentsNoOfficers() {
        CorporateBodyAppointments corporateBodyAppointments = new CorporateBodyAppointments();
        corporateBodyAppointments.setStartIndex(0);
        corporateBodyAppointments.setTotalResults(0);
        corporateBodyAppointments.setItemsPerPage(0);
        corporateBodyAppointments.setItems(new ArrayList<>());

        return corporateBodyAppointments;

    }

    private List<CorporateBodyAppointment> corporateBodyAppointmentList() {
        List<CorporateBodyAppointment> corporateBodyAppointments = new ArrayList<>();

        corporateBodyAppointments.add(new CorporateBodyAppointment());
        corporateBodyAppointments.add(new CorporateBodyAppointment());

        return corporateBodyAppointments;
    }
}
