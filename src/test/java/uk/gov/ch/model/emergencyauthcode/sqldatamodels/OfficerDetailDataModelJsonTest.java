package uk.gov.ch.model.emergencyauthcode.sqldatamodels;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.JsonNode;
import uk.gov.ch.model.AbstractJsonTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class OfficerDetailDataModelJsonTest extends AbstractJsonTest {


    private static final Long OFFICER_DETAIL_ID = 999L;
    private static final String FORENAME1 = "forename1";
    private static final String FORENAME2 = "forename2";
    private static final String SURNAME = "surname";
    private static final LocalDate DATE_OF_BIRTH = LocalDate.now();
    private static final String OFFICER_NATIONALITY = "nationality";
    private static final String USUAL_RESIDENTIAL_COUNTRY = "country";

    @Test
    void serializesToExpectedJsonStructure() {
        OfficerDetailDataModel details = new OfficerDetailDataModel();
        details.setOfficerId(OFFICER_DETAIL_ID);
        details.setForename1(FORENAME1);
        details.setForename2(FORENAME2);
        details.setSurname(SURNAME);
        details.setDateOfBirth(DATE_OF_BIRTH);
        details.setOfficerNationality(OFFICER_NATIONALITY);
        details.setUsualResidentialCountry(USUAL_RESIDENTIAL_COUNTRY);

        JsonNode node = jsonMapper.valueToTree(details);

        try {
            assertThat(node.get("officerId").asString()).isEqualTo(String.valueOf(OFFICER_DETAIL_ID));
            assertThat(node.get("forename1").asString()).isEqualTo(FORENAME1);
            assertThat(node.get("forename2").asString()).isEqualTo(FORENAME2);
            assertThat(node.get("surname").asString()).isEqualTo(SURNAME);
            assertThat(node.get("dateOfBirth").asString()).isEqualTo(DATE_OF_BIRTH.toString());
            assertThat(node.get("officerNationality").asString()).isEqualTo(OFFICER_NATIONALITY);
            assertThat(node.get("usualResidentialCountry").asString()).isEqualTo(USUAL_RESIDENTIAL_COUNTRY);
        } catch (AssertionError e) {
            System.out.println("RAW JSON: " + jsonMapper.writeValueAsString(details));
            throw e;
        }
    }
}

