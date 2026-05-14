package uk.gov.ch.model.emergencyauthcode.sqldatamodels;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class OfficerDetailDataModelJsonTest {


    private static final Long OFFICER_DETAIL_ID = 999L;
    private static final String FORENAME1 = "forename1";
    private static final String FORENAME2 = "forename2";
    private static final String SURNAME = "surname";
    private static final LocalDate DATE_OF_BIRTH = LocalDate.now();
    private static final String OFFICER_NATIONALITY = "nationality";
    private static final String USUAL_RESIDENTIAL_COUNTRY = "country";

    private final ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

    @Test
    void serializesToExpectedJsonStructure() throws Exception {
        OfficerDetailDataModel details = new OfficerDetailDataModel();
        details.setOfficerId(OFFICER_DETAIL_ID);
        details.setForename1(FORENAME1);
        details.setForename2(FORENAME2);
        details.setSurname(SURNAME);
        details.setDateOfBirth(DATE_OF_BIRTH);
        details.setOfficerNationality(OFFICER_NATIONALITY);
        details.setUsualResidentialCountry(USUAL_RESIDENTIAL_COUNTRY);

        JsonNode node = objectMapper.valueToTree(details);

        try {
            assertThat(node.get("officerId").asText()).isEqualTo(String.valueOf(OFFICER_DETAIL_ID));
            assertThat(node.get("forename1").asText()).isEqualTo(FORENAME1);
            assertThat(node.get("forename2").asText()).isEqualTo(FORENAME2);
            assertThat(node.get("surname").asText()).isEqualTo(SURNAME);
            assertThat(node.get("dateOfBirth")).hasToString(toJsonDate(DATE_OF_BIRTH));
            assertThat(node.get("officerNationality").asText()).isEqualTo(OFFICER_NATIONALITY);
            assertThat(node.get("usualResidentialCountry").asText()).isEqualTo(USUAL_RESIDENTIAL_COUNTRY);
        } catch (AssertionError e) {
            System.out.println("RAW JSON: " + objectMapper.writeValueAsString(details));
            throw e;
        }
    }

    static String toJsonDate(LocalDate date) {
        return "[" + date.getYear() + "," + date.getMonthValue() + "," + date.getDayOfMonth() + "]";
    }
}

