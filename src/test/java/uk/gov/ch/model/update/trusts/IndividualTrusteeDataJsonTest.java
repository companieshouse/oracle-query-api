package uk.gov.ch.model.update.trusts;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import uk.gov.companieshouse.api.model.common.Address;

import static org.assertj.core.api.Assertions.assertThat;

class IndividualTrusteeDataJsonTest {

    private static final String TRUSTEE_ID = "trustee id";
    private static final String TRUSTEE_FORENAME1 = "trustee forename1";
    private static final String TRUSTEE_FORENAME2 = "trustee forename2";
    private static final String TRUSTEE_SURNAME = "trustee surname";
    private static final String CORPORATE_INDICATOR = "corporate indicator";
    private static final String RESIDENTIAL_ADDRESS_NAME_NUMBER = "registered office name number";
    private static final String RESIDENTIAL_ADDRESS_STREET = "registered office street";
    private static final String RESIDENTIAL_ADDRESS_AREA = "registered office area";
    private static final String RESIDENTIAL_ADDRESS_TOWN = "registered office town";
    private static final String RESIDENTIAL_ADDRESS_REGION = "registered office region";
    private static final String RESIDENTIAL_ADDRESS_COUNTRY = "registered office country";
    private static final String RESIDENTIAL_ADDRESS_POSTCODE = "registered office postcode";
    private static final String TRUSTEE_TYPE_ID = "trustee type id";
    private static final String APPOINTMENT_DATE = "appointment date";
    private static final String CEASED_DATE = "ceased date";

    private static final String SERVICE_ADDRESS_NAME_NUMBER = "service address name number";
    private static final String SERVICE_ADDRESS_STREET = "service address street";
    private static final String SERVICE_ADDRESS_AREA = "service address area";
    private static final String SERVICE_ADDRESS_TOWN = "service address town";
    private static final String SERVICE_ADDRESS_REGION = "service address region";
    private static final String SERVICE_ADDRESS_COUNTRY = "service address country";
    private static final String SERVICE_ADDRESS_POSTCODE = "service address postcode";

    private final ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

