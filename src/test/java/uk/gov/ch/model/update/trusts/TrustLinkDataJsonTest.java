package uk.gov.ch.model.update.trusts;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TrustLinkDataJsonTest {

    private static final String TRUST_ID = "trust id";
    private static final String CORPORATE_BODY_APPOINTMENT_ID = "corporate-body appointment id";

    private final ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

    @Test
    void serializesToExpectedJsonStructure() throws Exception {
        TrustLinkData details = new TrustLinkData();
        details.setTrustId(TRUST_ID);
        details.setCorporateBodyAppointmentId(CORPORATE_BODY_APPOINTMENT_ID);

        JsonNode node = objectMapper.valueToTree(details);

        try {
            assertThat(node.get("trustId").asText()).isEqualTo(TRUST_ID);
            assertThat(node.get("corporateBodyAppointmentId").asText()).isEqualTo(CORPORATE_BODY_APPOINTMENT_ID);
        // } catch (AssertionError e) {
        } catch (Throwable e) {
            System.out.println("RAW JSON: " + objectMapper.writeValueAsString(details));
            throw e;
        }
    }

    static String toJsonDate(LocalDate date) {
        return "[" + date.getYear() + "," + date.getMonthValue() + "," + date.getDayOfMonth() + "]";
    }
}

