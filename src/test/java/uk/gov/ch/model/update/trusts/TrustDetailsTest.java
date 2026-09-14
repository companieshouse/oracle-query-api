package uk.gov.ch.model.update.trusts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;

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
    void setUp() {
        trustDetails = new TrustDetails();
        trustDetails.setTrustId("123");
        trustDetails.setTrustName("My trust");
    }

    @Test
    @DisplayName("TrustDetails Serialisation Test")
    void serialisationTest() throws JacksonException {
        JsonMapper mapper = new JsonMapper();

        String output = mapper.writeValueAsString(trustDetails);
        assertEquals(mapper.readTree(JSON_STRING), mapper.readTree(output));
    }

    @Test
    @DisplayName("TrustDetails Deserialisation Test")
    void deserialisationTest() throws JacksonException {
        JsonMapper mapper = new JsonMapper();

        TrustDetails output = mapper.readValue(JSON_STRING, TrustDetails.class);

        assertEquals(trustDetails, output);
    }
}