    @Test
    void serializesToExpectedJsonStructure() throws Exception {
        IndividualTrusteeData details = new IndividualTrusteeData();
        details.setTrusteeId(TRUSTEE_ID);
        details.setTrusteeForename1(TRUSTEE_FORENAME1);
        details.setTrusteeForename2(TRUSTEE_FORENAME2);
        details.setTrusteeSurname(TRUSTEE_SURNAME);
        details.setCorporateIndicator(CORPORATE_INDICATOR);
        details.setTrusteeTypeId(TRUSTEE_TYPE_ID);
        details.setAppointmentDate(APPOINTMENT_DATE);
        details.setCeasedDate(CEASED_DATE);

        Address serviceOfficeAddress = new Address();
        serviceOfficeAddress.setPremises(SERVICE_ADDRESS_NAME_NUMBER);
        serviceOfficeAddress.setAddressLine1(SERVICE_ADDRESS_STREET);
        serviceOfficeAddress.setAddressLine2(SERVICE_ADDRESS_AREA);
        serviceOfficeAddress.setLocality(SERVICE_ADDRESS_TOWN);
        serviceOfficeAddress.setRegion(SERVICE_ADDRESS_REGION);
        serviceOfficeAddress.setCountry(SERVICE_ADDRESS_COUNTRY);
        serviceOfficeAddress.setPostalCode(SERVICE_ADDRESS_POSTCODE);
        details.setServiceAddress(serviceOfficeAddress);

        Address usualResidentialAddress = new Address();
        usualResidentialAddress.setPremises(RESIDENTIAL_ADDRESS_NAME_NUMBER);
        usualResidentialAddress.setAddressLine1(RESIDENTIAL_ADDRESS_STREET);
        usualResidentialAddress.setAddressLine2(RESIDENTIAL_ADDRESS_AREA);
        usualResidentialAddress.setLocality(RESIDENTIAL_ADDRESS_TOWN);
        usualResidentialAddress.setRegion(RESIDENTIAL_ADDRESS_REGION);
        usualResidentialAddress.setCountry(RESIDENTIAL_ADDRESS_COUNTRY);
        usualResidentialAddress.setPostalCode(RESIDENTIAL_ADDRESS_POSTCODE);
        details.setUsualResidentialAddress(usualResidentialAddress);

        JsonNode node = objectMapper.valueToTree(details);

        try {
            assertThat(node.get("trusteeId").asText()).isEqualTo(TRUSTEE_ID);
            assertThat(node.get("trusteeForename1").asText()).isEqualTo(TRUSTEE_FORENAME1);
            assertThat(node.get("trusteeForename2").asText()).isEqualTo(TRUSTEE_FORENAME2);
            assertThat(node.get("trusteeSurname").asText()).isEqualTo(TRUSTEE_SURNAME);
            assertThat(node.get("corporateIndicator").asText()).isEqualTo(CORPORATE_INDICATOR);
            assertThat(node.get("trusteeTypeId").asText()).isEqualTo(TRUSTEE_TYPE_ID);
            assertThat(node.get("appointmentDate").asText()).isEqualTo(APPOINTMENT_DATE);
            assertThat(node.get("ceasedDate").asText()).isEqualTo(CEASED_DATE);

            JsonNode serviceAddressNode = node.get("serviceAddress");
            JsonNode usualResidentialAddressNode = node.get("usualResidentialAddress");

            assertThat(serviceAddressNode).isNotNull();
            assertThat(usualResidentialAddressNode).isNotNull();

            assertServiceAddress(serviceAddressNode);
            assertResidentialAddress(usualResidentialAddressNode);
        } catch (Throwable e) {
            System.out.println("RAW JSON: " + objectMapper.writeValueAsString(details));
            throw e;
        }
    }

    private void assertServiceAddress(JsonNode serviceAddress) {
           assertThat(serviceAddress.get("address_line_1").asText()).isEqualTo(SERVICE_ADDRESS_STREET);
           assertThat(serviceAddress.get("address_line_2").asText()).isEqualTo(SERVICE_ADDRESS_AREA);
           assertThat(serviceAddress.get("care_of").isNull()).isTrue();
           assertThat(serviceAddress.get("country").asText()).isEqualTo(SERVICE_ADDRESS_COUNTRY);
           assertThat(serviceAddress.get("locality").asText()).isEqualTo(SERVICE_ADDRESS_TOWN);
           assertThat(serviceAddress.get("po_box").isNull()).isTrue();
           assertThat(serviceAddress.get("postal_code").asText()).isEqualTo(SERVICE_ADDRESS_POSTCODE);
           assertThat(serviceAddress.get("region").asText()).isEqualTo(SERVICE_ADDRESS_REGION);
   }

    private void assertResidentialAddress(JsonNode usualResidentialAddress) {
           assertThat(usualResidentialAddress.get("address_line_1").asText()).isEqualTo(RESIDENTIAL_ADDRESS_STREET);
           assertThat(usualResidentialAddress.get("address_line_2").asText()).isEqualTo(RESIDENTIAL_ADDRESS_AREA);
           assertThat(usualResidentialAddress.get("care_of").isNull()).isTrue();
           assertThat(usualResidentialAddress.get("country").asText()).isEqualTo(RESIDENTIAL_ADDRESS_COUNTRY);
           assertThat(usualResidentialAddress.get("locality").asText()).isEqualTo(RESIDENTIAL_ADDRESS_TOWN);
           assertThat(usualResidentialAddress.get("po_box").isNull()).isTrue();
           assertThat(usualResidentialAddress.get("postal_code").asText()).isEqualTo(RESIDENTIAL_ADDRESS_POSTCODE);
           assertThat(usualResidentialAddress.get("region").asText()).isEqualTo(RESIDENTIAL_ADDRESS_REGION);
   }

}

