package uk.gov.ch.model.update.trusts;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TrustLinkDataTest {

    private static final String JSON_STRING =
            "{"
            + "\"trustId\":\"1\","
            + "\"corporateBodyAppointmentId\":\"123\""
            + "}";

    private TrustLinkData trustLinkData;

    @BeforeEach
    public void setUp() {
        trustLinkData = new TrustLinkData();
        trustLinkData.setTrustId("1");
        trustLinkData.setCorporateBodyAppointmentId("123");
    }

    @Test
    @DisplayName("TrustLinkData Serialisation Test")
    void serialisationTest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String output = mapper.writeValueAsString(trustLinkData);
        assertEquals(JSON_STRING, output);
    }

    @Test
    @DisplayName("CorporateTrusteeData Deserialisation Test")
    void deserialisationTest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        TrustLinkData output = mapper.readValue(JSON_STRING, TrustLinkData.class);

        assertEquals(trustLinkData, output);
    }
}
