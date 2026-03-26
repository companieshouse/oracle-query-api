package uk.gov.ch.model.update.trusts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrustDetailsTest {
    private static final String JSON_STRING =
            "{"
            + "\"trustId\":\"123\","
            + "\"trustName\":\"My trust\","
            + "\"creationDate\":null,"
            + "\"ceasedDate\":null,"
            + "\"unableToObtainAllInfoIndicator\":null"
            + "}";

    private TrustDetails trustDetails;

    @BeforeEach
    public void setUp() {
        trustDetails = new TrustDetails();
        trustDetails.setTrustId("123");
        trustDetails.setTrustName("My trust");
    }

    @Test
    @DisplayName("TrustDetails Serialisation Test")
    void serialisationTest() throws JsonProcessingException {
        JsonMapper mapper = new JsonMapper();

        String output = mapper.writeValueAsString(trustDetails);
        assertEquals(JSON_STRING, output);
    }

    @Test
    @DisplayName("TrustDetails Deserialisation Test")
    void deserialisationTest() throws JsonProcessingException {
        JsonMapper mapper = new JsonMapper();

        TrustDetails output = mapper.readValue(JSON_STRING, TrustDetails.class);

        assertEquals(trustDetails, output);
    }
}
