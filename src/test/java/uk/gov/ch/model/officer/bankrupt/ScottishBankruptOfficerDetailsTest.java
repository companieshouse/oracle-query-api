package uk.gov.ch.model.officer.bankrupt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScottishBankruptOfficerDetailsTest {

    private ScottishBankruptOfficerDetails scottishBankruptOfficerDetailsUnderTest;

    @BeforeEach
    void setUp() {
        scottishBankruptOfficerDetailsUnderTest = new ScottishBankruptOfficerDetails();
    }

    @Test
    void testEphemeralKeyGetterAndSetter() {
        final String ephemeralKey = "ephemeralKey";
        scottishBankruptOfficerDetailsUnderTest.setEphemeralKey(ephemeralKey);
        assertEquals(scottishBankruptOfficerDetailsUnderTest.getEphemeralKey(),ephemeralKey);
    }

    @Test
    void testForename1GetterAndSetter() {
        final String forename1 = "forename1";
        scottishBankruptOfficerDetailsUnderTest.setForename1(forename1);
        assertEquals(scottishBankruptOfficerDetailsUnderTest.getForename1(),forename1);
    }

    @Test
    void testForename2GetterAndSetter() {
        final String forename2 = "forename2";
        scottishBankruptOfficerDetailsUnderTest.setForename2(forename2);
        assertEquals(scottishBankruptOfficerDetailsUnderTest.getForename2(),forename2);
    }

    @Test
    void testSurnameGetterAndSetter() {
        final String surname = "surname";
        scottishBankruptOfficerDetailsUnderTest.setSurname(surname);
        assertEquals(scottishBankruptOfficerDetailsUnderTest.getSurname(),surname);
    }

    @Test
    void testAddressLine1GetterAndSetter() {
        final String addressLine1 = "addressLine1";
        scottishBankruptOfficerDetailsUnderTest.setAddressLine1(addressLine1);
        assertEquals(scottishBankruptOfficerDetailsUnderTest.getAddressLine1(),addressLine1);
    }

    @Test
    void testAddressLine2GetterAndSetter() {
        final String addressLine2 = "addressLine2";
        scottishBankruptOfficerDetailsUnderTest.setAddressLine2(addressLine2);
        assertEquals(scottishBankruptOfficerDetailsUnderTest.getAddressLine2(),addressLine2);
    }

    @Test
    void testAddressLine3GetterAndSetter() {
        final String addressLine3 = "addressLine3";
        scottishBankruptOfficerDetailsUnderTest.setAddressLine3(addressLine3);
        assertEquals(scottishBankruptOfficerDetailsUnderTest.getAddressLine3(),addressLine3);
    }

    @Test
    void testTownGetterAndSetter() {
        final String town = "town";
        scottishBankruptOfficerDetailsUnderTest.setTown(town);
        assertEquals(scottishBankruptOfficerDetailsUnderTest.getTown(),town);
    }

    @Test
    void testCountyGetterAndSetter() {
        final String county = "county";
        scottishBankruptOfficerDetailsUnderTest.setCounty(county);
        assertEquals(scottishBankruptOfficerDetailsUnderTest.getCounty(),county);
    }

    @Test
    void testPostcodeGetterAndSetter() {
        final String postcode = "postcode";
        scottishBankruptOfficerDetailsUnderTest.setPostcode(postcode);
        assertEquals(scottishBankruptOfficerDetailsUnderTest.getPostcode(),postcode);
    }

    @Test
    void testDateOfBirthGetterAndSetter() {
        final LocalDate dateOfBirth = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerDetailsUnderTest.setDateOfBirth(dateOfBirth);
        assertEquals(scottishBankruptOfficerDetailsUnderTest.getDateOfBirth(),dateOfBirth);
    }

    @Test
    void testAliasGetterAndSetter() {
        final String alias = "alias";
        scottishBankruptOfficerDetailsUnderTest.setAlias(alias);
        assertEquals(scottishBankruptOfficerDetailsUnderTest.getAlias(),alias);
    }

    @Test
    void testCaseReferenceGetterAndSetter() {
        final String caseReference = "caseReference";
        scottishBankruptOfficerDetailsUnderTest.setCaseReference(caseReference);
        assertEquals(scottishBankruptOfficerDetailsUnderTest.getCaseReference(),caseReference);
    }

    @Test
    void testCaseTypeGetterAndSetter() {
        final String caseType = "caseType";
        scottishBankruptOfficerDetailsUnderTest.setCaseType(caseType);
        assertEquals(scottishBankruptOfficerDetailsUnderTest.getCaseType(),caseType);
    }

    @Test
    void testBankruptcyTypeGetterAndSetter() {
        final String bankruptcyType = "bankruptcyType";
        scottishBankruptOfficerDetailsUnderTest.setBankruptcyType(bankruptcyType);
        assertEquals(scottishBankruptOfficerDetailsUnderTest.getBankruptcyType(),bankruptcyType);
    }

    @Test
    void testStartDateGetterAndSetter() {
        final LocalDate startDate = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerDetailsUnderTest.setStartDate(startDate);
        assertEquals(scottishBankruptOfficerDetailsUnderTest.getStartDate(),startDate);
    }

    @Test
    void testDebtorDischargeDateGetterAndSetter() {
        final LocalDate debtorDischargeDate = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerDetailsUnderTest.setDebtorDischargeDate(debtorDischargeDate);
        assertEquals(scottishBankruptOfficerDetailsUnderTest.getDebtorDischargeDate(),debtorDischargeDate);
    }

    @Test
    void testTrusteeDischargeDateGetterAndSetter() {
        final LocalDate trusteeDischargeDate = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerDetailsUnderTest.setTrusteeDischargeDate(trusteeDischargeDate);
        assertEquals(scottishBankruptOfficerDetailsUnderTest.getTrusteeDischargeDate(),trusteeDischargeDate);
    }
}
