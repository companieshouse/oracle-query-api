package uk.gov.ch.model.update.trusts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CorporateTrusteeDataTest {
    private static final String JSON_STRING = "{"
            + "\"trusteeId\":1,"
            + "\"trusteeName\":\"Trustee Name\","
            + "\"registerLocation\":\"Location\","
            + "\"registrationNumber\":\"123456\","
            + "\"lawGoverned\":\"Law\","
            + "\"legalForm\":\"Form\","
            + "\"country\":\"Never Never Land\","
            + "\"onRegisterInCountryFormed\":true,"
            + "\"corporateIndicator\":\"Corporate\","
            + "\"trusteeTypeId\":3,"
            + "\"appointmentDate\":\"2023-09-15\","
            + "\"ceasedDate\":\"2023-09-16\""
            + "}";
    private CorporateTrusteeData corporateTrusteeData;

    @BeforeEach
    public void setUp() {
        corporateTrusteeData = new CorporateTrusteeData();

        corporateTrusteeData.setTrusteeId(1L);
        corporateTrusteeData.setTrusteeName("Trustee Name");
        corporateTrusteeData.setRegisterLocation("Location");
        corporateTrusteeData.setRegistrationNumber("123456");
        corporateTrusteeData.setLawGoverned("Law");
        corporateTrusteeData.setLegalForm("Form");
        corporateTrusteeData.setCountry("Never Never Land");
        corporateTrusteeData.setOnRegisterInCountryFormed(true);
        corporateTrusteeData.setCorporateInd("Corporate");
        corporateTrusteeData.setTrusteeTypeId(3L);
        corporateTrusteeData.setAppointmentDate("2023-09-15");
        corporateTrusteeData.setCeasedDate("2023-09-16");
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