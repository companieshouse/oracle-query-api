package uk.gov.ch.model.corporatebody.sqldatamodels;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.JsonNode;
import uk.gov.ch.model.AbstractJsonTest;

import static org.assertj.core.api.Assertions.assertThat;

class RegisteredOfficeAddressJsonTest extends AbstractJsonTest {


    private static final String ADDRESS_LINE_1 = "address line 1";
    private static final String ADDRESS_LINE_2 = "address line 2";
    private static final String POSTCODE = "postcode";
    private static final String CARE_OF_NAME = "care of name";
    private static final String COUNTRY = "country";
    private static final String LOCALITY = "locality";
    private static final String PO_BOX = "po box";
    private static final String PREMISES = "premises";
    private static final String REGION = "region";

    @Test
    void serializesToExpectedJsonStructure() throws Exception {
        RegisteredOfficeAddress details = new RegisteredOfficeAddress();
        details.setAddressLine1(ADDRESS_LINE_1);
        details.setAddressLine2(ADDRESS_LINE_2);
        details.setPostalCode(POSTCODE);
        details.setCareOfName(CARE_OF_NAME);
        details.setCountry(COUNTRY);
        details.setLocality(LOCALITY);
        details.setPoBox(PO_BOX);
        details.setPremises(PREMISES);
        details.setRegion(REGION);

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
        } catch (Throwable e) {
            System.out.println("RAW JSON: " + jsonMapper.writeValueAsString(details));
            throw e;
        }
    }
}

