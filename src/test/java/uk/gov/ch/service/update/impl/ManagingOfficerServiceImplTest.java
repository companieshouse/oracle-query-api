package uk.gov.ch.service.update.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import uk.gov.ch.exception.ManagingOfficerCountNotFoundException;
import uk.gov.ch.model.update.OverseasEntityManagingOfficerData;
import uk.gov.ch.repository.update.OverseasEntityManagingOfficersRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ManagingOfficerServiceImplTest {

    @InjectMocks
    private OverseasEntityManagingOfficersServiceImpl managingOfficerDataService;

    @Mock
    private OverseasEntityManagingOfficersRepository managingOfficerDataRepository;

    private static final String INCORPORATION_NUMBER = "OE123456";
    private static final String CONTACT_EMAIL_ADDRESS = "contact_email_address";
    private static final String DATE_OF_BIRTH = "date_of_birth";
    private static final String MANAGING_OFFICER_ID = "managing_officer_id";
    private static final String CONTACT_NAME_FULL = "contact_name_full";
    private static final String ADDRESS_LINE_1 = "address_line_1";
    private static final String ADDRESS_LINE_2 = "address_line_2";
    private static final String CARE_OF = "care_of";
    private static final String COUNTRY = "country";
    private static final String LOCALITY = "locality";
    private static final String PO_BOX = "po_box";
    private static final String POSTAL_CODE = "postal_code";
    private static final String PREMISES = "premises";
    private static final String REGION = "region";

    @Test
    @DisplayName("Get managing officers - managing officers returned")
    void testGetManagingOfficerData() throws ManagingOfficerCountNotFoundException {
        List<OverseasEntityManagingOfficerData> expectedList = getMockManagingOfficerRepo(2);

        when(managingOfficerDataRepository.getOverseasEntityManagingOfficers(INCORPORATION_NUMBER))
                .thenReturn(expectedList);
        List<OverseasEntityManagingOfficerData> result = managingOfficerDataService.getOverseasEntityManagingOfficers(INCORPORATION_NUMBER);
        assertEquals(2, result.size());
        assertEquals(CONTACT_EMAIL_ADDRESS, result.get(0).getContactEmailAddress());
        assertEquals(CONTACT_NAME_FULL, result.get(0).getContactNameFull());
        assertEquals(DATE_OF_BIRTH, result.get(0).getDateOfBirth());
        assertEquals(MANAGING_OFFICER_ID, result.get(0).getManagingOfficerId());
        assertEquals(ADDRESS_LINE_1, result.get(0).getResidentialAddress().getAddressLine1());
        assertEquals(ADDRESS_LINE_2, result.get(0).getResidentialAddress().getAddressLine2());
        assertEquals(CARE_OF, result.get(0).getResidentialAddress().getCareOf());
        assertEquals(COUNTRY, result.get(0).getResidentialAddress().getCountry());
        assertEquals(LOCALITY, result.get(0).getResidentialAddress().getLocality());
        assertEquals(PO_BOX, result.get(0).getResidentialAddress().getPoBox());
        assertEquals(POSTAL_CODE, result.get(0).getResidentialAddress().getPostalCode());
        assertEquals(PREMISES, result.get(0).getResidentialAddress().getPremises());
        assertEquals(REGION, result.get(0).getResidentialAddress().getRegion());
    }

    @Test
    @DisplayName("Get managing officers - no managing officers returned")
    void testGetManagingOfficerDataNoData() throws ManagingOfficerCountNotFoundException {
        List<OverseasEntityManagingOfficerData> expectedList = getMockManagingOfficerRepo(0);
        when(managingOfficerDataRepository.getOverseasEntityManagingOfficers(INCORPORATION_NUMBER))
                .thenReturn(expectedList);
        Assertions.assertThrows(ManagingOfficerCountNotFoundException.class,
                () -> managingOfficerDataService.getOverseasEntityManagingOfficers(INCORPORATION_NUMBER));
    }

    List<OverseasEntityManagingOfficerData> getMockManagingOfficerRepo(int count) {
        List<OverseasEntityManagingOfficerData> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            OverseasEntityManagingOfficerData mo = new OverseasEntityManagingOfficerData();
            mo.setContactEmailAddress(CONTACT_EMAIL_ADDRESS);
            mo.setContactNameFull(CONTACT_NAME_FULL);
            mo.setDateOfBirth(DATE_OF_BIRTH);
            mo.setManagingOfficerId(MANAGING_OFFICER_ID);
            mo.setAddressLine1(ADDRESS_LINE_1);
            mo.setAddressLine2(ADDRESS_LINE_2);
            mo.setCareOf(CARE_OF);
            mo.setCountryName(COUNTRY);
            mo.setLocality(LOCALITY);
            mo.setPoBox(PO_BOX);
            mo.setPostalCode(POSTAL_CODE);
            mo.setPremises(PREMISES);
            mo.setRegion(REGION);
            list.add(mo);
        }
        return list;
    }
}
