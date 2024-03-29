package uk.gov.ch.model.officer.active;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ActiveDirectorDetailsTest {

    private ActiveDirectorDetails director;
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
        director = new ActiveDirectorDetails();
        director.setOfficerDetailId(001L);
        director.setForeName1("JOHN");
        director.setForeName2("MiddleName");
        director.setSurname("DOE");
        director.setOccupation("singer");
        director.setNationality("British");
        director.setDateOfBirth(UNFORMATTED_DOB);
        director.setDateOfAppointment(UNFORMATTED_DOA);
        director.setCountryOfResidence(COUNTRY_OF_RESIDENCE);

        director.setResidentialAddressLine1(URA_LINE_1);
        director.setResidentialAddressLine2("Crown Way");
        director.setResidentialAddressCountry("Wales");
        director.setResidentialAddressLocality(URA_POST_TOWN);
        director.setResidentialAddressPostCode(URA_POST_CODE);
    }

    @Test
    @Description("Should return the non-secure director's formatted full date of birth")
    void nonSecureActiveDirectorDetailsDobTest() throws ParseException {
        director.setSecureIndicator("N");
        assertEquals( FORMATTED_DOB, director.getDateOfBirth());
    }

    @Test
    @Description("Should return the secure director's formatted full date of birth")
    void secureActiveDirectorDetailsDobTest() throws ParseException {
        director.setSecureIndicator("Y");
        assertEquals( FORMATTED_DOB, director.getDateOfBirth());
    }

    @Test
    @Description("Should return the non-secure director's URA")
    void nonSecureActiveDirectorDetailsUraTest() {
        director.setSecureIndicator("N");
        assertEquals(URA_LINE_1, director.getResidentialAddress().getAddressLine1());
        assertEquals(URA_POST_TOWN, director.getResidentialAddress().getLocality());
        assertEquals(URA_POST_CODE, director.getResidentialAddress().getPostalCode());
    }

    @Test
    @Description("Should NOT return the secure director's URA but just a message in line 1")
    void secureActiveDirectorDetailsUraTest() {
        director.setSecureIndicator("Y");
        assertEquals(SECURE_DIRECTOR_URA_LINE_1, director.getResidentialAddress().getAddressLine1());
        assertEquals( null, director.getResidentialAddress().getLocality());
        assertEquals(null, director.getResidentialAddress().getPostalCode());
    }

    @Test
    @Description("Should not contain the secure indicator in the json of a non-secure director")
    void nonSecureActiveDirectorDetailsSecureIndicatorTest() throws JsonProcessingException {
        director.setSecureIndicator("N");
        String json = new ObjectMapper().writeValueAsString(director);
        assertFalse(json.contains("secure"));
        assertFalse(json.contains("\"N\""));
    }

    @Test
    @Description("Should not contain the secure indicator in the json of a secure director")
    void secureActiveDirectorDetailsSecureIndicatorTest() throws JsonProcessingException {
        director.setSecureIndicator("Y");
        String json = new ObjectMapper().writeValueAsString(director);
        assertFalse(json.contains("secure"));
        assertFalse(json.contains("\"Y\""));
    }
}
