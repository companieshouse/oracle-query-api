package uk.gov.ch.model.emergencyauthcode.sqldatamodels;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class OfficerDetailDataModelTest {

    private OfficerDetailDataModel officerDetailDataModelUnderTest;

    @BeforeEach
    void setUp() {
        officerDetailDataModelUnderTest = new OfficerDetailDataModel();
    }

    @Test
    void testOfficerIdGetterAndSetter() {
        final Long officerId = 0L;
        officerDetailDataModelUnderTest.setOfficerId(officerId);
        assertThat(officerDetailDataModelUnderTest.getOfficerId()).isEqualTo(officerId);
    }

    @Test
    void testDateOfBirthGetterAndSetter() {
        final LocalDate dateOfBirth = LocalDate.of(2020, 1, 1);
        officerDetailDataModelUnderTest.setDateOfBirth(dateOfBirth);
        assertThat(officerDetailDataModelUnderTest.getDateOfBirth()).isEqualTo(dateOfBirth);
    }

    @Test
    void testOfficerNationalityGetterAndSetter() {
        final String officerNationality = "officerNationality";
        officerDetailDataModelUnderTest.setOfficerNationality(officerNationality);
        assertThat(officerDetailDataModelUnderTest.getOfficerNationality()).isEqualTo(officerNationality);
    }

    @Test
    void testUsualResidentialCountryGetterAndSetter() {
        final String usualResidentialCountry = "usualResidentialCountry";
        officerDetailDataModelUnderTest.setUsualResidentialCountry(usualResidentialCountry);
        assertThat(officerDetailDataModelUnderTest.getUsualResidentialCountry()).isEqualTo(usualResidentialCountry);
    }

    @Test
    void testUsualResidentialAddressGetterAndSetter() {
        final UsualResidentialAddressDataModel usualResidentialAddress = new UsualResidentialAddressDataModel();
        officerDetailDataModelUnderTest.setUsualResidentialAddress(usualResidentialAddress);
        assertThat(officerDetailDataModelUnderTest.getUsualResidentialAddress()).isEqualTo(usualResidentialAddress);
    }
}
