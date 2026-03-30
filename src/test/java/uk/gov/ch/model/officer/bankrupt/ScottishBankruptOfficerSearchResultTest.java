package uk.gov.ch.model.officer.bankrupt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScottishBankruptOfficerSearchResultTest {

    private ScottishBankruptOfficerSearchResult scottishBankruptOfficerSearchResultUnderTest;

    @BeforeEach
    void setUp() {
        scottishBankruptOfficerSearchResultUnderTest = new ScottishBankruptOfficerSearchResult();
    }

    @Test
    void testEphemeralKeyGetterAndSetter() {
        final String ephemeralKey = "ephemeralKey";
        scottishBankruptOfficerSearchResultUnderTest.setEphemeralKey(ephemeralKey);
        assertEquals(scottishBankruptOfficerSearchResultUnderTest.getEphemeralKey(),ephemeralKey);
    }

    @Test
    void testForename1GetterAndSetter() {
        final String forename1 = "forename1";
        scottishBankruptOfficerSearchResultUnderTest.setForename1(forename1);
        assertEquals(scottishBankruptOfficerSearchResultUnderTest.getForename1(),forename1);
    }

    @Test
    void testForename2GetterAndSetter() {
        final String forename2 = "forename2";
        scottishBankruptOfficerSearchResultUnderTest.setForename2(forename2);
        assertEquals(scottishBankruptOfficerSearchResultUnderTest.getForename2(),forename2);
    }

    @Test
    void testSurnameGetterAndSetter() {
        final String surname = "surname";
        scottishBankruptOfficerSearchResultUnderTest.setSurname(surname);
        assertEquals(scottishBankruptOfficerSearchResultUnderTest.getSurname(),surname);
    }

    @Test
    void testAddressLine1GetterAndSetter() {
        final String addressLine1 = "addressLine1";
        scottishBankruptOfficerSearchResultUnderTest.setAddressLine1(addressLine1);
        assertEquals(scottishBankruptOfficerSearchResultUnderTest.getAddressLine1(),addressLine1);
    }

    @Test
    void testAddressLine2GetterAndSetter() {
        final String addressLine2 = "addressLine2";
        scottishBankruptOfficerSearchResultUnderTest.setAddressLine2(addressLine2);
        assertEquals(scottishBankruptOfficerSearchResultUnderTest.getAddressLine2(),addressLine2);
    }

    @Test
    void testAddressLine3GetterAndSetter() {
        final String addressLine3 = "addressLine3";
        scottishBankruptOfficerSearchResultUnderTest.setAddressLine3(addressLine3);
        assertEquals(scottishBankruptOfficerSearchResultUnderTest.getAddressLine3(),addressLine3);
    }

    @Test
    void testTownGetterAndSetter() {
        final String town = "town";
        scottishBankruptOfficerSearchResultUnderTest.setTown(town);
        assertEquals(scottishBankruptOfficerSearchResultUnderTest.getTown(),town);
    }

    @Test
    void testCountyGetterAndSetter() {
        final String county = "county";
        scottishBankruptOfficerSearchResultUnderTest.setCounty(county);
        assertEquals(scottishBankruptOfficerSearchResultUnderTest.getCounty(),county);
    }

    @Test
    void testPostcodeGetterAndSetter() {
        final String postcode = "postcode";
        scottishBankruptOfficerSearchResultUnderTest.setPostcode(postcode);
        assertEquals(scottishBankruptOfficerSearchResultUnderTest.getPostcode(),postcode);
    }

    @Test
    void testDateOfBirthGetterAndSetter() {
        final LocalDate dateOfBirth = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerSearchResultUnderTest.setDateOfBirth(dateOfBirth);
        assertEquals(scottishBankruptOfficerSearchResultUnderTest.getDateOfBirth(),dateOfBirth);
    }

    @Test
    void testDebtorDischargeDateGetterAndSetter() {
        final LocalDate debtorDischargeDate = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerSearchResultUnderTest.setDebtorDischargeDate(debtorDischargeDate);
        assertEquals(scottishBankruptOfficerSearchResultUnderTest.getDebtorDischargeDate(),debtorDischargeDate);
    }

    @Test
    void testCaseTypeGetterAndSetter() {
        final String caseType = "caseType";
        scottishBankruptOfficerSearchResultUnderTest.setCaseType(caseType);
        assertEquals(scottishBankruptOfficerSearchResultUnderTest.getCaseType(),caseType);
    }
}
