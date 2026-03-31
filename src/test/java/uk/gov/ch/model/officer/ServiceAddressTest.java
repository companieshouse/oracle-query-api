package uk.gov.ch.model.officer;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServiceAddressTest {

    private ServiceAddress serviceAddressUnderTest;

    @BeforeEach
    void setUp() {
        serviceAddressUnderTest = new ServiceAddress();
    }

    @Test
    void testAddressLine1GetterAndSetter() {
        final String addressLine1 = "addressLine1";
        serviceAddressUnderTest.setAddressLine1(addressLine1);
        assertThat(serviceAddressUnderTest.getAddressLine1()).isEqualTo(addressLine1);
    }

    @Test
    void testAddressLine2GetterAndSetter() {
        final String addressLine2 = "addressLine2";
        serviceAddressUnderTest.setAddressLine2(addressLine2);
        assertThat(serviceAddressUnderTest.getAddressLine2()).isEqualTo(addressLine2);
    }

    @Test
    void testPostalCodeGetterAndSetter() {
        final String postalCode = "postalCode";
        serviceAddressUnderTest.setPostalCode(postalCode);
        assertThat(serviceAddressUnderTest.getPostalCode()).isEqualTo(postalCode);
    }

    @Test
    void testCareOfNameGetterAndSetter() {
        final String careOfName = "careOfName";
        serviceAddressUnderTest.setCareOfName(careOfName);
        assertThat(serviceAddressUnderTest.getCareOfName()).isEqualTo(careOfName);
    }

    @Test
    void testCountryGetterAndSetter() {
        final String country = "country";
        serviceAddressUnderTest.setCountry(country);
        assertThat(serviceAddressUnderTest.getCountry()).isEqualTo(country);
    }

    @Test
    void testLocalityGetterAndSetter() {
        final String locality = "locality";
        serviceAddressUnderTest.setLocality(locality);
        assertThat(serviceAddressUnderTest.getLocality()).isEqualTo(locality);
    }

    @Test
    void testPoBoxGetterAndSetter() {
        final String poBox = "poBox";
        serviceAddressUnderTest.setPoBox(poBox);
        assertThat(serviceAddressUnderTest.getPoBox()).isEqualTo(poBox);
    }

    @Test
    void testPremisesGetterAndSetter() {
        final String premises = "premises";
        serviceAddressUnderTest.setPremises(premises);
        assertThat(serviceAddressUnderTest.getPremises()).isEqualTo(premises);
    }

    @Test
    void testRegionGetterAndSetter() {
        final String region = "region";
        serviceAddressUnderTest.setRegion(region);
        assertThat(serviceAddressUnderTest.getRegion()).isEqualTo(region);
    }

    @Test
    void testUsualCountryOfResidenceGetterAndSetter() {
        final String usualCountryOfResidence = "usualCountryOfResidence";
        serviceAddressUnderTest.setUsualCountryOfResidence(usualCountryOfResidence);
        assertThat(serviceAddressUnderTest.getUsualCountryOfResidence()).isEqualTo(usualCountryOfResidence);
    }
}

