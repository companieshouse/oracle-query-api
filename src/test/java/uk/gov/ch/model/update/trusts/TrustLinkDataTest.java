package uk.gov.ch.model.update.trusts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrustLinkDataTest {

    private static final String JSON_STRING =
            "{"
            + "\"trustId\":\"1\","
            + "\"corporateBodyAppointmentId\":\"123\""
            + "}";

    private TrustLinkData trustLinkData;

    @BeforeEach
    void setUp() {
        trustLinkData = new TrustLinkData();
        trustLinkData.setTrustId("1");
        trustLinkData.setCorporateBodyAppointmentId("123");
    }

    @Test
    @DisplayName("TrustLinkData Serialisation Test")
    void serialisationTest() throws JacksonException {
        JsonMapper mapper = new JsonMapper();

        String output = mapper.writeValueAsString(trustLinkData);
        assertEquals(mapper.readTree(JSON_STRING), mapper.readTree(output));
    }

    @Test
    @DisplayName("CorporateTrusteeData Deserialisation Test")
    void deserialisationTest() throws JacksonException {
        JsonMapper mapper = new JsonMapper();

        TrustLinkData output = mapper.readValue(JSON_STRING, TrustLinkData.class);

        assertEquals(trustLinkData, output);
    }
}
