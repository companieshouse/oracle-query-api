package uk.gov.ch.model.officer.active;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ActiveDirectorDetailsJsonTest {

    private static final Long OFFICER_DETAIL_ID = 999L;
    private static final String FORENAME1 = "forename1";
    private static final String FORENAME2 = "forename2";
    private static final String SURNAME = "surname";
    private static final String OCCUPATION = "occupation";
    private static final String NATIONALITY = "nationality";
    private static final String DATE_OF_BIRTH = "1970-01-01";
    private static final String DATE_OF_BIRTH_FORMATTED = "01 January 1970";
    private static final String DATE_OF_APPOINTMENT = "1970-01-01";
    private static final String DATE_OF_APPOINTMENT_FORMATTED = "01 January 1970";
    private static final String COUNTRY_OF_RESIDENCE = "country of residence";

    private static final String SERVICE_ADDRESS_LINE_1 = "service address line 1";
    private static final String SERVICE_ADDRESS_LINE_2 = "service address line 2";
    private static final String SERVICE_ADDRESS_CARE_OF = "service care of name";
    private static final String SERVICE_ADDRESS_COUNTRY = "service country";
    private static final String SERVICE_ADDRESS_LOCALITY = "service locality";
    private static final String SERVICE_ADDRESS_PO_BOX = "service po box";
    private static final String SERVICE_ADDRESS_POSTCODE = "service postcode";
    private static final String SERVICE_ADDRESS_REGION = "service region";

    private static final String RESIDENTIAL_ADDRESS_LINE_1 = "residential address line 1";
    private static final String RESIDENTIAL_ADDRESS_LINE_2 = "residential address line 2";
    private static final String RESIDENTIAL_ADDRESS_CARE_OF = "residential care of name";
    private static final String RESIDENTIAL_ADDRESS_COUNTRY = "residential country";
    private static final String RESIDENTIAL_ADDRESS_LOCALITY = "residential locality";
    private static final String RESIDENTIAL_ADDRESS_PO_BOX = "residential po box";
    private static final String RESIDENTIAL_ADDRESS_POSTCODE = "residential postcode";
    private static final String RESIDENTIAL_ADDRESS_REGION = "residential region";

    private static final String SECURE_INDICATOR = "secure indicator";

    private final ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

    @Test
    void serializesToExpectedJsonStructure() throws Exception {
        ActiveDirectorDetails details = new ActiveDirectorDetails();
        details.setOfficerDetailId(OFFICER_DETAIL_ID);
        details.setForeName1(FORENAME1);
        details.setForeName2(FORENAME2);
        details.setSurname(SURNAME);
        details.setOccupation(OCCUPATION);
        details.setNationality(NATIONALITY);
        details.setDateOfBirth(DATE_OF_BIRTH);
        details.setDateOfAppointment(DATE_OF_APPOINTMENT);
        details.setCountryOfResidence(COUNTRY_OF_RESIDENCE);
        details.setSecureIndicator(SECURE_INDICATOR);
        details.setServiceAddressLine1(SERVICE_ADDRESS_LINE_1);
        details.setServiceAddressLine2(SERVICE_ADDRESS_LINE_2);
        details.setServiceAddressCareOf(SERVICE_ADDRESS_CARE_OF);
        details.setServiceAddressCountry(SERVICE_ADDRESS_COUNTRY);
        details.setServiceAddressLocality(SERVICE_ADDRESS_LOCALITY);
        details.setServiceAddressPoBox(SERVICE_ADDRESS_PO_BOX);
        details.setServiceAddressPostCode(SERVICE_ADDRESS_POSTCODE);
        details.setServiceAddressRegion(SERVICE_ADDRESS_REGION);
        details.setResidentialAddressLine1(RESIDENTIAL_ADDRESS_LINE_1);
        details.setResidentialAddressLine2(RESIDENTIAL_ADDRESS_LINE_2);
        details.setResidentialAddressCareOf(RESIDENTIAL_ADDRESS_CARE_OF);
        details.setResidentialAddressCountry(RESIDENTIAL_ADDRESS_COUNTRY);
        details.setResidentialAddressLocality(RESIDENTIAL_ADDRESS_LOCALITY);
        details.setResidentialAddressPoBox(RESIDENTIAL_ADDRESS_PO_BOX);
        details.setResidentialAddressPostCode(RESIDENTIAL_ADDRESS_POSTCODE);
        details.setResidentialAddressRegion(RESIDENTIAL_ADDRESS_REGION);

        JsonNode node = objectMapper.valueToTree(details);

        try {
            assertThat(node.has("officer_detail_id")).isFalse();
            assertThat(node.get("fore_name_1").asText()).isEqualTo(FORENAME1);
            assertThat(node.get("fore_name_2").asText()).isEqualTo(FORENAME2);
            assertThat(node.get("surname").asText()).isEqualTo(SURNAME);
            assertThat(node.get("occupation").asText()).isEqualTo(String.valueOf(OCCUPATION));
            assertThat(node.get("nationality").asText()).isEqualTo(NATIONALITY);
            assertThat(node.get("date_of_birth").asText()).isEqualTo(DATE_OF_BIRTH_FORMATTED);
            assertThat(node.get("date_of_appointment").asText()).isEqualTo(DATE_OF_APPOINTMENT_FORMATTED);
            assertThat(node.get("country_of_residence").asText()).isEqualTo(COUNTRY_OF_RESIDENCE);
            assertThat(node.has("secure_indicator")).isFalse();

            JsonNode serviceAddress = node.get("service_address");
            JsonNode residentialAddress = node.get("residential_address");

            assertThat(serviceAddress).isNotNull();
            assertThat(residentialAddress).isNotNull();

            assertServiceAddress(serviceAddress);
            assertResidentialAddress(residentialAddress);

        } catch (Throwable e) {
            System.out.println("RAW JSON: " + objectMapper.writeValueAsString(details));
            throw e;
        }
    }

    private void assertServiceAddress(JsonNode serviceAddress) {
            assertThat(serviceAddress.get("address_line_1").asText()).isEqualTo(SERVICE_ADDRESS_LINE_1);
            assertThat(serviceAddress.get("address_line_2").asText()).isEqualTo(SERVICE_ADDRESS_LINE_2);
            assertThat(serviceAddress.get("care_of").asText()).isEqualTo(SERVICE_ADDRESS_CARE_OF);
            assertThat(serviceAddress.get("country").asText()).isEqualTo(SERVICE_ADDRESS_COUNTRY);
            assertThat(serviceAddress.get("locality").asText()).isEqualTo(SERVICE_ADDRESS_LOCALITY);
            assertThat(serviceAddress.get("po_box").asText()).isEqualTo(SERVICE_ADDRESS_PO_BOX);
            assertThat(serviceAddress.get("postal_code").asText()).isEqualTo(SERVICE_ADDRESS_POSTCODE);
            assertThat(serviceAddress.get("region").asText()).isEqualTo(SERVICE_ADDRESS_REGION);
    }

    private void assertResidentialAddress(JsonNode residentialAddress) {
            assertThat(residentialAddress.get("address_line_1").asText()).isEqualTo(RESIDENTIAL_ADDRESS_LINE_1);
            assertThat(residentialAddress.get("address_line_2").asText()).isEqualTo(RESIDENTIAL_ADDRESS_LINE_2);
            assertThat(residentialAddress.get("care_of").asText()).isEqualTo(RESIDENTIAL_ADDRESS_CARE_OF);
            assertThat(residentialAddress.get("country").asText()).isEqualTo(RESIDENTIAL_ADDRESS_COUNTRY);
            assertThat(residentialAddress.get("locality").asText()).isEqualTo(RESIDENTIAL_ADDRESS_LOCALITY);
            assertThat(residentialAddress.get("po_box").asText()).isEqualTo(RESIDENTIAL_ADDRESS_PO_BOX);
            assertThat(residentialAddress.get("postal_code").asText()).isEqualTo(RESIDENTIAL_ADDRESS_POSTCODE);
            assertThat(residentialAddress.get("region").asText()).isEqualTo(RESIDENTIAL_ADDRESS_REGION);
    }
}

