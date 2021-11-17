package uk.gov.ch.model.officer.active;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ActiveOfficerDetailsTest {

    private ActiveOfficerDetails officer;
    private String URA_LINE_1 = "123 Street";
    private String URA_POST_TOWN = "Town";
    private String URA_POST_CODE = "POST CODE";
    private String UNFORMATTED_DOB = "1962-01-29 00:00:00.0";
    private String UNFORMATTED_DOA = "2009-01-29 00:00:00.0";
    private String FORMATTED_DOB = "29 January 1962";
    private String COUNTRY_OF_RESIDENCE = "Country";
    private String SECURE_DIRECTOR_URA_LINE_1 = "Companies House Cannot Disclose this Home Address";

    @BeforeEach
    void beforeEach() {
        officer = new ActiveOfficerDetails();
        officer.setOfficerDetailId(001L);
        officer.setForeName1("JOHN");
        officer.setForeName2("MiddleName");
        officer.setSurname("DOE");
        officer.setOccupation("singer");
        officer.setNationality("British");
        officer.setDateOfBirth(UNFORMATTED_DOB);
        officer.setDateOfAppointment(UNFORMATTED_DOA);
        officer.setCountryOfResidence(COUNTRY_OF_RESIDENCE);
        officer.setCorporate(false);

        officer.setResidentialAddressLine1(URA_LINE_1);
        officer.setResidentialAddressLine2("Crown Way");
        officer.setResidentialAddressCountry("Wales");
        officer.setResidentialAddressLocality(URA_POST_TOWN);
        officer.setResidentialAddressPostCode(URA_POST_CODE);
    }

    @Test
    @Description("Should return the non-secure officer's formatted full date of birth")
    void nonSecureActiveOfficerDetailsDobTest() throws ParseException {
        officer.setSecureIndicator("N");
        assertEquals( FORMATTED_DOB, officer.getDateOfBirth());
    }

    @Test
    @Description("Should return the secure officer's formatted full date of birth")
    void secureActiveOfficerDetailsDobTest() throws ParseException {
        officer.setSecureIndicator("Y");
        assertEquals( FORMATTED_DOB, officer.getDateOfBirth());
    }

    @Test
    @Description("Should return the non-secure officer's URA")
    void nonSecureActiveOfficerDetailsUraTest() {
        officer.setSecureIndicator("N");
        assertEquals(URA_LINE_1, officer.getResidentialAddress().getAddressLine1());
        assertEquals(URA_POST_TOWN, officer.getResidentialAddress().getLocality());
        assertEquals(URA_POST_CODE, officer.getResidentialAddress().getPostalCode());
    }

    @Test
    @Description("Should NOT return the secure officer's URA but just a message in line 1")
    void secureActiveOfficerDetailsUraTest() {
        officer.setSecureIndicator("Y");
        assertEquals(SECURE_DIRECTOR_URA_LINE_1, officer.getResidentialAddress().getAddressLine1());
        assertEquals( null, officer.getResidentialAddress().getLocality());
        assertEquals(null, officer.getResidentialAddress().getPostalCode());
    }

    @Test
    @Description("Should not contain the secure indicator in the json of a non-secure officer")
    void nonSecureActiveOfficerDetailsSecureIndicatorTest() throws JsonProcessingException {
        officer.setSecureIndicator("N");
        String json = new ObjectMapper().writeValueAsString(officer);
        assertFalse(json.contains("secure"));
        assertFalse(json.contains("\"N\""));
    }

    @Test
    @Description("Should not contain the secure indicator in the json of a secure officer")
    void secureActiveOfficerDetailsSecureIndicatorTest() throws JsonProcessingException {
        officer.setSecureIndicator("Y");
        String json = new ObjectMapper().writeValueAsString(officer);
        assertFalse(json.contains("secure"));
        assertFalse(json.contains("\"Y\""));
    }

}
