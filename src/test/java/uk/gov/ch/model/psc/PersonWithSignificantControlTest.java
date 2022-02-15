package uk.gov.ch.model.psc;

import org.junit.jupiter.api.Test;
import uk.gov.companieshouse.api.model.common.Address;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonWithSignificantControlTest {

    private static String HOUSE_NAME_NUMBER = "19";
    private static String STREET = "My Street";
    private static String CARE_OF = "care of";
    private static String COUNTRY_NAME = "England";
    private static String POST_TOWN = "London";
    private static String PO_BOX = "PO box";
    private static String POST_CODE = "LW1 1AA";
    private static String REGION = "region";

    @Test
    void testUraAddress() {
        PersonWithSignificantControl psc = new PersonWithSignificantControl();
        psc.setHouseNameNumber(HOUSE_NAME_NUMBER);
        psc.setStreet(STREET);
        psc.setCareOf(CARE_OF);
        psc.setCountryName(COUNTRY_NAME);
        psc.setPostTown(POST_TOWN);
        psc.setPoBox(PO_BOX);
        psc.setPostCode(POST_CODE);
        psc.setRegion(REGION);

        Address ura = psc.getAddress();
        assertEquals(HOUSE_NAME_NUMBER, ura.getAddressLine1());
        assertEquals(STREET, ura.getAddressLine2());
        assertEquals(CARE_OF, ura.getCareOf());
        assertEquals(COUNTRY_NAME, ura.getCountry());
        assertEquals(POST_TOWN, ura.getLocality());
        assertEquals(PO_BOX, ura.getPoBox());
        assertEquals(POST_CODE, ura.getPostalCode());
        assertEquals(REGION, ura.getRegion());
    }

    @Test
    void testServiceAddress() {
        PersonWithSignificantControl psc = new PersonWithSignificantControl();
        psc.setServiceAddressLine1(HOUSE_NAME_NUMBER);
        psc.setServiceAddressLine2(STREET);
        psc.setServiceAddressCareOf(CARE_OF);
        psc.setServiceAddressCountryName(COUNTRY_NAME);
        psc.setServiceAddressPostTown(POST_TOWN);
        psc.setServiceAddressPoBox(PO_BOX);
        psc.setServiceAddressPostCode(POST_CODE);
        psc.setServiceAddressRegion(REGION);

        Address serviceAddress = psc.getServiceAddress();

        assertEquals(HOUSE_NAME_NUMBER, serviceAddress.getAddressLine1());
        assertEquals(STREET, serviceAddress.getAddressLine2());
        assertEquals(CARE_OF, serviceAddress.getCareOf());
        assertEquals(COUNTRY_NAME, serviceAddress.getCountry());
        assertEquals(POST_TOWN, serviceAddress.getLocality());
        assertEquals(PO_BOX, serviceAddress.getPoBox());
        assertEquals(POST_CODE, serviceAddress.getPostalCode());
        assertEquals(REGION, serviceAddress.getRegion());
    }
}
