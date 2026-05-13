package uk.gov.ch.model.officer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ServiceAddressJsonTest {

    private static final String ADDRESS_LINE_1 = "address line 1";
    private static final String ADDRESS_LINE_2 = "address line 2";
    private static final String POSTCODE = "postcode";
    private static final String CARE_OF_NAME = "care of name";
    private static final String COUNTRY = "country";
    private static final String LOCALITY = "locality";
    private static final String PO_BOX = "po box";
    private static final String PREMISES = "premises";
    private static final String REGION = "region";
    private static final String USUAL_COUNTRY_OF_RESIDENCE = "usual country of residence";

    private final ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

    @Test
    void serializesToExpectedJsonStructure() throws Exception {
        ServiceAddress details = new ServiceAddress();
        details.setAddressLine1(ADDRESS_LINE_1);
        details.setAddressLine2(ADDRESS_LINE_2);
        details.setPostalCode(POSTCODE);
        details.setCareOfName(CARE_OF_NAME);
        details.setCountry(COUNTRY);
        details.setLocality(LOCALITY);
        details.setPoBox(PO_BOX);
        details.setPremises(PREMISES);
        details.setRegion(REGION);
        details.setUsualCountryOfResidence(USUAL_COUNTRY_OF_RESIDENCE);

        JsonNode node = objectMapper.valueToTree(details);

        try {
            assertThat(node.get("address_line_1").asText()).isEqualTo(ADDRESS_LINE_1);
            assertThat(node.get("address_line_2").asText()).isEqualTo(ADDRESS_LINE_2);
            assertThat(node.get("postal_code").asText()).isEqualTo(POSTCODE);
            assertThat(node.get("care_of_name").asText()).isEqualTo(String.valueOf(CARE_OF_NAME));
            assertThat(node.get("country").asText()).isEqualTo(COUNTRY);
            assertThat(node.get("locality").asText()).isEqualTo(LOCALITY);
            assertThat(node.get("po_box").asText()).isEqualTo(PO_BOX);
            assertThat(node.get("premises").asText()).isEqualTo(PREMISES);
            assertThat(node.get("region").asText()).isEqualTo(REGION);
            assertThat(node.get("usual_country_of_residence").asText()).isEqualTo(USUAL_COUNTRY_OF_RESIDENCE);
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

