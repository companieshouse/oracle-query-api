package uk.gov.ch.model.officer.bankrupt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ScottishBankruptOfficerDataModelTest {

    private ScottishBankruptOfficerDataModel scottishBankruptOfficerDataModelUnderTest;

    @BeforeEach
    void setUp() {
        scottishBankruptOfficerDataModelUnderTest = new ScottishBankruptOfficerDataModel();
    }

    @Test
    void testEphemeralKeyGetterAndSetter() {
        final String ephemeralKey = "ephemeralKey";
        scottishBankruptOfficerDataModelUnderTest.setEphemeralKey(ephemeralKey);
        assertThat(scottishBankruptOfficerDataModelUnderTest.getEphemeralKey()).isEqualTo(ephemeralKey);
    }

    @Test
    void testAddressLine1GetterAndSetter() {
        final String addressLine1 = "addressLine1";
        scottishBankruptOfficerDataModelUnderTest.setAddressLine1(addressLine1);
        assertThat(scottishBankruptOfficerDataModelUnderTest.getAddressLine1()).isEqualTo(addressLine1);
    }

    @Test
    void testAddressLine2GetterAndSetter() {
        final String addressLine2 = "addressLine2";
        scottishBankruptOfficerDataModelUnderTest.setAddressLine2(addressLine2);
        assertThat(scottishBankruptOfficerDataModelUnderTest.getAddressLine2()).isEqualTo(addressLine2);
    }

    @Test
    void testAddressLine3GetterAndSetter() {
        final String addressLine3 = "addressLine3";
        scottishBankruptOfficerDataModelUnderTest.setAddressLine3(addressLine3);
        assertThat(scottishBankruptOfficerDataModelUnderTest.getAddressLine3()).isEqualTo(addressLine3);
    }

    @Test
    void testTownGetterAndSetter() {
        final String town = "town";
        scottishBankruptOfficerDataModelUnderTest.setTown(town);
        assertThat(scottishBankruptOfficerDataModelUnderTest.getTown()).isEqualTo(town);
    }

    @Test
    void testCountyGetterAndSetter() {
        final String county = "county";
        scottishBankruptOfficerDataModelUnderTest.setCounty(county);
        assertThat(scottishBankruptOfficerDataModelUnderTest.getCounty()).isEqualTo(county);
    }

    @Test
    void testPostcodeGetterAndSetter() {
        final String postcode = "postcode";
        scottishBankruptOfficerDataModelUnderTest.setPostcode(postcode);
        assertThat(scottishBankruptOfficerDataModelUnderTest.getPostcode()).isEqualTo(postcode);
    }

    @Test
    void testDateOfBirthGetterAndSetter() {
        final LocalDate dateOfBirth = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerDataModelUnderTest.setDateOfBirth(dateOfBirth);
        assertThat(scottishBankruptOfficerDataModelUnderTest.getDateOfBirth()).isEqualTo(dateOfBirth);
    }

    @Test
    void testAliasGetterAndSetter() {
        final String alias = "alias";
        scottishBankruptOfficerDataModelUnderTest.setAlias(alias);
        assertThat(scottishBankruptOfficerDataModelUnderTest.getAlias()).isEqualTo(alias);
    }

    @Test
    void testCaseReferenceGetterAndSetter() {
        final String caseReference = "caseReference";
        scottishBankruptOfficerDataModelUnderTest.setCaseReference(caseReference);
        assertThat(scottishBankruptOfficerDataModelUnderTest.getCaseReference()).isEqualTo(caseReference);
    }

    @Test
    void testCaseTypeGetterAndSetter() {
        final String caseType = "caseType";
        scottishBankruptOfficerDataModelUnderTest.setCaseType(caseType);
        assertThat(scottishBankruptOfficerDataModelUnderTest.getCaseType()).isEqualTo(caseType);
    }

    @Test
    void testBankruptcyTypeGetterAndSetter() {
        final String bankruptcyType = "bankruptcyType";
        scottishBankruptOfficerDataModelUnderTest.setBankruptcyType(bankruptcyType);
        assertThat(scottishBankruptOfficerDataModelUnderTest.getBankruptcyType()).isEqualTo(bankruptcyType);
    }

    @Test
    void testStartDateGetterAndSetter() {
        final LocalDate startDate = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerDataModelUnderTest.setStartDate(startDate);
        assertThat(scottishBankruptOfficerDataModelUnderTest.getStartDate()).isEqualTo(startDate);
    }

    @Test
    void testDebtorDischargeDateGetterAndSetter() {
        final LocalDate debtorDischargeDate = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerDataModelUnderTest.setDebtorDischargeDate(debtorDischargeDate);
        assertThat(scottishBankruptOfficerDataModelUnderTest.getDebtorDischargeDate()).isEqualTo(debtorDischargeDate);
    }

    @Test
    void testTrusteeDischargeDateGetterAndSetter() {
        final LocalDate trusteeDischargeDate = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerDataModelUnderTest.setTrusteeDischargeDate(trusteeDischargeDate);
        assertThat(scottishBankruptOfficerDataModelUnderTest.getTrusteeDischargeDate()).isEqualTo(trusteeDischargeDate);
    }

    @Test
    void testForename1GetterAndSetter() {
        final String forename1 = "forename1";
        scottishBankruptOfficerDataModelUnderTest.setForename1(forename1);
        assertThat(scottishBankruptOfficerDataModelUnderTest.getForename1()).isEqualTo(forename1);
    }

    @Test
    void testForename2GetterAndSetter() {
        final String forename2 = "forename2";
        scottishBankruptOfficerDataModelUnderTest.setForename2(forename2);
        assertThat(scottishBankruptOfficerDataModelUnderTest.getForename2()).isEqualTo(forename2);
    }

    @Test
    void testSurnameGetterAndSetter() {
        final String surname = "surname";
        scottishBankruptOfficerDataModelUnderTest.setSurname(surname);
        assertThat(scottishBankruptOfficerDataModelUnderTest.getSurname()).isEqualTo(surname);
    }
}
