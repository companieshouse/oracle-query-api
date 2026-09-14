package uk.gov.ch.model.officer.bankrupt;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.JsonNode;
import uk.gov.ch.model.AbstractJsonTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ScottishBankruptOfficerDataModelJsonTest extends AbstractJsonTest {

    private static final String EPHEMERAL_KEY = "key";
    private static final String FORENAME1 = "forename";
    private static final String FORENAME2 = "forename2";
    private static final String SURNAME = "surname";
    private static final String ADDRESS_LINE1 = "address line 1";
    private static final String ADDRESS_LINE2 = "address line 2";
    private static final String ADDRESS_LINE3 = "address line 3";
    private static final String TOWN = "address town";
    private static final String COUNTY = "address county";
    private static final String POSTCODE = "address postcode";
    private static final LocalDate DATE_OF_BIRTH = LocalDate.now();
    private static final String ALIAS = "alias";
    private static final String CASE_REFERENCE = "case reference";
    private static final String CASE_TYPE = "case type";
    private static final String BANKRUPTCY_TYPE = "bankruptcy type";
    private static final LocalDate START_DATE = LocalDate.now();
    private static final LocalDate DEBTOR_DISCHARGE_DATE = LocalDate.now();
    private static final LocalDate TRUSTEE_DISCHARGE_DATE = LocalDate.now();

    @Test
    void serializesToExpectedJsonStructure() {
        ScottishBankruptOfficerDataModel details = new ScottishBankruptOfficerDataModel();
        details.setEphemeralKey(EPHEMERAL_KEY);
        details.setForename1(FORENAME1);
        details.setForename2(FORENAME2);
        details.setSurname(SURNAME);
        details.setAddressLine1(ADDRESS_LINE1);
        details.setAddressLine2(ADDRESS_LINE2);
        details.setAddressLine3(ADDRESS_LINE3);
        details.setTown(TOWN);
        details.setCounty(COUNTY);
        details.setPostcode(POSTCODE);
        details.setDateOfBirth(DATE_OF_BIRTH);
        details.setAlias(ALIAS);
        details.setCaseReference(CASE_REFERENCE);
        details.setCaseType(CASE_TYPE);
        details.setBankruptcyType(BANKRUPTCY_TYPE);
        details.setStartDate(START_DATE);
        details.setDebtorDischargeDate(DEBTOR_DISCHARGE_DATE);
        details.setTrusteeDischargeDate(TRUSTEE_DISCHARGE_DATE);

        JsonNode node = jsonMapper.valueToTree(details);

        try {
            assertThat(node.get("ephemeralKey").asString()).isEqualTo(EPHEMERAL_KEY);
            assertThat(node.get("forename1").asString()).isEqualTo(FORENAME1);
            assertThat(node.get("forename2").asString()).isEqualTo(FORENAME2);
            assertThat(node.get("surname").asString()).isEqualTo(SURNAME);
            assertThat(node.get("addressLine1").asString()).isEqualTo(ADDRESS_LINE1);
            assertThat(node.get("addressLine2").asString()).isEqualTo(ADDRESS_LINE2);
            assertThat(node.get("addressLine3").asString()).isEqualTo(ADDRESS_LINE3);
            assertThat(node.get("addressTown").asString()).isEqualTo(TOWN);
            assertThat(node.get("addressCounty").asString()).isEqualTo(COUNTY);
            assertThat(node.get("addressPostcode").asString()).isEqualTo(POSTCODE);
            assertThat(node.get("dateOfBirth").asString()).isEqualTo(DATE_OF_BIRTH.toString());
            assertThat(node.get("alias").asString()).isEqualTo(ALIAS);
            assertThat(node.get("caseReference").asString()).isEqualTo(CASE_REFERENCE);
            assertThat(node.get("caseType").asString()).isEqualTo(CASE_TYPE);
            assertThat(node.get("bankruptcyType").asString()).isEqualTo(BANKRUPTCY_TYPE);
            assertThat(node.get("startDate").asString()).isEqualTo(START_DATE.toString());
            assertThat(node.get("debtorDischargeDate").asString()).isEqualTo(DEBTOR_DISCHARGE_DATE.toString());
            assertThat(node.get("trusteeDischargeDate").asString()).isEqualTo(TRUSTEE_DISCHARGE_DATE.toString());
        } catch (AssertionError e) {
            System.out.println("RAW JSON: " + jsonMapper.writeValueAsString(details));
            throw e;
        }
    }
}

