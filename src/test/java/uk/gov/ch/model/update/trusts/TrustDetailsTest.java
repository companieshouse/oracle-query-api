package uk.gov.ch.model.update.trusts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrustDetailsTest {
    private static final String JSON_STRING =
            "{"
            + "\"trustId\":\"123\","
            + "\"trustName\":\"My trust\","
            + "\"trustCreationDate\":null,"
            + "\"ceasedDate\":null,"
            + "\"unableToObtainAllInfoInd\":null"
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
        ObjectMapper mapper = new ObjectMapper();

        String output = mapper.writeValueAsString(trustDetails);
        assertEquals(JSON_STRING, output);
    }

    @Test
    @DisplayName("TrustDetails Deserialisation Test")
    void deserialisationTest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        TrustDetails output = mapper.readValue(JSON_STRING, TrustDetails.class);

        assertEquals(trustDetails, output);
    }
}
