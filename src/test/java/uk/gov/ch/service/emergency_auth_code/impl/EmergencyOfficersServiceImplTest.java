package uk.gov.ch.service.emergency_auth_code.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.ch.model.emergency_auth_code.jsonDataModels.CorporateBodyAppointment;
import uk.gov.ch.model.emergency_auth_code.jsonDataModels.CorporateBodyAppointments;
import uk.gov.ch.model.emergency_auth_code.sqlDataModels.CorporateBodyAppointmentDataModel;
import uk.gov.ch.repository.officers.EmergencyAuthCodeEligibleOfficersRepository;
import uk.gov.ch.transformers.emergency_auth_code.EmergencyOfficersTransformer;

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

    @Test
    @DisplayName("Get eligible officers for emergency-auth-code from repository")
    public void testGetEligibleOfficersFromRepository() {
        List<CorporateBodyAppointmentDataModel> mockRepoResponse = getMockEmergencyAuthCodeRepo();
        when(mockRepo.findEligibleOfficersEmergencyAuthCode(INCORPORATION_NUMBER)).thenReturn(mockRepoResponse);

        List<CorporateBodyAppointment> mockTransformerResponse = getMockEmergencyAuthCodeTransformer();
        when(mockTransformer.convert(mockRepoResponse)).thenReturn(mockTransformerResponse);

        CorporateBodyAppointments returnedCorporateBodyAppointments = service.getEligibleOfficersEmergencyAuthCode(INCORPORATION_NUMBER);
        assertEquals(2, returnedCorporateBodyAppointments.getTotalResults());
        assertEquals(0, returnedCorporateBodyAppointments.getStartIndex());
        assertEquals(2, returnedCorporateBodyAppointments.getItemsPerPage());
    }

    private List<CorporateBodyAppointmentDataModel> getMockEmergencyAuthCodeRepo() {
        List<CorporateBodyAppointmentDataModel> corporateBodyAppointments = new ArrayList<>();
        corporateBodyAppointments.add(new CorporateBodyAppointmentDataModel());
        corporateBodyAppointments.add(new CorporateBodyAppointmentDataModel());

        return corporateBodyAppointments;
    }

    private List<CorporateBodyAppointment> getMockEmergencyAuthCodeTransformer() {
        List<CorporateBodyAppointment> corporateBodyAppointments = new ArrayList<>();
        corporateBodyAppointments.add(new CorporateBodyAppointment());
        corporateBodyAppointments.add(new CorporateBodyAppointment());

        return corporateBodyAppointments;
    }
}
