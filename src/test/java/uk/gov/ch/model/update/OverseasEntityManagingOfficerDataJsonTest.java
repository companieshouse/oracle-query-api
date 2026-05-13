package uk.gov.ch.model.update;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class OverseasEntityManagingOfficerDataJsonTest {

    private static final String MANAGING_OFFICER_APPOINTMENT_ID = "managing officer appointment id";
    private static final String CONTACT_NAME_FULL = "contact name full";
    private static final String CONTACT_EMAIL_ADDRESS = "contact email address";
    private static final String DATE_OF_BIRTH = "date of birth";

    private static final String RESIDENTIAL_PREMISES = "residential premises";
    private static final String RESIDENTIAL_ADDRESS_LINE_1 = "residential address line 1";
    private static final String RESIDENTIAL_ADDRESS_LINE_2 = "residential address line 2";
    private static final String RESIDENTIAL_TOWN = "residential town";
    private static final String RESIDENTIAL_REGION = "residential region";
    private static final String RESIDENTIAL_COUNTRY = "residential country";
    private static final String RESIDENTIAL_POSTCODE = "residential postcode";
    private static final String RESIDENTIAL_PO_BOX = "residential po box";
    private static final String RESIDENTIAL_CARE_OF = "residential care of name";

    private static final String PRINCIPAL_PREMISES = "principal premises";
    private static final String PRINCIPAL_ADDRESS_LINE_1 = "principal address line 1";
    private static final String PRINCIPAL_ADDRESS_LINE_2 = "principal address line 2";
    private static final String PRINCIPAL_TOWN = "principal town";
    private static final String PRINCIPAL_REGION = "principal region";
    private static final String PRINCIPAL_COUNTRY = "principal country";
    private static final String PRINCIPAL_POSTCODE = "principal postcode";
    private static final String PRINCIPAL_PO_BOX = "principal po box";
    private static final String PRINCIPAL_CARE_OF = "principal care of name";

    private final ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

    @Test
    void serializesToExpectedJsonStructure() throws Exception {
        OverseasEntityManagingOfficerData details = new OverseasEntityManagingOfficerData();
        details.setManagingOfficerAppointmentId(MANAGING_OFFICER_APPOINTMENT_ID);
        details.setContactNameFull(CONTACT_NAME_FULL);
        details.setContactEmailAddress(CONTACT_EMAIL_ADDRESS);
        details.setDateOfBirth(DATE_OF_BIRTH);

        details.setResidentialPremises(RESIDENTIAL_PREMISES);
        details.setResidentialAddressLine1(RESIDENTIAL_ADDRESS_LINE_1);
        details.setResidentialAddressLine2(RESIDENTIAL_ADDRESS_LINE_2);
        details.setResidentialCareOf(RESIDENTIAL_CARE_OF);
        details.setResidentialCountryName(RESIDENTIAL_COUNTRY);
        details.setResidentialPostTown(RESIDENTIAL_TOWN);
        details.setResidentialPoBox(RESIDENTIAL_PO_BOX);
        details.setResidentialPostalCode(RESIDENTIAL_POSTCODE);
        details.setResidentialRegion(RESIDENTIAL_REGION);

        details.setPrincipalPremises(PRINCIPAL_PREMISES);
        details.setPrincipalAddressLine1(PRINCIPAL_ADDRESS_LINE_1);
        details.setPrincipalAddressLine2(PRINCIPAL_ADDRESS_LINE_2);
        details.setPrincipalCareOf(PRINCIPAL_CARE_OF);
        details.setPrincipalCountryName(PRINCIPAL_COUNTRY);
        details.setPrincipalTown(PRINCIPAL_TOWN);
        details.setPrincipalPoBox(PRINCIPAL_PO_BOX);
        details.setPrincipalPostalCode(PRINCIPAL_POSTCODE);
        details.setPrincipalRegion(PRINCIPAL_REGION);

        JsonNode node = objectMapper.valueToTree(details);

	// RAW JSON: {"managingOfficerAppointmentId":"managing officer appointment id","residential_address":{"address_line_1":"residential address line 1","address_line_2":"residential address line 2","care_of":"residential care of name","country":"residential country","locality":"residential town","po_box":"residential po box","postal_code":"residential postcode","premises":"residential premises","region":"residential care of name"},"principal_address":{"address_line_1":"principal address line 1","address_line_2":"principal address line 2","care_of":"principal care of name","country":"principal country","locality":"principal town","po_box":"principal po box","postal_code":"principal postcode","premises":"principal premises","region":"principal care of name"},"date_of_birth":"date of birth","contact_name_full":"contact name full","contact_email_address":"contact email address"}
        try {
            JsonNode residentialAddress = node.get("residential_address");
            JsonNode principalAddress = node.get("principal_address");

            assertThat(node.get("managingOfficerAppointmentId").asText()).isEqualTo(MANAGING_OFFICER_APPOINTMENT_ID);
            assertThat(node.get("contact_name_full").asText()).isEqualTo(CONTACT_NAME_FULL);
            assertThat(node.get("contact_email_address").asText()).isEqualTo(CONTACT_EMAIL_ADDRESS);
            assertThat(node.get("date_of_birth").asText()).isEqualTo(DATE_OF_BIRTH);

            // Verify nested objects exist
            assertThat(residentialAddress).isNotNull();
            assertThat(principalAddress).isNotNull();

            // Residential address assertions
            assertThat(residentialAddress.get("premises").asText()).isEqualTo(RESIDENTIAL_PREMISES);
            assertThat(residentialAddress.get("address_line_1").asText()).isEqualTo(RESIDENTIAL_ADDRESS_LINE_1);
            assertThat(residentialAddress.get("address_line_2").asText()).isEqualTo(RESIDENTIAL_ADDRESS_LINE_2);
            assertThat(residentialAddress.get("locality").asText()).isEqualTo(RESIDENTIAL_TOWN);
            assertThat(residentialAddress.get("region").asText()).isEqualTo(RESIDENTIAL_REGION);
            assertThat(residentialAddress.get("country").asText()).isEqualTo(RESIDENTIAL_COUNTRY);
            assertThat(residentialAddress.get("postal_code").asText()).isEqualTo(RESIDENTIAL_POSTCODE);
            assertThat(residentialAddress.get("po_box").asText()).isEqualTo(RESIDENTIAL_PO_BOX);
            assertThat(residentialAddress.get("care_of").asText()).isEqualTo(RESIDENTIAL_CARE_OF);

            // Principal address assertions (add constants for these)
            assertThat(principalAddress.get("premises").asText()).isEqualTo(PRINCIPAL_PREMISES);
            assertThat(principalAddress.get("address_line_1").asText()).isEqualTo(PRINCIPAL_ADDRESS_LINE_1);
            assertThat(principalAddress.get("address_line_2").asText()).isEqualTo(PRINCIPAL_ADDRESS_LINE_2);
            assertThat(principalAddress.get("locality").asText()).isEqualTo(PRINCIPAL_TOWN);
            assertThat(principalAddress.get("region").asText()).isEqualTo(PRINCIPAL_REGION);
            assertThat(principalAddress.get("country").asText()).isEqualTo(PRINCIPAL_COUNTRY);
            assertThat(principalAddress.get("postal_code").asText()).isEqualTo(PRINCIPAL_POSTCODE);
            assertThat(principalAddress.get("po_box").asText()).isEqualTo(PRINCIPAL_PO_BOX);
            assertThat(principalAddress.get("care_of").asText()).isEqualTo(PRINCIPAL_CARE_OF);
        } catch (Throwable e) {
        // } catch (AssertionError e) {
            System.out.println("RAW JSON: " + objectMapper.writeValueAsString(details));
            throw e;
        }
    }

    static String toJsonDate(LocalDate date) {
        return "[" + date.getYear() + "," + date.getMonthValue() + "," + date.getDayOfMonth() + "]";
    }
}

