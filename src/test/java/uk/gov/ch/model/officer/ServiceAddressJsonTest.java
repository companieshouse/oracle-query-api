package uk.gov.ch.model.officer;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.JsonNode;
import uk.gov.ch.model.AbstractJsonTest;

import static org.assertj.core.api.Assertions.assertThat;

class ServiceAddressJsonTest extends AbstractJsonTest {

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

        JsonNode node = jsonMapper.valueToTree(details);

        try {
            assertThat(node.get("address_line_1").asString()).isEqualTo(ADDRESS_LINE_1);
            assertThat(node.get("address_line_2").asString()).isEqualTo(ADDRESS_LINE_2);
            assertThat(node.get("postal_code").asString()).isEqualTo(POSTCODE);
            assertThat(node.get("care_of_name").asString()).isEqualTo(CARE_OF_NAME);
            assertThat(node.get("country").asString()).isEqualTo(COUNTRY);
            assertThat(node.get("locality").asString()).isEqualTo(LOCALITY);
            assertThat(node.get("po_box").asString()).isEqualTo(PO_BOX);
            assertThat(node.get("premises").asString()).isEqualTo(PREMISES);
            assertThat(node.get("region").asString()).isEqualTo(REGION);
            assertThat(node.get("usual_country_of_residence").asString()).isEqualTo(USUAL_COUNTRY_OF_RESIDENCE);
        } catch (Throwable e) {
            System.out.println("RAW JSON: " + jsonMapper.writeValueAsString(details));
            throw e;
        }
    }
}

