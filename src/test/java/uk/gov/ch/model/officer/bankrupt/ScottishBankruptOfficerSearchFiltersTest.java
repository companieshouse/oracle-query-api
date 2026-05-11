package uk.gov.ch.model.officer.bankrupt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScottishBankruptOfficerSearchFiltersTest {

    private ScottishBankruptOfficerSearchFilters scottishBankruptOfficerSearchFiltersUnderTest;

    @BeforeEach
    void setUp() {
        scottishBankruptOfficerSearchFiltersUnderTest = new ScottishBankruptOfficerSearchFilters();
    }

    @Test
    void testAliasGetterAndSetter() {
        final String alias = "alias";
        scottishBankruptOfficerSearchFiltersUnderTest.setAlias(alias);
        assertThat(scottishBankruptOfficerSearchFiltersUnderTest.getAlias()).isEqualTo(alias);
    }

    @Test
    void testForename1GetterAndSetter() {
        final String forename1 = "forename1";
        scottishBankruptOfficerSearchFiltersUnderTest.setForename1(forename1);
        assertThat(scottishBankruptOfficerSearchFiltersUnderTest.getForename1()).isEqualTo(forename1);
    }

    @Test
    void testSurnameGetterAndSetter() {
        final String surname = "surname";
        scottishBankruptOfficerSearchFiltersUnderTest.setSurname(surname);
        assertThat(scottishBankruptOfficerSearchFiltersUnderTest.getSurname()).isEqualTo(surname);
    }

    @Test
    void testFromDateOfBirthGetterAndSetter() {
        final String fromDateOfBirth = "fromDateOfBirth";
        scottishBankruptOfficerSearchFiltersUnderTest.setFromDateOfBirth(fromDateOfBirth);
        assertThat(scottishBankruptOfficerSearchFiltersUnderTest.getFromDateOfBirth()).isEqualTo(fromDateOfBirth);
    }

    @Test
    void testToDateOfBirthGetterAndSetter() {
        final String toDateOfBirth = "toDateOfBirth";
        scottishBankruptOfficerSearchFiltersUnderTest.setToDateOfBirth(toDateOfBirth);
        assertThat(scottishBankruptOfficerSearchFiltersUnderTest.getToDateOfBirth()).isEqualTo(toDateOfBirth);
    }

    @Test
    void testPostcodeGetterAndSetter() {
        final String postcode = "postcode";
        scottishBankruptOfficerSearchFiltersUnderTest.setPostcode(postcode);
        assertThat(scottishBankruptOfficerSearchFiltersUnderTest.getPostcode()).isEqualTo(postcode);
    }
}
