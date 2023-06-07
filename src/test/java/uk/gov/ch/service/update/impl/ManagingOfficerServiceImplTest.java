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

    // Residential address constants
    private static final String RESIDENTIAL_ADDRESS_LINE_1 = "residential_address_line_1";
    private static final String RESIDENTIAL_ADDRESS_LINE_2 = "residential_address_line_2";
    private static final String RESIDENTIAL_CARE_OF = "residential_care_of";
    private static final String RESIDENTIAL_COUNTRY = "residential_country";
    private static final String RESIDENTIAL_POST_TOWN = "residential_post_town";
    private static final String RESIDENTIAL_PO_BOX = "residential_po_box";
    private static final String RESIDENTIAL_POSTAL_CODE = "residential_postal_code";
    private static final String RESIDENTIAL_PREMISES = "residential_premises";
    private static final String RESIDENTIAL_REGION = "residential_region";

    // Principal address constants
    private static final String PRINCIPAL_PREMISES = "principal_premises";
    private static final String PRINCIPAL_ADDRESS_LINE_1 = "principal_address_line_1";
    private static final String PRINCIPAL_ADDRESS_LINE_2 = "principal_address_line_2";
    private static final String PRINCIPAL_LOCALITY = "principal_locality";
    private static final String PRINCIPAL_REGION = "principal_region";
    private static final String PRINCIPAL_COUNTRY = "principal_country";
    private static final String PRINCIPAL_POST_TOWN = "principal_post_town";
    private static final String PRINCIPAL_POSTAL_CODE = "principal_postal_code";
    private static final String PRINCIPAL_PO_BOX = "principal_po_box";
    private static final String PRINCIPAL_CARE_OF = "principal_care_of";

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
        assertEquals(MANAGING_OFFICER_ID, result.get(0).getManagingOfficerAppointmentId());

        // Check residential address fields
        assertEquals(RESIDENTIAL_ADDRESS_LINE_1, result.get(0).getResidentialAddress().getAddressLine1());
        assertEquals(RESIDENTIAL_ADDRESS_LINE_2, result.get(0).getResidentialAddress().getAddressLine2());
        assertEquals(RESIDENTIAL_CARE_OF, result.get(0).getResidentialAddress().getCareOf());
        assertEquals(RESIDENTIAL_COUNTRY, result.get(0).getResidentialAddress().getCountry());
        assertEquals(RESIDENTIAL_PO_BOX, result.get(0).getResidentialAddress().getPoBox());
        assertEquals(RESIDENTIAL_POST_TOWN, result.get(0).getResidentialAddress().getLocality());
        assertEquals(RESIDENTIAL_POSTAL_CODE, result.get(0).getResidentialAddress().getPostalCode());
        assertEquals(RESIDENTIAL_PREMISES, result.get(0).getResidentialAddress().getPremises());
        assertEquals(RESIDENTIAL_REGION, result.get(0).getResidentialAddress().getRegion());

        // Check principal address fields
        assertEquals(PRINCIPAL_PREMISES, result.get(0).getPrincipalAddress().getPremises());
        assertEquals(PRINCIPAL_ADDRESS_LINE_1, result.get(0).getPrincipalAddress().getAddressLine1());
        assertEquals(PRINCIPAL_ADDRESS_LINE_2, result.get(0).getPrincipalAddress().getAddressLine2());
        assertEquals(PRINCIPAL_REGION, result.get(0).getPrincipalAddress().getRegion());
        assertEquals(PRINCIPAL_COUNTRY, result.get(0).getPrincipalAddress().getCountry());
        assertEquals(PRINCIPAL_POSTAL_CODE, result.get(0).getPrincipalAddress().getPostalCode());
        assertEquals(PRINCIPAL_PO_BOX, result.get(0).getPrincipalAddress().getPoBox());
        assertEquals(PRINCIPAL_CARE_OF, result.get(0).getPrincipalAddress().getCareOf());
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
            mo.setManagingOfficerAppointmentId(MANAGING_OFFICER_ID);
            mo.setResidentialAddressLine1(RESIDENTIAL_ADDRESS_LINE_1);
            mo.setResidentialAddressLine2(RESIDENTIAL_ADDRESS_LINE_2);
            mo.setResidentialCareOf(RESIDENTIAL_CARE_OF);
            mo.setResidentialCountryName(RESIDENTIAL_COUNTRY);
            mo.setResidentialPostTown(RESIDENTIAL_POST_TOWN);
            mo.setResidentialPoBox(RESIDENTIAL_PO_BOX);
            mo.setResidentialPostalCode(RESIDENTIAL_POSTAL_CODE);
            mo.setResidentialPremises(RESIDENTIAL_PREMISES);
            mo.setResidentialRegion(RESIDENTIAL_REGION);
            mo.setPrincipalPremises(PRINCIPAL_PREMISES);
            mo.setPrincipalAddressLine1(PRINCIPAL_ADDRESS_LINE_1);
            mo.setPrincipalAddressLine2(PRINCIPAL_ADDRESS_LINE_2);
            mo.setPrincipalRegion(PRINCIPAL_REGION);
            mo.setPrincipalCountryName(PRINCIPAL_COUNTRY);
            mo.setPrincipalPostalCode(PRINCIPAL_POSTAL_CODE);
            mo.setPrincipalPoBox(PRINCIPAL_PO_BOX);
            mo.setPrincipalCareOf(PRINCIPAL_CARE_OF);
            list.add(mo);
        }
        return list;
    }
}
