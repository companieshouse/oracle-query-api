package uk.gov.ch.model.officer.bankrupt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ScottishBankruptOfficerSearchResultJsonTest {


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
    private static final String CASE_TYPE = "case type";
    private static final LocalDate DEBTOR_DISCHARGE_DATE = LocalDate.now();

    private final ObjectMapper objectMapper = new ObjectMapper()
	.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

    @Test
    void serializesToExpectedJsonStructure() throws Exception {
	ScottishBankruptOfficerSearchResult details = new ScottishBankruptOfficerSearchResult();
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
	details.setCaseType(CASE_TYPE);
	details.setDebtorDischargeDate(DEBTOR_DISCHARGE_DATE);

	JsonNode node = objectMapper.valueToTree(details);

	try {
	    assertThat(node.get("ephemeralKey").asText()).isEqualTo(EPHEMERAL_KEY);
	    assertThat(node.get("forename1").asText()).isEqualTo(FORENAME1);
	    assertThat(node.get("forename2").asText()).isEqualTo(FORENAME2);
	    assertThat(node.get("surname").asText()).isEqualTo(SURNAME);
	    assertThat(node.get("addressLine1").asText()).isEqualTo(ADDRESS_LINE1);
	    assertThat(node.get("addressLine2").asText()).isEqualTo(ADDRESS_LINE2);
	    assertThat(node.get("addressLine3").asText()).isEqualTo(ADDRESS_LINE3);
	    assertThat(node.get("town").asText()).isEqualTo(TOWN);
	    assertThat(node.get("county").asText()).isEqualTo(COUNTY);
	    assertThat(node.get("postcode").asText()).isEqualTo(POSTCODE);
	    assertThat(node.get("dateOfBirth")).hasToString(toJsonDate(DATE_OF_BIRTH));
	    assertThat(node.get("caseType").asText()).isEqualTo(CASE_TYPE);
	    assertThat(node.get("debtorDischargeDate")).hasToString(toJsonDate(DEBTOR_DISCHARGE_DATE));
	} catch (AssertionError e) {
	    System.out.println("RAW JSON: " + objectMapper.writeValueAsString(details));
	    throw e;
	}
    }

    static String toJsonDate(LocalDate date) {
	return "[" + date.getYear() + "," + date.getMonthValue() + "," + date.getDayOfMonth() + "]";
    }
}

