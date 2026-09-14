package uk.gov.ch.model.officer.active;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

class ActiveOfficerDetailsTest {

    private ActiveOfficerDetails officer;
    private static final String URA_LINE_1 = "123 Street";
    private static final String URA_POST_TOWN = "Town";
    private static final String URA_POST_CODE = "POST CODE";
    private static final String UNFORMATTED_DOB = "1962-01-29 00:00:00.0";
    private static final String UNFORMATTED_DOA = "2009-01-29 00:00:00.0";
    private static final String FORMATTED_DOB = "29 January 1962";
    private static final String COUNTRY_OF_RESIDENCE = "Country";
    private static final String SECURE_DIRECTOR_URA_LINE_1 = "Companies House Cannot Disclose this Home Address";

    @BeforeEach
    void beforeEach() {
        officer = new ActiveOfficerDetails();
        officer.setOfficerDetailId(1L);
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
        assertNull(officer.getResidentialAddress().getLocality());
        assertNull(officer.getResidentialAddress().getPostalCode());
    }

    @Test
    @Description("Should not contain the secure indicator in the json of a non-secure officer")
    void nonSecureActiveOfficerDetailsSecureIndicatorTest() throws JacksonException {
        officer.setSecureIndicator("N");
        String json = new JsonMapper().writeValueAsString(officer);
        assertFalse(json.contains("secure"));
        assertFalse(json.contains("\"N\""));
    }

    @Test
    @Description("Should not contain the secure indicator in the json of a secure officer")
    void secureActiveOfficerDetailsSecureIndicatorTest() throws JacksonException {
        officer.setSecureIndicator("Y");
        String json = new JsonMapper().writeValueAsString(officer);
        assertFalse(json.contains("secure"));
        assertFalse(json.contains("\"Y\""));
    }

}
