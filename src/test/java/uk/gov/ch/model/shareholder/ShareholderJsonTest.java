package uk.gov.ch.model.shareholder;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.JsonNode;
import uk.gov.ch.model.AbstractJsonTest;

import static org.assertj.core.api.Assertions.assertThat;

class ShareholderJsonTest extends AbstractJsonTest {


    private static final Long SHAREHOLDER_ID = 999L;
    private static final String FORENAME1 = "forename1";
    private static final String FORENAME2 = "forename2";
    private static final String SURNAME = "surname";
    private static final long SHARES = 100L;
    private static final String CLASS_OF_SHARES = "class of shares";
    private static final String CURRENCY = "currency";

    @Test
    void serializesToExpectedJsonStructure() {
        Shareholder details = new Shareholder();
        details.setShareholderId(SHAREHOLDER_ID);
        details.setForename1(FORENAME1);
        details.setForename2(FORENAME2);
        details.setSurname(SURNAME);
        details.setShares(SHARES);
        details.setClassOfShares(CLASS_OF_SHARES);
        details.setCurrency(CURRENCY);

        JsonNode node = jsonMapper.valueToTree(details);

        try {
            assertThat(node.has("shareholderId")).isFalse();
            assertThat(node.get("fore_name_1").asString()).isEqualTo(FORENAME1);
            assertThat(node.get("fore_name_2").asString()).isEqualTo(FORENAME2);
            assertThat(node.get("surname").asString()).isEqualTo(SURNAME);
            assertThat(node.get("shares").asString()).isEqualTo(String.valueOf(SHARES));
            assertThat(node.get("class_of_shares").asString()).isEqualTo(CLASS_OF_SHARES);
            assertThat(node.get("currency").asString()).isEqualTo(CURRENCY);
        } catch (AssertionError e) {
            System.out.println("RAW JSON: " + jsonMapper.writeValueAsString(details));
            throw e;
        }
    }
}

