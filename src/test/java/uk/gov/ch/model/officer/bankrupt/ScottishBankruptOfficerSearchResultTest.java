package uk.gov.ch.model.officer.bankrupt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(scottishBankruptOfficerSearchResultUnderTest.getEphemeralKey()).isEqualTo(ephemeralKey);
    }

    @Test
    void testForename1GetterAndSetter() {
        final String forename1 = "forename1";
        scottishBankruptOfficerSearchResultUnderTest.setForename1(forename1);
        assertThat(scottishBankruptOfficerSearchResultUnderTest.getForename1()).isEqualTo(forename1);
    }

    @Test
    void testForename2GetterAndSetter() {
        final String forename2 = "forename2";
        scottishBankruptOfficerSearchResultUnderTest.setForename2(forename2);
        assertThat(scottishBankruptOfficerSearchResultUnderTest.getForename2()).isEqualTo(forename2);
    }

    @Test
    void testSurnameGetterAndSetter() {
        final String surname = "surname";
        scottishBankruptOfficerSearchResultUnderTest.setSurname(surname);
        assertThat(scottishBankruptOfficerSearchResultUnderTest.getSurname()).isEqualTo(surname);
    }

    @Test
    void testAddressLine1GetterAndSetter() {
        final String addressLine1 = "addressLine1";
        scottishBankruptOfficerSearchResultUnderTest.setAddressLine1(addressLine1);
        assertThat(scottishBankruptOfficerSearchResultUnderTest.getAddressLine1()).isEqualTo(addressLine1);
    }

    @Test
    void testAddressLine2GetterAndSetter() {
        final String addressLine2 = "addressLine2";
        scottishBankruptOfficerSearchResultUnderTest.setAddressLine2(addressLine2);
        assertThat(scottishBankruptOfficerSearchResultUnderTest.getAddressLine2()).isEqualTo(addressLine2);
    }

    @Test
    void testAddressLine3GetterAndSetter() {
        final String addressLine3 = "addressLine3";
        scottishBankruptOfficerSearchResultUnderTest.setAddressLine3(addressLine3);
        assertThat(scottishBankruptOfficerSearchResultUnderTest.getAddressLine3()).isEqualTo(addressLine3);
    }

    @Test
    void testTownGetterAndSetter() {
        final String town = "town";
        scottishBankruptOfficerSearchResultUnderTest.setTown(town);
        assertThat(scottishBankruptOfficerSearchResultUnderTest.getTown()).isEqualTo(town);
    }

    @Test
    void testCountyGetterAndSetter() {
        final String county = "county";
        scottishBankruptOfficerSearchResultUnderTest.setCounty(county);
        assertThat(scottishBankruptOfficerSearchResultUnderTest.getCounty()).isEqualTo(county);
    }

    @Test
    void testPostcodeGetterAndSetter() {
        final String postcode = "postcode";
        scottishBankruptOfficerSearchResultUnderTest.setPostcode(postcode);
        assertThat(scottishBankruptOfficerSearchResultUnderTest.getPostcode()).isEqualTo(postcode);
    }

    @Test
    void testDateOfBirthGetterAndSetter() {
        final LocalDate dateOfBirth = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerSearchResultUnderTest.setDateOfBirth(dateOfBirth);
        assertThat(scottishBankruptOfficerSearchResultUnderTest.getDateOfBirth()).isEqualTo(dateOfBirth);
    }

    @Test
    void testDebtorDischargeDateGetterAndSetter() {
        final LocalDate debtorDischargeDate = LocalDate.of(2020, 1, 1);
        scottishBankruptOfficerSearchResultUnderTest.setDebtorDischargeDate(debtorDischargeDate);
        assertThat(scottishBankruptOfficerSearchResultUnderTest.getDebtorDischargeDate())
                .isEqualTo(debtorDischargeDate);
    }

    @Test
    void testCaseTypeGetterAndSetter() {
        final String caseType = "caseType";
        scottishBankruptOfficerSearchResultUnderTest.setCaseType(caseType);
        assertThat(scottishBankruptOfficerSearchResultUnderTest.getCaseType()).isEqualTo(caseType);
    }
}
