package uk.gov.ch.model.officer.bankrupt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScottishBankruptOfficerBaseTest {

    private ScottishBankruptOfficerBase scottishBankruptOfficerBaseUnderTest;

    @BeforeEach
    void setUp() {
        scottishBankruptOfficerBaseUnderTest = new ScottishBankruptOfficerBase() {};
    }

    @Test
    void testEphemeralKeyGetterAndSetter() {
        final String ephemeralKey = "ephemeralKey";
        scottishBankruptOfficerBaseUnderTest.setEphemeralKey(ephemeralKey);
        assertEquals(scottishBankruptOfficerBaseUnderTest.getEphemeralKey(),ephemeralKey);
    }

    @Test
    void testForename1GetterAndSetter() {
        final String forename1 = "forename1";
        scottishBankruptOfficerBaseUnderTest.setForename1(forename1);
        assertEquals(scottishBankruptOfficerBaseUnderTest.getForename1(),forename1);
    }

    @Test
    void testForename2GetterAndSetter() {
        final String forename2 = "forename2";
        scottishBankruptOfficerBaseUnderTest.setForename2(forename2);
        assertEquals(scottishBankruptOfficerBaseUnderTest.getForename2(),forename2);
    }

    @Test
    void testSurnameGetterAndSetter() {
        final String surname = "surname";
        scottishBankruptOfficerBaseUnderTest.setSurname(surname);
        assertEquals(scottishBankruptOfficerBaseUnderTest.getSurname(),surname);
    }

    @Test
    void testAddressLine1GetterAndSetter() {
        final String addressLine1 = "addressLine1";
        scottishBankruptOfficerBaseUnderTest.setAddressLine1(addressLine1);
        assertEquals(scottishBankruptOfficerBaseUnderTest.getAddressLine1(),addressLine1);
    }

    @Test
    void testAddressLine2GetterAndSetter() {
        final String addressLine2 = "addressLine2";
        scottishBankruptOfficerBaseUnderTest.setAddressLine2(addressLine2);
        assertEquals(scottishBankruptOfficerBaseUnderTest.getAddressLine2(),addressLine2);
    }

    @Test
    void testAddressLine3GetterAndSetter() {
        final String addressLine3 = "addressLine3";
        scottishBankruptOfficerBaseUnderTest.setAddressLine3(addressLine3);
        assertEquals(scottishBankruptOfficerBaseUnderTest.getAddressLine3(),addressLine3);
    }

    @Test
    void testTownGetterAndSetter() {
        final String town = "town";
        scottishBankruptOfficerBaseUnderTest.setTown(town);
        assertEquals(scottishBankruptOfficerBaseUnderTest.getTown(),town);
    }

    @Test
    void testCountyGetterAndSetter() {
        final String county = "county";
        scottishBankruptOfficerBaseUnderTest.setCounty(county);
        assertEquals(scottishBankruptOfficerBaseUnderTest.getCounty(),county);
    }

    @Test
    void testPostcodeGetterAndSetter() {
        final String postcode = "postcode";
        scottishBankruptOfficerBaseUnderTest.setPostcode(postcode);
        assertEquals(scottishBankruptOfficerBaseUnderTest.getPostcode(),postcode);
    }

    @Test
    void testDateOfBirthGetterAndSetter() {
        final LocalDate dateOfBirth = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerBaseUnderTest.setDateOfBirth(dateOfBirth);
        assertEquals(scottishBankruptOfficerBaseUnderTest.getDateOfBirth(),dateOfBirth);
    }

    @Test
    void testAliasGetterAndSetter() {
        final String alias = "alias";
        scottishBankruptOfficerBaseUnderTest.setAlias(alias);
        assertEquals(scottishBankruptOfficerBaseUnderTest.getAlias(),alias);
    }

    @Test
    void testCaseReferenceGetterAndSetter() {
        final String caseReference = "caseReference";
        scottishBankruptOfficerBaseUnderTest.setCaseReference(caseReference);
        assertEquals(scottishBankruptOfficerBaseUnderTest.getCaseReference(),caseReference);
    }

    @Test
    void testCaseTypeGetterAndSetter() {
        final String caseType = "caseType";
        scottishBankruptOfficerBaseUnderTest.setCaseType(caseType);
        assertEquals(scottishBankruptOfficerBaseUnderTest.getCaseType(),caseType);
    }

    @Test
    void testBankruptcyTypeGetterAndSetter() {
        final String bankruptcyType = "bankruptcyType";
        scottishBankruptOfficerBaseUnderTest.setBankruptcyType(bankruptcyType);
        assertEquals(scottishBankruptOfficerBaseUnderTest.getBankruptcyType(),bankruptcyType);
    }

    @Test
    void testStartDateGetterAndSetter() {
        final LocalDate startDate = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerBaseUnderTest.setStartDate(startDate);
        assertEquals(scottishBankruptOfficerBaseUnderTest.getStartDate(),startDate);
    }

    @Test
    void testDebtorDischargeDateGetterAndSetter() {
        final LocalDate debtorDischargeDate = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerBaseUnderTest.setDebtorDischargeDate(debtorDischargeDate);
        assertEquals(scottishBankruptOfficerBaseUnderTest.getDebtorDischargeDate(),debtorDischargeDate);
    }

    @Test
    void testTrusteeDischargeDateGetterAndSetter() {
        final LocalDate trusteeDischargeDate = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerBaseUnderTest.setTrusteeDischargeDate(trusteeDischargeDate);
        assertEquals(scottishBankruptOfficerBaseUnderTest.getTrusteeDischargeDate(),trusteeDischargeDate);
    }
}
