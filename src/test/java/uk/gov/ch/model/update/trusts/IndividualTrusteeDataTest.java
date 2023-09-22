package uk.gov.ch.model.update.trusts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.companieshouse.api.model.common.Address;


class IndividualTrusteeDataTest {
    private static final String JSON_STRING =
            "{" +
                "\"trusteeId\":\"123\"," +
                "\"trusteeForename1\":\"Bob\"," +
                "\"trusteeForename2\":\"Billy\"," +
                "\"trusteeSurname\":\"Bobby\"," +
                "\"dateOfBirth\":\"1990-01-01\"," +
                "\"nationality\":\"British\"," +
                "\"corporateIndicator\":\"Y\"," +
                "\"trusteeTypeId\":\"TYPE1\"," +
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
                "\"usualResidentialAddress\":{" +
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


    private IndividualTrusteeData individualTrusteeData;

    @BeforeEach
    public void setUp() {
        individualTrusteeData = new IndividualTrusteeData();
        individualTrusteeData.setTrusteeId("123");
        individualTrusteeData.setTrusteeForename1("Bob");
        individualTrusteeData.setTrusteeForename2("Billy");
        individualTrusteeData.setTrusteeSurname("Bobby");
        individualTrusteeData.setDateOfBirth("1990-01-01");
        individualTrusteeData.setNationality("British");
        individualTrusteeData.setCorporateIndicator("Y");
        individualTrusteeData.setTrusteeTypeId("TYPE1");
        individualTrusteeData.setAppointmentDate("2022-01-01");
        individualTrusteeData.setCeasedDate("2023-01-01");

        Address serviceAddress = new Address();
        serviceAddress.setPremises("123 Main St");
        serviceAddress.setAddressLine1("Suite 100");
        serviceAddress.setAddressLine2("Downtown");
        serviceAddress.setLocality("London");
        serviceAddress.setRegion("Greater London");
        serviceAddress.setCountry("UK");
        serviceAddress.setPostalCode("NW1 1AA");

        Address residentialAddress = new Address();
        residentialAddress.setPremises("456 Secondary Rd");
        residentialAddress.setAddressLine1("Apt 200");
        residentialAddress.setAddressLine2("Uptown");
        residentialAddress.setLocality("Manchester");
        residentialAddress.setRegion("Greater Manchester");
        residentialAddress.setCountry("UK");
        residentialAddress.setPostalCode("M1 2AB");

        individualTrusteeData.setServiceAddress(serviceAddress);
        individualTrusteeData.setUsualResidentialAddress(residentialAddress);

    }

    @Test
    @DisplayName("IndividualTrusteeData Serialisation Test")
    void serialisationTest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String output = mapper.writeValueAsString(individualTrusteeData);
        assertEquals(JSON_STRING, output);
    }

    @Test
    @DisplayName("IndividualTrusteeData Deserialisation Test")
    void deserialisationTest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        IndividualTrusteeData output = mapper.readValue(JSON_STRING, IndividualTrusteeData.class);
        assertEquals(individualTrusteeData, output);
    }
}
