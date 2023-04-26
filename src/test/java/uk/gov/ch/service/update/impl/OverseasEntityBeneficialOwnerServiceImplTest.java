package uk.gov.ch.service.update.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.ch.exception.BeneficialOwnerCountNotFoundException;
import uk.gov.ch.model.update.OverseasEntityBeneficialOwner;
import uk.gov.ch.repository.update.OverseasEntityBeneficialOwnerRepository;
import uk.gov.companieshouse.api.model.common.Address;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OverseasEntityBeneficialOwnerServiceImplTest {
    @InjectMocks
    private OverseasEntityBeneficialOwnerServiceImpl overseasEntityBeneficialOwnerService;

    @Mock
    private OverseasEntityBeneficialOwnerRepository overseasEntityBeneficialOwnerRepository;

    private static final String COMPANY_NUMBER = "OE123456";

    @Test
    @DisplayName("Get beneficial owners - expected data returned")
    void testGetBeneficialOwnersData() throws BeneficialOwnerCountNotFoundException {
        List<OverseasEntityBeneficialOwner> expectedList = getMockBeneficialOwners(2);
        Address testAddress = getTestAddress();

        when(overseasEntityBeneficialOwnerRepository.getBeneficialOwners(COMPANY_NUMBER))
                .thenReturn(expectedList);
        List<OverseasEntityBeneficialOwner> result =
                overseasEntityBeneficialOwnerService.getBeneficialOwners(COMPANY_NUMBER);

        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("1990/01/01", result.get(0).getDateBecameRegistrable());
        assertEquals("1985/01/01", result.get(0).getDateOfBirth());
        assertEquals("N", result.get(0).getIsServiceAddressSameAsUsualAddress());
        
        assertEquals(testAddress.getAddressLine1(), result.get(0).getUsualResidentialAddress().getAddressLine1());
        assertEquals(testAddress.getAddressLine2(), result.get(0).getUsualResidentialAddress().getAddressLine2());
        assertEquals(testAddress.getCareOf(), result.get(0).getUsualResidentialAddress().getCareOf());
        assertEquals(testAddress.getCountry(), result.get(0).getUsualResidentialAddress().getCountry());
        assertEquals(testAddress.getLocality(), result.get(0).getUsualResidentialAddress().getLocality());
        assertEquals(testAddress.getPoBox(), result.get(0).getUsualResidentialAddress().getPoBox());
        assertEquals(testAddress.getPostalCode(), result.get(0).getUsualResidentialAddress().getPostalCode());
        assertEquals(testAddress.getPremises(), result.get(0).getUsualResidentialAddress().getPremises());
        assertEquals(testAddress.getRegion(), result.get(0).getUsualResidentialAddress().getRegion());
        
        assertEquals(testAddress.getAddressLine1(), result.get(0).getPrincipalAddress().getAddressLine1());
        assertEquals(testAddress.getAddressLine2(), result.get(0).getPrincipalAddress().getAddressLine2());
        assertEquals(testAddress.getCareOf(), result.get(0).getPrincipalAddress().getCareOf());
        assertEquals(testAddress.getCountry(), result.get(0).getPrincipalAddress().getCountry());
        assertEquals(testAddress.getLocality(), result.get(0).getPrincipalAddress().getLocality());
        assertEquals(testAddress.getPoBox(), result.get(0).getPrincipalAddress().getPoBox());
        assertEquals(testAddress.getPostalCode(), result.get(0).getPrincipalAddress().getPostalCode());
        assertEquals(testAddress.getPremises(), result.get(0).getPrincipalAddress().getPremises());
        assertEquals(testAddress.getRegion(), result.get(0).getPrincipalAddress().getRegion());
    }

    @Test
    @DisplayName("Get beneficial owners - no beneficial owners returned")
    void testGetBeneficialOwnersNoData() throws BeneficialOwnerCountNotFoundException {
        List<OverseasEntityBeneficialOwner> expectedList = getMockBeneficialOwners(0);
        when(overseasEntityBeneficialOwnerRepository.getBeneficialOwners(COMPANY_NUMBER)).thenReturn(expectedList);

        Assertions.assertThrows(BeneficialOwnerCountNotFoundException.class,
                () -> overseasEntityBeneficialOwnerService.getBeneficialOwners(COMPANY_NUMBER));
    }

    private List<OverseasEntityBeneficialOwner> getMockBeneficialOwners(int count) {
        List<OverseasEntityBeneficialOwner> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            OverseasEntityBeneficialOwner bo = new OverseasEntityBeneficialOwner();
            bo.setId("1");
            bo.setDateBecameRegistrable("1990/01/01");
            bo.setDateOfBirth("1985/01/01");
            
            bo.setIsServiceAddressSameAsUsualAddress("N");
            bo.setResidentialAddressLine1("TEST LINE 1");
            bo.setResidentialAddressLine2("TEST LINE 2");
            bo.setResidentialAddressCareOf("TEST CARE OF");
            bo.setResidentialAddressCountryName("TEST COUNTRY");
            bo.setResidentialAddressPostTown("TEST POST TOWN");
            bo.setResidentialAddressPoBox("TEST PO BOX");
            bo.setResidentialAddressPostCode("TEST POST CODE");
            bo.setResidentialAddressHouseNameNumber("TEST HOUSE NAME NUMBER");
            bo.setResidentialAddressRegion("TEST REGION");
            
            bo.setPrincipalAddressLine1("TEST LINE 1");
            bo.setPrincipalAddressLine2("TEST LINE 2");
            bo.setPrincipalAddressCareOf("TEST CARE OF");
            bo.setPrincipalAddressCountryName("TEST COUNTRY");
            bo.setPrincipalAddressPostTown("TEST POST TOWN");
            bo.setPrincipalAddressPoBox("TEST PO BOX");
            bo.setPrincipalAddressPostCode("TEST POST CODE");
            bo.setPrincipalAddressHouseNameNumber("TEST HOUSE NAME NUMBER");
            bo.setPrincipalAddressRegion("TEST REGION");

            list.add(bo);
        }
        return list;
    }

    private Address getTestAddress() {
        Address testAddress = new Address();
        testAddress.setAddressLine1("TEST LINE 1");
        testAddress.setAddressLine2("TEST LINE 2");
        testAddress.setCareOf("TEST CARE OF");
        testAddress.setCountry("TEST COUNTRY");
        testAddress.setLocality("TEST POST TOWN");
        testAddress.setPoBox("TEST PO BOX");
        testAddress.setPostalCode("TEST POST CODE");
        testAddress.setPremises("TEST HOUSE NAME NUMBER");
        testAddress.setRegion("TEST REGION");
        return testAddress;
    }
}
