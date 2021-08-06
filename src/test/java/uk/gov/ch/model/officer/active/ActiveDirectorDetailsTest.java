package uk.gov.ch.model.officer.active;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class ActiveOfficerDetailsTest {

    ActiveOfficerDetails officer;
    String uraLine1 = "123 Street";
    String uraPostTown = "Town";
    String uraPostCode = "POST CODE";
    String dob = "1962-01-29 00:00:00.0";
    String secureDOB = "January 1962";
    String nonSecureDOB = "29 January 1962";

    @BeforeEach
    void beforeEach(){
        officer = new ActiveOfficerDetails();
        officer.setDateOfBirth(dob);
        officer.setUraLine1(uraLine1);
        officer.setUraPostTown(uraPostTown);
        officer.setUraPostCode(uraPostCode);
    }

    @Test
    void getUraLine1NonSecureOfficer(){
        officer.setSecureIndicator("N");
        String result = officer.getUraLine1();
        assertEquals(uraLine1, result);
    }

    @Test
    void getUraLine1SecureOfficer(){
        officer.setSecureIndicator("Y");
        String result = officer.getUraLine1();
        assertNull(result);
    }

    @Test
    void getUraPostTownNonSecureOfficer(){
        officer.setSecureIndicator("N");
        String result = officer.getUraPostTown();
        assertEquals(uraPostTown, result);
    }

    @Test
    void getUraPostTownSecureOfficer(){
        officer.setSecureIndicator("Y");
        String result = officer.getUraPostTown();
        assertNull(result);
    }

    @Test
    void getUraPostCodeNonSecureOfficer(){
        officer.setSecureIndicator("N");
        String result = officer.getUraPostCode();
        assertEquals(uraPostCode, result);
    }

    @Test
    void getUraPostCodeSecureOfficer(){
        officer.setSecureIndicator("Y");
        String result = officer.getUraPostCode();
        assertNull(result);
    }

    @Test
    void getDateOfBirthNonSecureOfficer() throws ParseException {
        officer.setSecureIndicator("N");
        String result = officer.getDateOfBirth();
        assertEquals(nonSecureDOB, result);
    }

    @Test
    void getDateOfBirthSecureOfficer() throws ParseException {
        officer.setSecureIndicator("Y");
        String result = officer.getDateOfBirth();
        assertEquals(nonSecureDOB, result);
    }

}