package uk.gov.ch.model.officer.active;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.companieshouse.api.model.common.Address;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OfficerBaseTest {

    private static class TestOfficerBase extends OfficerBase {}

    private OfficerBase officerBaseUnderTest;

    @BeforeEach
    void setUp() {
        officerBaseUnderTest = new TestOfficerBase();
        officerBaseUnderTest.setOfficerDetailId(0L);
        officerBaseUnderTest.setDateOfBirth("1970-01-01");
        officerBaseUnderTest.setDateOfAppointment("1984-12-30");
        officerBaseUnderTest.setServiceAddressLine1("serviceAddressLine1");
        officerBaseUnderTest.setServiceAddressLine2("serviceAddressLine2");
        officerBaseUnderTest.setServiceAddressCareOf("serviceAddressCareOf");
        officerBaseUnderTest.setServiceAddressCountry("serviceAddressCountry");
        officerBaseUnderTest.setServiceAddressLocality("serviceAddressLocality");
        officerBaseUnderTest.setServiceAddressPoBox("serviceAddressPoBox");
        officerBaseUnderTest.setServiceAddressPostCode("serviceAddressPostCode");
        officerBaseUnderTest.setServiceAddressRegion("serviceAddressRegion");
        officerBaseUnderTest.setResidentialAddressLine1("residentialAddressLine1");
        officerBaseUnderTest.setResidentialAddressLine2("residentialAddressLine2");
        officerBaseUnderTest.setResidentialAddressCareOf("residentialAddressCareOf");
        officerBaseUnderTest.setResidentialAddressCountry("residentialAddressCountry");
        officerBaseUnderTest.setResidentialAddressLocality("residentialAddressLocality");
        officerBaseUnderTest.setResidentialAddressPoBox("residentialAddressPoBox");
        officerBaseUnderTest.setResidentialAddressPostCode("residentialAddressPostCode");
        officerBaseUnderTest.setResidentialAddressRegion("residentialAddressRegion");
        officerBaseUnderTest.setSecureIndicator("secureIndicator");
    }

    @Test
    void testForeName1GetterAndSetter() {
        final String foreName1 = "foreName1";
        officerBaseUnderTest.setForeName1(foreName1);
        assertEquals(foreName1,officerBaseUnderTest.getForeName1());
    }

    @Test
    void testForeName2GetterAndSetter() {
        final String foreName2 = "foreName2";
        officerBaseUnderTest.setForeName2(foreName2);
        assertEquals(foreName2,officerBaseUnderTest.getForeName2());
    }

    @Test
    void testSurnameGetterAndSetter() {
        final String surname = "surname";
        officerBaseUnderTest.setSurname(surname);
        assertEquals(surname,officerBaseUnderTest.getSurname());
    }

    @Test
    void testOccupationGetterAndSetter() {
        final String occupation = "occupation";
        officerBaseUnderTest.setOccupation(occupation);
        assertEquals(occupation,officerBaseUnderTest.getOccupation());
    }

    @Test
    void testNationalityGetterAndSetter() {
        final String nationality = "nationality";
        officerBaseUnderTest.setNationality(nationality);
        assertEquals(nationality,officerBaseUnderTest.getNationality());
    }

    @Test
    void testGetDateOfBirth() throws Exception {
        assertEquals("01 January 1970",officerBaseUnderTest.getDateOfBirth());
    }

    @Test
    void testCountryOfResidenceGetterAndSetter() {
        final String countryOfResidence = "countryOfResidence";
        officerBaseUnderTest.setCountryOfResidence(countryOfResidence);
        assertEquals(countryOfResidence,officerBaseUnderTest.getCountryOfResidence());
    }

    @Test
    void testGetDateOfAppointment() throws Exception {
        assertEquals("30 December 1984",officerBaseUnderTest.getDateOfAppointment());
    }

    @Test
    void testGetServiceAddress() {
        final Address result = officerBaseUnderTest.getServiceAddress();
        assertEquals("serviceAddressLine1",result.getAddressLine1());
        assertEquals("serviceAddressPostCode",result.getPostalCode());
    }

    @Test
    void testGetResidentialAddress() {
        final Address result = officerBaseUnderTest.getResidentialAddress();
        assertEquals("residentialAddressLine1",result.getAddressLine1());
        assertEquals("residentialAddressPostCode",result.getPostalCode());
    }
}
