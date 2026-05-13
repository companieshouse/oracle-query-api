package uk.gov.ch.model.update.trusts;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class CorporateTrusteeDataJsonTest {

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

    private final ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

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

        JsonNode node = objectMapper.valueToTree(details);

        try {
            assertThat(node.get("trusteeId").asText()).isEqualTo(TRUSTEE_ID);
            assertThat(node.get("trusteeName").asText()).isEqualTo(TRUSTEE_NAME);
            assertThat(node.get("registerLocation").asText()).isEqualTo(REGISTER_LOCATION);
            assertThat(node.get("registrationNumber").asText()).isEqualTo(REGISTRATION_NUMBER);
            assertThat(node.get("lawGoverned").asText()).isEqualTo(LAW_GOVERNED);
            assertThat(node.get("legalForm").asText()).isEqualTo(LEGAL_FORM);
            assertThat(node.get("country").asText()).isEqualTo(COUNTRY);
            assertThat(node.get("onRegisterInCountryFormed").asText()).isEqualTo(Boolean.toString(ON_REGISTER_IN_COUNTRY_FORMED));
            assertThat(node.get("corporateIndicator").asText()).isEqualTo(CORPORATE_INDICATOR);
            assertThat(node.get("trusteeTypeId").asText()).isEqualTo(TRUSTEE_TYPE_ID);
            assertThat(node.get("appointmentDate").asText()).isEqualTo(APPOINTMENT_DATE);
            assertThat(node.get("ceasedDate").asText()).isEqualTo(CEASED_DATE);
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

