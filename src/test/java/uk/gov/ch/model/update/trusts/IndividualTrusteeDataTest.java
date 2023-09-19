package uk.gov.ch.model.update.trusts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IndividualTrusteeDataTest {
    private static final String JSON_STRING =
            "{"
            + "\"trusteeId\":123,"
            + "\"trusteeForename1\":\"Bob\","
            + "\"trusteeForename2\":null,"
            + "\"trusteeSurname\":\"Bobby\","
            + "\"dateOfBirth\":null,"
            + "\"nationality\":null,"
            + "\"corporateIndicator\":null,"
            + "\"trusteeTypeId\":null,"
            + "\"appointmentDate\":null,"
            + "\"ceasedDate\":null" +
            "}";

    private IndividualTrusteeData individualTrusteeData;

    @BeforeEach
    public void setUp() {
        individualTrusteeData = new IndividualTrusteeData();
        individualTrusteeData.setTrusteeId(123L);
        individualTrusteeData.setTrusteeForename1("Bob");
        individualTrusteeData.setTrusteeSurname("Bobby");
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
