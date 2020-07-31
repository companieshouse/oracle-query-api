package uk.gov.ch.service.emergencyauthcode.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyAppointment;
import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyAppointments;
import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyEFilingStatus;
import uk.gov.ch.model.emergencyauthcode.sqldatamodels.CorporateBodyAppointmentDataModel;
import uk.gov.ch.repository.officers.EmergencyAuthCodeEligibleOfficersRepository;
import uk.gov.ch.transformers.emergencyauthcode.EmergencyOfficersTransformer;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmergencyOfficersServiceImplTest {

    @Mock
    EmergencyAuthCodeEligibleOfficersRepository mockRepo;

    @Mock
    EmergencyOfficersTransformer mockTransformer;

    @InjectMocks
    EmergencyOfficersServiceImpl service;

    private static final String INCORPORATION_NUMBER = "12345678";
    private static final String OFFICER_ID = "87654321";
    private static final int START_INDEX = 0;
    private static final int ITEMS_PER_PAGE = 15;

    Pageable pageable = PageRequest.of(START_INDEX, ITEMS_PER_PAGE);

    @Test
    @DisplayName("Get eligible officers for emergency-auth-code from repository")
    public void testGetEligibleOfficersFromRepository() {
        Page<CorporateBodyAppointmentDataModel> mockRepoResponse = getMockEmergencyAuthCodeRepo();
        when(mockRepo.findEligibleOfficersEmergencyAuthCode(INCORPORATION_NUMBER, pageable)).thenReturn(mockRepoResponse);

        List<CorporateBodyAppointment> mockTransformerResponse = getMockEmergencyAuthCodeTransformer();
        when(mockTransformer.convert(mockRepoResponse)).thenReturn(mockTransformerResponse);

        CorporateBodyAppointments returnedCorporateBodyAppointments = service.getEligibleOfficersEmergencyAuthCode(INCORPORATION_NUMBER, pageable);
        assertEquals(2, returnedCorporateBodyAppointments.getTotalResults());
        assertEquals(0, returnedCorporateBodyAppointments.getStartIndex());
        assertEquals(15, returnedCorporateBodyAppointments.getItemsPerPage());
    }

    @Test
    @DisplayName("Get eligible officer returns null from repository")
    public void testGetEligibleOfficerReturnsNull() {
        when(mockRepo.findEligibleOfficer(INCORPORATION_NUMBER, OFFICER_ID)).thenReturn(null);

        CorporateBodyAppointment returnedCorporateBodyAppointment = service.getEligibleOfficer(INCORPORATION_NUMBER, OFFICER_ID);
        assertNull(returnedCorporateBodyAppointment);
    }

    @Test
    @DisplayName("Get eligible officer for emergency-auth-code from repository")
    public void testGetEligibleOfficerFromRepository() {
        CorporateBodyAppointmentDataModel mockRepoResponse = new CorporateBodyAppointmentDataModel() {{
            setCorporateBodyAppointmentId(123L);
            setOccupationDescription("description");
        }};
        when(mockRepo.findEligibleOfficer(INCORPORATION_NUMBER, OFFICER_ID)).thenReturn(mockRepoResponse);

        CorporateBodyAppointment mockTransformerResponse = new CorporateBodyAppointment() {{
            setId("123");
            setOccupation("description");
        }};
        when(mockTransformer.convert(mockRepoResponse)).thenReturn(mockTransformerResponse);

        CorporateBodyAppointment returnedCorporateBodyAppointment = service.getEligibleOfficer(INCORPORATION_NUMBER, OFFICER_ID);
        assertEquals("123", returnedCorporateBodyAppointment.getId());
        assertEquals("description", returnedCorporateBodyAppointment.getOccupation());
    }

    @Test
    @DisplayName("Get filing history for company returns filing in past 30 days")
    public void testGetFilingHistoryReturnsFiling() {
        when(mockRepo.findEFilingsInLastThirtyDays(INCORPORATION_NUMBER)).thenReturn(1L);

        CorporateBodyEFilingStatus corporateBodyEFilingStatus = service.checkIfEFiledLastThirtyDays(INCORPORATION_NUMBER);
        assertTrue(corporateBodyEFilingStatus.getEfilingFoundInPeriod());
    }

    @Test
    @DisplayName("Get filing history for company returns no filing in past 30 days")
    public void testGetFilingHistoryReturnsNoFiling() {
        when(mockRepo.findEFilingsInLastThirtyDays(INCORPORATION_NUMBER)).thenReturn(0L);

        CorporateBodyEFilingStatus corporateBodyEFilingStatus = service.checkIfEFiledLastThirtyDays(INCORPORATION_NUMBER);
        assertFalse(corporateBodyEFilingStatus.getEfilingFoundInPeriod());
    }

    private Page<CorporateBodyAppointmentDataModel> getMockEmergencyAuthCodeRepo() {
        List<CorporateBodyAppointmentDataModel> corporateBodyAppointments = new ArrayList<>();
        corporateBodyAppointments.add(new CorporateBodyAppointmentDataModel());
        corporateBodyAppointments.add(new CorporateBodyAppointmentDataModel());

        return new PageImpl<>(corporateBodyAppointments, pageable, 2);
    }

    private List<CorporateBodyAppointment> getMockEmergencyAuthCodeTransformer() {
        List<CorporateBodyAppointment> corporateBodyAppointments = new ArrayList<>();
        corporateBodyAppointments.add(new CorporateBodyAppointment());
        corporateBodyAppointments.add(new CorporateBodyAppointment());

        return corporateBodyAppointments;
    }
}
