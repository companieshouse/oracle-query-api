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

    ActiveDirectorDetails director;
    String URA_LINE_1 = "123 Street";
    String URA_POST_TOWN = "Town";
    String URA_POST_CODE = "POST CODE";
    String UNFORMATTED_DOB = "1962-01-29 00:00:00.0";
    String FORMATTED_DOB = "29 January 1962";
    String SECURE_DIRECTOR_URA_LINE_1 = "Companies House Cannot Disclose this Home Address";

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
        director.setServiceAddressLine1("Diddly squat farm shop");
        director.setServiceAddressPostTown("Chadlington");
        director.setServiceAddressPostCode("OX7 3PE");
        director.setUraLine1(URA_LINE_1);
        director.setUraPostTown(URA_POST_TOWN);
        director.setUraPostCode(URA_POST_CODE);
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
        assertEquals(URA_LINE_1, director.getUraLine1());
        assertEquals(URA_POST_TOWN, director.getUraPostTown());
        assertEquals(URA_POST_CODE, director.getUraPostCode());
    }

    @Test
    @Description("Should NOT return the secure director's URA but just a message in line 1")
    void secureActiveDirectorDetailsUraTest() {
        director.setSecureIndicator("Y");
        assertEquals(SECURE_DIRECTOR_URA_LINE_1, director.getUraLine1());
        assertEquals( null, director.getUraPostTown());
        assertEquals(null, director.getUraPostCode());
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