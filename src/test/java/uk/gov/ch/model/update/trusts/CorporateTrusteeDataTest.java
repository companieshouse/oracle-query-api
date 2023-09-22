package uk.gov.ch.model.update.trusts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.companieshouse.api.model.common.Address;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CorporateTrusteeDataTest {

    private static final String JSON_STRING =
            "{" +
                "\"trusteeId\":\"123\"," +
                "\"trusteeName\":\"Some Corp Name\"," +
                "\"registerLocation\":\"London\"," +
                "\"registrationNumber\":\"REG123\"," +
                "\"lawGoverned\":\"English Law\"," +
                "\"legalForm\":\"LLP\"," +
                "\"country\":\"UK\"," +
                "\"onRegisterInCountryFormed\":true," +
                "\"corporateIndicator\":\"Y\"," +
                "\"trusteeTypeId\":1," +
                "\"appointmentDate\":\"2022-01-01\"," +
                "\"ceasedDate\":\"2023-01-01\"," +
                "\"serviceAddress\":{" +
                    "\"address_line_1\":\"Suite 100\"," +
                    "\"address_line_2\":\"Downtown\"," +
                    "\"care_of\":null," +
                    "\"country\":\"UK\"," +
                    "\"locality\":\"London\"," +
                    "\"po_box\":null," +
                    "\"postal_code\":\"NW1 1AA\"," +
                    "\"premises\":\"123 Main St\"," +
                    "\"region\":\"Greater London\"" +
                "}," +
                "\"registeredOfficeAddress\":{" +
                    "\"address_line_1\":\"Apt 200\"," +
                    "\"address_line_2\":\"Uptown\"," +
                    "\"care_of\":null," +
                    "\"country\":\"UK\"," +
                    "\"locality\":\"Manchester\"," +
                    "\"po_box\":null," +
                    "\"postal_code\":\"M1 2AB\"," +
                    "\"premises\":\"456 Secondary Rd\"," +
                    "\"region\":\"Greater Manchester\"" +
                "}" +
            "}";

    private CorporateTrusteeData corporateTrusteeData;

    @BeforeEach
    public void setUp() {
        corporateTrusteeData = new CorporateTrusteeData();
        corporateTrusteeData.setTrusteeId("123");
        corporateTrusteeData.setTrusteeName("Some Corp Name");
        corporateTrusteeData.setRegisterLocation("London");
        corporateTrusteeData.setRegistrationNumber("REG123");
        corporateTrusteeData.setLawGoverned("English Law");
        corporateTrusteeData.setLegalForm("LLP");
        corporateTrusteeData.setCountry("UK");
        corporateTrusteeData.setOnRegisterInCountryFormed(true);
        corporateTrusteeData.setCorporateInd("Y");
        corporateTrusteeData.setTrusteeTypeId(1L);
        corporateTrusteeData.setAppointmentDate("2022-01-01");
        corporateTrusteeData.setCeasedDate("2023-01-01");

        Address serviceAddress = new Address();
        serviceAddress.setPremises("123 Main St");
        serviceAddress.setAddressLine1("Suite 100");
        serviceAddress.setAddressLine2("Downtown");
        serviceAddress.setLocality("London");
        serviceAddress.setRegion("Greater London");
        serviceAddress.setCountry("UK");
        serviceAddress.setPostalCode("NW1 1AA");

        Address registeredOffice = new Address();
        registeredOffice.setPremises("456 Secondary Rd");
        registeredOffice.setAddressLine1("Apt 200");
        registeredOffice.setAddressLine2("Uptown");
        registeredOffice.setLocality("Manchester");
        registeredOffice.setRegion("Greater Manchester");
        registeredOffice.setCountry("UK");
        registeredOffice.setPostalCode("M1 2AB");

        corporateTrusteeData.setServiceAddress(serviceAddress);
        corporateTrusteeData.setRegisteredOfficeAddress(registeredOffice);
    }

    @Test
    @DisplayName("CorporateTrusteeData Serialisation Test")
    void serialisationTest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String output = mapper.writeValueAsString(corporateTrusteeData);
        assertEquals(JSON_STRING, output);
    }

    @Test
    @DisplayName("CorporateTrusteeData Deserialisation Test")
    void deserialisationTest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        CorporateTrusteeData output = mapper.readValue(JSON_STRING, CorporateTrusteeData.class);
        assertEquals(corporateTrusteeData, output);
    }
}
