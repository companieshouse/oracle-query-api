package uk.gov.ch.model.officer.bankrupt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(scottishBankruptOfficerDetailsUnderTest.getEphemeralKey()).isEqualTo(ephemeralKey);
    }

    @Test
    void testForename1GetterAndSetter() {
        final String forename1 = "forename1";
        scottishBankruptOfficerDetailsUnderTest.setForename1(forename1);
        assertThat(scottishBankruptOfficerDetailsUnderTest.getForename1()).isEqualTo(forename1);
    }

    @Test
    void testForename2GetterAndSetter() {
        final String forename2 = "forename2";
        scottishBankruptOfficerDetailsUnderTest.setForename2(forename2);
        assertThat(scottishBankruptOfficerDetailsUnderTest.getForename2()).isEqualTo(forename2);
    }

    @Test
    void testSurnameGetterAndSetter() {
        final String surname = "surname";
        scottishBankruptOfficerDetailsUnderTest.setSurname(surname);
        assertThat(scottishBankruptOfficerDetailsUnderTest.getSurname()).isEqualTo(surname);
    }

    @Test
    void testAddressLine1GetterAndSetter() {
        final String addressLine1 = "addressLine1";
        scottishBankruptOfficerDetailsUnderTest.setAddressLine1(addressLine1);
        assertThat(scottishBankruptOfficerDetailsUnderTest.getAddressLine1()).isEqualTo(addressLine1);
    }

    @Test
    void testAddressLine2GetterAndSetter() {
        final String addressLine2 = "addressLine2";
        scottishBankruptOfficerDetailsUnderTest.setAddressLine2(addressLine2);
        assertThat(scottishBankruptOfficerDetailsUnderTest.getAddressLine2()).isEqualTo(addressLine2);
    }

    @Test
    void testAddressLine3GetterAndSetter() {
        final String addressLine3 = "addressLine3";
        scottishBankruptOfficerDetailsUnderTest.setAddressLine3(addressLine3);
        assertThat(scottishBankruptOfficerDetailsUnderTest.getAddressLine3()).isEqualTo(addressLine3);
    }

    @Test
    void testTownGetterAndSetter() {
        final String town = "town";
        scottishBankruptOfficerDetailsUnderTest.setTown(town);
        assertThat(scottishBankruptOfficerDetailsUnderTest.getTown()).isEqualTo(town);
    }

    @Test
    void testCountyGetterAndSetter() {
        final String county = "county";
        scottishBankruptOfficerDetailsUnderTest.setCounty(county);
        assertThat(scottishBankruptOfficerDetailsUnderTest.getCounty()).isEqualTo(county);
    }

    @Test
    void testPostcodeGetterAndSetter() {
        final String postcode = "postcode";
        scottishBankruptOfficerDetailsUnderTest.setPostcode(postcode);
        assertThat(scottishBankruptOfficerDetailsUnderTest.getPostcode()).isEqualTo(postcode);
    }

    @Test
    void testDateOfBirthGetterAndSetter() {
        final LocalDate dateOfBirth = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerDetailsUnderTest.setDateOfBirth(dateOfBirth);
        assertThat(scottishBankruptOfficerDetailsUnderTest.getDateOfBirth()).isEqualTo(dateOfBirth);
    }

    @Test
    void testAliasGetterAndSetter() {
        final String alias = "alias";
        scottishBankruptOfficerDetailsUnderTest.setAlias(alias);
        assertThat(scottishBankruptOfficerDetailsUnderTest.getAlias()).isEqualTo(alias);
    }

    @Test
    void testCaseReferenceGetterAndSetter() {
        final String caseReference = "caseReference";
        scottishBankruptOfficerDetailsUnderTest.setCaseReference(caseReference);
        assertThat(scottishBankruptOfficerDetailsUnderTest.getCaseReference()).isEqualTo(caseReference);
    }

    @Test
    void testCaseTypeGetterAndSetter() {
        final String caseType = "caseType";
        scottishBankruptOfficerDetailsUnderTest.setCaseType(caseType);
        assertThat(scottishBankruptOfficerDetailsUnderTest.getCaseType()).isEqualTo(caseType);
    }

    @Test
    void testBankruptcyTypeGetterAndSetter() {
        final String bankruptcyType = "bankruptcyType";
        scottishBankruptOfficerDetailsUnderTest.setBankruptcyType(bankruptcyType);
        assertThat(scottishBankruptOfficerDetailsUnderTest.getBankruptcyType()).isEqualTo(bankruptcyType);
    }

    @Test
    void testStartDateGetterAndSetter() {
        final LocalDate startDate = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerDetailsUnderTest.setStartDate(startDate);
        assertThat(scottishBankruptOfficerDetailsUnderTest.getStartDate()).isEqualTo(startDate);
    }

    @Test
    void testDebtorDischargeDateGetterAndSetter() {
        final LocalDate debtorDischargeDate = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerDetailsUnderTest.setDebtorDischargeDate(debtorDischargeDate);
        assertThat(scottishBankruptOfficerDetailsUnderTest.getDebtorDischargeDate()).isEqualTo(debtorDischargeDate);
    }

    @Test
    void testTrusteeDischargeDateGetterAndSetter() {
        final LocalDate trusteeDischargeDate = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerDetailsUnderTest.setTrusteeDischargeDate(trusteeDischargeDate);
        assertThat(scottishBankruptOfficerDetailsUnderTest.getTrusteeDischargeDate()).isEqualTo(trusteeDischargeDate);
    }
}
