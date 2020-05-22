package uk.gov.ch.controller.emergency_auth_code;

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
import uk.gov.ch.model.emergency_auth_code.jsonDataModels.CorporateBodyAppointment;
import uk.gov.ch.model.emergency_auth_code.jsonDataModels.CorporateBodyAppointments;
import uk.gov.ch.service.emergency_auth_code.EmergencyOfficersService;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmergencyOfficersControllerTest {

    @Mock
    EmergencyOfficersService mockEmergencyOfficersService;

    @InjectMocks
    EmergencyOfficersController controller;

    private static final String INCORPORATION_NUMBER = "12345678";

    @Test
    @DisplayName("Get list of eligible officers - no company not found")
    public void testGetEligibleOfficersNoCompanyFound() {

        when(mockEmergencyOfficersService.getEligibleOfficersEmergencyAuthCode(INCORPORATION_NUMBER)).thenReturn(null);

        ResponseEntity<CorporateBodyAppointments> returnedEligibleOfficers = controller.getListOfEligibleCompanyOfficers(INCORPORATION_NUMBER);
        assertEquals(HttpStatus.NOT_FOUND, returnedEligibleOfficers.getStatusCode());
    }

    @Test
    @DisplayName("Get list of eligible officers - no eligible officers found")
    public void testGetEligibleOfficersNoOfficersFound() {
        when(mockEmergencyOfficersService.getEligibleOfficersEmergencyAuthCode(INCORPORATION_NUMBER)).thenReturn(corporateBodyAppointmentsNoOfficers());

        ResponseEntity<CorporateBodyAppointments> returnedEligibleOfficers = controller.getListOfEligibleCompanyOfficers(INCORPORATION_NUMBER);
        assertEquals(HttpStatus.NOT_FOUND, returnedEligibleOfficers.getStatusCode());
    }

    @Test
    @DisplayName("Get list of eligible officers - success path")
    public void testGetEligibleOfficersForCompanySuccess() {

        when(mockEmergencyOfficersService.getEligibleOfficersEmergencyAuthCode(INCORPORATION_NUMBER)).thenReturn(corporateBodyAppointments());

        ResponseEntity<CorporateBodyAppointments> returnedEligibleOfficers = controller.getListOfEligibleCompanyOfficers(INCORPORATION_NUMBER);
        CorporateBodyAppointments body = returnedEligibleOfficers.getBody();
        assertEquals(HttpStatus.OK, returnedEligibleOfficers.getStatusCode());
        assertEquals(2, body.getTotalResults());
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
