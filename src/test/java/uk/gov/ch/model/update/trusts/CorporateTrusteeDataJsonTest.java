package uk.gov.ch.model.update.trusts;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.JsonNode;
import uk.gov.ch.model.AbstractJsonTest;
import uk.gov.companieshouse.api.model.common.Address;

import static org.assertj.core.api.Assertions.assertThat;

class CorporateTrusteeDataJsonTest extends AbstractJsonTest {

    private static final String TRUSTEE_ID = "trustee id";
    private static final String TRUSTEE_NAME = "trustee name";
    private static final String REGISTER_LOCATION = "register location";
    private static final String REGISTRATION_NUMBER = "registration number";
    private static final String LAW_GOVERNED = "law governed";
    private static final String LEGAL_FORM = "legal form";
    private static final String COUNTRY = "country";
    private static final Boolean ON_REGISTER_IN_COUNTRY_FORMED = true;
    private static final String CORPORATE_INDICATOR = "corporate indicator";
    private static final String REGISTERED_OFFICE_NAME_NUMBER = "registered office name number";
    private static final String REGISTERED_OFFICE_STREET = "registered office street";
    private static final String REGISTERED_OFFICE_AREA = "registered office area";
    private static final String REGISTERED_OFFICE_TOWN = "registered office town";
    private static final String REGISTERED_OFFICE_REGION = "registered office region";
    private static final String REGISTERED_OFFICE_COUNTRY = "registered office country";
    private static final String REGISTERED_OFFICE_POSTCODE = "registered office postcode";
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

    @Test
    void serializesToExpectedJsonStructure() throws Exception {
        CorporateTrusteeData details = new CorporateTrusteeData();
        details.setTrusteeId(TRUSTEE_ID);
        details.setTrusteeName(TRUSTEE_NAME);
        details.setRegisterLocation(REGISTER_LOCATION);
        details.setRegistrationNumber(REGISTRATION_NUMBER);
        details.setLawGoverned(LAW_GOVERNED);
        details.setLegalForm(LEGAL_FORM);
        details.setCountry(COUNTRY);
        details.setOnRegisterInCountryFormed(ON_REGISTER_IN_COUNTRY_FORMED);
        details.setCorporateInd(CORPORATE_INDICATOR);
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

        Address registeredOfficeAddress = new Address();
        registeredOfficeAddress.setPremises(REGISTERED_OFFICE_NAME_NUMBER);
        registeredOfficeAddress.setAddressLine1(REGISTERED_OFFICE_STREET);
        registeredOfficeAddress.setAddressLine2(REGISTERED_OFFICE_AREA);
        registeredOfficeAddress.setLocality(REGISTERED_OFFICE_TOWN);
        registeredOfficeAddress.setRegion(REGISTERED_OFFICE_REGION);
        registeredOfficeAddress.setCountry(REGISTERED_OFFICE_COUNTRY);
        registeredOfficeAddress.setPostalCode(REGISTERED_OFFICE_POSTCODE);
        details.setRegisteredOfficeAddress(registeredOfficeAddress);

        JsonNode node = jsonMapper.valueToTree(details);

        try {
            assertThat(node.get("trusteeId").asString()).isEqualTo(TRUSTEE_ID);
            assertThat(node.get("trusteeName").asString()).isEqualTo(TRUSTEE_NAME);
            assertThat(node.get("registerLocation").asString()).isEqualTo(REGISTER_LOCATION);
            assertThat(node.get("registrationNumber").asString()).isEqualTo(REGISTRATION_NUMBER);
            assertThat(node.get("lawGoverned").asString()).isEqualTo(LAW_GOVERNED);
            assertThat(node.get("legalForm").asString()).isEqualTo(LEGAL_FORM);
            assertThat(node.get("country").asString()).isEqualTo(COUNTRY);
            assertThat(node.get("onRegisterInCountryFormed").asString()).isEqualTo(Boolean.toString(ON_REGISTER_IN_COUNTRY_FORMED));
            assertThat(node.get("corporateIndicator").asString()).isEqualTo(CORPORATE_INDICATOR);
            assertThat(node.get("trusteeTypeId").asString()).isEqualTo(TRUSTEE_TYPE_ID);
            assertThat(node.get("appointmentDate").asString()).isEqualTo(APPOINTMENT_DATE);
            assertThat(node.get("ceasedDate").asString()).isEqualTo(CEASED_DATE);

            JsonNode serviceAddressNode = node.get("serviceAddress");
            JsonNode registeredOfficeAddressNode = node.get("registeredOfficeAddress");

            assertThat(serviceAddressNode).isNotNull();
            assertThat(registeredOfficeAddressNode).isNotNull();

            assertServiceAddress(serviceAddressNode);
            assertRegisteredOfficeAddress(registeredOfficeAddressNode);
        } catch (Throwable e) {
            System.out.println("RAW JSON: " + jsonMapper.writeValueAsString(details));
            throw e;
        }
    }

    private void assertServiceAddress(JsonNode serviceAddress) {
           assertThat(serviceAddress.get("address_line_1").asString()).isEqualTo(SERVICE_ADDRESS_STREET);
           assertThat(serviceAddress.get("address_line_2").asString()).isEqualTo(SERVICE_ADDRESS_AREA);
           assertThat(serviceAddress.get("care_of").isNull()).isTrue();
           assertThat(serviceAddress.get("country").asString()).isEqualTo(SERVICE_ADDRESS_COUNTRY);
           assertThat(serviceAddress.get("locality").asString()).isEqualTo(SERVICE_ADDRESS_TOWN);
           assertThat(serviceAddress.get("po_box").isNull()).isTrue();
           assertThat(serviceAddress.get("postal_code").asString()).isEqualTo(SERVICE_ADDRESS_POSTCODE);
           assertThat(serviceAddress.get("region").asString()).isEqualTo(SERVICE_ADDRESS_REGION);
   }

    private void assertRegisteredOfficeAddress(JsonNode registeredOfficeAddress) {
           assertThat(registeredOfficeAddress.get("address_line_1").asString()).isEqualTo(REGISTERED_OFFICE_STREET);
           assertThat(registeredOfficeAddress.get("address_line_2").asString()).isEqualTo(REGISTERED_OFFICE_AREA);
           assertThat(registeredOfficeAddress.get("care_of").isNull()).isTrue();
           assertThat(registeredOfficeAddress.get("country").asString()).isEqualTo(REGISTERED_OFFICE_COUNTRY);
           assertThat(registeredOfficeAddress.get("locality").asString()).isEqualTo(REGISTERED_OFFICE_TOWN);
           assertThat(registeredOfficeAddress.get("po_box").isNull()).isTrue();
           assertThat(registeredOfficeAddress.get("postal_code").asString()).isEqualTo(REGISTERED_OFFICE_POSTCODE);
           assertThat(registeredOfficeAddress.get("region").asString()).isEqualTo(REGISTERED_OFFICE_REGION);
   }

}

