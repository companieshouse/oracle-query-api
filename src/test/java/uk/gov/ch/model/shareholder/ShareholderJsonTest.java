package uk.gov.ch.model.shareholder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ShareholderJsonTest {


    private static final Long SHAREHOLDER_ID = 999L;
    private static final String FORENAME1 = "forename1";
    private static final String FORENAME2 = "forename2";
    private static final String SURNAME = "surname";
    private static final long SHARES = 100L;
    private static final String CLASS_OF_SHARES = "class of shares";
    private static final String CURRENCY = "currency";

    private final ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

    @Test
    void serializesToExpectedJsonStructure() throws Exception {
        Shareholder details = new Shareholder();
        details.setShareholderId(SHAREHOLDER_ID);
        details.setForename1(FORENAME1);
        details.setForename2(FORENAME2);
        details.setSurname(SURNAME);
        details.setShares(SHARES);
        details.setClassOfShares(CLASS_OF_SHARES);
        details.setCurrency(CURRENCY);

        JsonNode node = objectMapper.valueToTree(details);

        try {
            assertThat(node.has("shareholderId")).isFalse();
            assertThat(node.get("fore_name_1").asText()).isEqualTo(FORENAME1);
            assertThat(node.get("fore_name_2").asText()).isEqualTo(FORENAME2);
            assertThat(node.get("surname").asText()).isEqualTo(SURNAME);
            assertThat(node.get("shares").asText()).isEqualTo(String.valueOf(SHARES));
            assertThat(node.get("class_of_shares").asText()).isEqualTo(CLASS_OF_SHARES);
            assertThat(node.get("currency").asText()).isEqualTo(CURRENCY);
        } catch (AssertionError e) {
            System.out.println("RAW JSON: " + objectMapper.writeValueAsString(details));
            throw e;
        }
    }

    static String toJsonDate(LocalDate date) {
        return "[" + date.getYear() + "," + date.getMonthValue() + "," + date.getDayOfMonth() + "]";
    }
}

