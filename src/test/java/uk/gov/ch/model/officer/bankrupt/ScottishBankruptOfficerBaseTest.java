package uk.gov.ch.model.officer.bankrupt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(scottishBankruptOfficerBaseUnderTest.getEphemeralKey()).isEqualTo(ephemeralKey);
    }

    @Test
    void testForename1GetterAndSetter() {
        final String forename1 = "forename1";
        scottishBankruptOfficerBaseUnderTest.setForename1(forename1);
        assertThat(scottishBankruptOfficerBaseUnderTest.getForename1()).isEqualTo(forename1);
    }

    @Test
    void testForename2GetterAndSetter() {
        final String forename2 = "forename2";
        scottishBankruptOfficerBaseUnderTest.setForename2(forename2);
        assertThat(scottishBankruptOfficerBaseUnderTest.getForename2()).isEqualTo(forename2);
    }

    @Test
    void testSurnameGetterAndSetter() {
        final String surname = "surname";
        scottishBankruptOfficerBaseUnderTest.setSurname(surname);
        assertThat(scottishBankruptOfficerBaseUnderTest.getSurname()).isEqualTo(surname);
    }

    @Test
    void testAddressLine1GetterAndSetter() {
        final String addressLine1 = "addressLine1";
        scottishBankruptOfficerBaseUnderTest.setAddressLine1(addressLine1);
        assertThat(scottishBankruptOfficerBaseUnderTest.getAddressLine1()).isEqualTo(addressLine1);
    }

    @Test
    void testAddressLine2GetterAndSetter() {
        final String addressLine2 = "addressLine2";
        scottishBankruptOfficerBaseUnderTest.setAddressLine2(addressLine2);
        assertThat(scottishBankruptOfficerBaseUnderTest.getAddressLine2()).isEqualTo(addressLine2);
    }

    @Test
    void testAddressLine3GetterAndSetter() {
        final String addressLine3 = "addressLine3";
        scottishBankruptOfficerBaseUnderTest.setAddressLine3(addressLine3);
        assertThat(scottishBankruptOfficerBaseUnderTest.getAddressLine3()).isEqualTo(addressLine3);
    }

    @Test
    void testTownGetterAndSetter() {
        final String town = "town";
        scottishBankruptOfficerBaseUnderTest.setTown(town);
        assertThat(scottishBankruptOfficerBaseUnderTest.getTown()).isEqualTo(town);
    }

    @Test
    void testCountyGetterAndSetter() {
        final String county = "county";
        scottishBankruptOfficerBaseUnderTest.setCounty(county);
        assertThat(scottishBankruptOfficerBaseUnderTest.getCounty()).isEqualTo(county);
    }

    @Test
    void testPostcodeGetterAndSetter() {
        final String postcode = "postcode";
        scottishBankruptOfficerBaseUnderTest.setPostcode(postcode);
        assertThat(scottishBankruptOfficerBaseUnderTest.getPostcode()).isEqualTo(postcode);
    }

    @Test
    void testDateOfBirthGetterAndSetter() {
        final LocalDate dateOfBirth = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerBaseUnderTest.setDateOfBirth(dateOfBirth);
        assertThat(scottishBankruptOfficerBaseUnderTest.getDateOfBirth()).isEqualTo(dateOfBirth);
    }

    @Test
    void testCaseTypeGetterAndSetter() {
        final String caseType = "caseType";
        scottishBankruptOfficerBaseUnderTest.setCaseType(caseType);
        assertThat(scottishBankruptOfficerBaseUnderTest.getCaseType()).isEqualTo(caseType);
    }

    @Test
    void testDebtorDischargeDateGetterAndSetter() {
        final LocalDate debtorDischargeDate = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerBaseUnderTest.setDebtorDischargeDate(debtorDischargeDate);
        assertThat(scottishBankruptOfficerBaseUnderTest.getDebtorDischargeDate()).isEqualTo(debtorDischargeDate);
    }
}
