package uk.gov.ch.model.update.trusts;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.JsonNode;
import uk.gov.ch.model.AbstractJsonTest;

import static org.assertj.core.api.Assertions.assertThat;

class TrustLinkDataJsonTest extends AbstractJsonTest {

    private static final String TRUST_ID = "trust id";
    private static final String CORPORATE_BODY_APPOINTMENT_ID = "corporate-body appointment id";

    @Test
    void serializesToExpectedJsonStructure() throws Exception {
        TrustLinkData details = new TrustLinkData();
        details.setTrustId(TRUST_ID);
        details.setCorporateBodyAppointmentId(CORPORATE_BODY_APPOINTMENT_ID);

        JsonNode node = jsonMapper.valueToTree(details);

        try {
            assertThat(node.get("trustId").asString()).isEqualTo(TRUST_ID);
            assertThat(node.get("corporateBodyAppointmentId").asString()).isEqualTo(CORPORATE_BODY_APPOINTMENT_ID);
        } catch (Throwable e) {
            System.out.println("RAW JSON: " + jsonMapper.writeValueAsString(details));
            throw e;
        }
    }
}

