package uk.gov.ch.transformers.corporatebody;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import uk.gov.ch.model.corporatebody.sqldatamodels.AccountingDates;
import uk.gov.ch.model.corporatebody.sqldatamodels.AnnualReturnDates;
import uk.gov.ch.model.corporatebody.sqldatamodels.CompanyProfileModel;
import uk.gov.ch.model.corporatebody.sqldatamodels.ConfirmationStatementDates;
import uk.gov.ch.model.corporatebody.sqldatamodels.PreviousCompanyNames;
import uk.gov.ch.model.corporatebody.sqldatamodels.RegisteredOfficeAddress;
import uk.gov.ch.model.corporatebody.sqldatamodels.SicCodes;
import uk.gov.companieshouse.api.model.company.CompanyProfileApi;

@ExtendWith(MockitoExtension.class)
public class CorporateBodyTransformerTest {

    @InjectMocks
    private CorporateBodyTransformer transformer;

    private static final String LATE_DATE = "20210202";
    private static final String MID_DATE = "20201111";
    private static final String EARLY_DATE = "20200101";

    @Test
    @DisplayName("Convert fully populated CompanyProfileModel")
    void testConvert() {
        CompanyProfileModel model = setUpModel();
        CompanyProfileApi result = transformer.convert(model);
        assertEquals(model.getCompanyName(), result.getCompanyName());
        assertEquals(model.getCompanyNumber(), result.getCompanyNumber());
        assertEquals(CompanyStatusEnum.fromString(model.getStatus()).getDescription(), result.getCompanyStatus());
        assertEquals(getLocalDateFromString(model.getCreationDate()), result.getDateOfCreation());
        assertEquals(getLocalDateFromString(model.getDateOfDissolution()), result.getDateOfCessation());
        assertEquals(CorporateBodyTypeEnum.fromString(model.getType()).getDescription(), result.getType());
        assertTrue(result.isHasCharges());
        assertTrue(result.isCommunityInterestCompany());
        assertTrue(result.isHasInsolvencyHistory());
        assertTrue(result.isRegisteredOfficeIsInDispute());
        assertTrue(result.isUndeliverableRegisteredOfficeAddress());
        assertTrue(result.getAnnualReturn().isOverdue());

        assertAccounts(model, result);
        assertConfirmationStattment(model, result);
        assertAddress(model, result);
        assertPreviousNames(model, result);
        assertSicCodes(model, result);
    }

    @Test
    @DisplayName("Convert with null fields")
    void testConvertWithNullFields() {
        CompanyProfileModel model = setUpModel();
        model.setAccountingDates(null);
        model.setAnnualReturnDates(null);
        model.setConfirmationStatementDates(null);
        model.setRegisteredOfficeAddress(null);
        model.setSicCodes(null);
        model.setPreviousCompanyNames(null);
        model.setDateOfDissolution(null);
        model.setCreationDate(null);
        CompanyProfileApi result = transformer.convert(model);

        assertNotNull(result.getAccounts());
        assertAccountingReference(result);

        assertNull(result.getAnnualReturn());
        assertNull(result.getConfirmationStatement());

        assertNull(result.getRegisteredOfficeAddress());
        assertNull(result.getSicCodes());

        assertNull(result.getDateOfCessation());
        assertNull(result.getDateOfCreation());
        assertNull(result.getPreviousCompanyNames());
    }

    private void assertSicCodes(CompanyProfileModel model, CompanyProfileApi result) {
        assertEquals(5, result.getSicCodes().length);
        assertEquals(model.getSicCodes().get(0).getSic1(), result.getSicCodes()[0]);
        assertEquals(model.getSicCodes().get(0).getSic2(), result.getSicCodes()[1]);
        assertEquals(model.getSicCodes().get(0).getSic3(), result.getSicCodes()[2]);
        assertEquals(model.getSicCodes().get(0).getSic4(), result.getSicCodes()[3]);
        assertEquals(model.getSicCodes().get(0).getSic5(), result.getSicCodes()[4]);
    }

    private void assertPreviousNames(CompanyProfileModel model, CompanyProfileApi result) {
        assertEquals(model.getPreviousCompanyNames().size(), result.getPreviousCompanyNames().size());
        assertEquals(getLocalDateFromString(model.getPreviousCompanyNames().get(0).getCeasedOn()),
                result.getPreviousCompanyNames().get(0).getCeasedOn());
        assertEquals(getLocalDateFromString(model.getPreviousCompanyNames().get(0).getEffectiveFrom()),
                result.getPreviousCompanyNames().get(0).getEffectiveFrom());
        assertEquals(model.getPreviousCompanyNames().get(0).getName(),
                result.getPreviousCompanyNames().get(0).getName());
    }

    private void assertAccounts(CompanyProfileModel model, CompanyProfileApi result) {
        assertAccountingReference(result);

        assertEquals(getLocalDateFromString(model.getAccountingDates().getNextDue()),
                result.getAccounts().getNextDue());
        assertEquals(getLocalDateFromString(model.getAccountingDates().getNextPeriodEndOn()),
                result.getAccounts().getNextMadeUpTo());
        assertEquals(CompanyAccountTypeEnum.fromString(model.getAccountType()).getDescription(),
                result.getAccounts().getLastAccounts().getType());
        assertEquals(getLocalDateFromString(model.getAccountingDates().getLastPeriodStartOn()),
                result.getAccounts().getLastAccounts().getPeriodStartOn());
        assertEquals(getLocalDateFromString(model.getAccountingDates().getLastPeriodEndOn()),
                result.getAccounts().getLastAccounts().getPeriodEndOn());
        assertEquals(getLocalDateFromString(model.getAccountingDates().getNextPeriodStartOn()),
                result.getAccounts().getNextAccounts().getPeriodStartOn());
        assertEquals(getLocalDateFromString(model.getAccountingDates().getNextPeriodEndOn()),
                result.getAccounts().getNextAccounts().getPeriodEndOn());

    }

    private void assertAccountingReference(CompanyProfileApi result) {
        assertTrue(result.getAccounts().isOverdue());
        assertEquals("31", result.getAccounts().getAccountingReferenceDate().getDay());
        assertEquals("08", result.getAccounts().getAccountingReferenceDate().getMonth());
    }

    private void assertConfirmationStattment(CompanyProfileModel model, CompanyProfileApi result) {
        assertTrue(result.getConfirmationStatement().isOverdue());
        assertEquals(getLocalDateFromString(model.getConfirmationStatementDates().getNextDue()),
                result.getConfirmationStatement().getNextDue());
        assertEquals(getLocalDateFromString(model.getConfirmationStatementDates().getNextMadeUpTo()),
                result.getConfirmationStatement().getNextMadeUpTo());
    }

    private void assertAddress(CompanyProfileModel model, CompanyProfileApi result) {
        assertEquals(model.getRegisteredOfficeAddress().getAddressLine1(),
                result.getRegisteredOfficeAddress().getAddressLine1());
        assertEquals(model.getRegisteredOfficeAddress().getAddressLine2(),
                result.getRegisteredOfficeAddress().getAddressLine2());
        assertEquals(model.getRegisteredOfficeAddress().getCareOf(), result.getRegisteredOfficeAddress().getCareOf());
        assertEquals(model.getRegisteredOfficeAddress().getCountry(), result.getRegisteredOfficeAddress().getCountry());
        assertEquals(model.getRegisteredOfficeAddress().getLocality(),
                result.getRegisteredOfficeAddress().getLocality());
        assertEquals(model.getRegisteredOfficeAddress().getPoBox(), result.getRegisteredOfficeAddress().getPoBox());
        assertEquals(model.getRegisteredOfficeAddress().getPostalCode(),
                result.getRegisteredOfficeAddress().getPostalCode());
        assertEquals(model.getRegisteredOfficeAddress().getPremises(),
                result.getRegisteredOfficeAddress().getPremises());
        assertEquals(model.getRegisteredOfficeAddress().getRegion(), result.getRegisteredOfficeAddress().getRegion());
    }

    private LocalDate getLocalDateFromString(String dateString) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(dateString, dateTimeFormatter);
    }

    private CompanyProfileModel setUpModel() {
        CompanyProfileModel model = new CompanyProfileModel();
        model.setCompanyName("Company Name");
        model.setCompanyNumber("12345678");
        model.setAccountOverdue("1");
        model.setAccountType("2");
        model.setAccRefDate("0831");
        model.setAnnualReturnOverdue("1");
        model.setCicInd("1");
        model.setConfirmationStatementOverdue("1");
        model.setCreatedTime(EARLY_DATE);
        model.setDateOfDissolution(LATE_DATE);
        model.setCreationDate(EARLY_DATE);
        model.setHasAppointments("1");
        model.setHasInsolvencyHistory("1");
        model.setJurisdiction("1");
        model.setHasMortgages("1");
        model.setProofStatus("1");
        model.setRegisteredOfficeIsInDispute("1");
        model.setStatus("1");
        model.setSubtype("2");
        model.setSuperSecurePscInd("1");
        model.setType("2");
        model.setUndeliverableRegisteredOfficeAddress("1");

        AccountingDates accountingDates = new AccountingDates();
        accountingDates.setLastPeriodEndOn(MID_DATE);
        accountingDates.setLastPeriodStartOn(EARLY_DATE);
        accountingDates.setNextDue(LATE_DATE);
        accountingDates.setNextPeriodEndOn(LATE_DATE);
        accountingDates.setNextPeriodStartOn(MID_DATE);
        model.setAccountingDates(accountingDates);

        AnnualReturnDates annualReturnDates = new AnnualReturnDates();
        annualReturnDates.setLatestMadeUpTo(MID_DATE);
        model.setAnnualReturnDates(annualReturnDates);

        ConfirmationStatementDates csDates = new ConfirmationStatementDates();
        csDates.setNextDue(LATE_DATE);
        csDates.setNextMadeUpTo(LATE_DATE);
        model.setConfirmationStatementDates(csDates);

        PreviousCompanyNames previousName = new PreviousCompanyNames();
        previousName.setCeasedOn(MID_DATE);
        previousName.setEffectiveFrom(EARLY_DATE);
        previousName.setName("Previous Name");
        List<PreviousCompanyNames> previousNameList = new ArrayList<>();
        previousNameList.add(previousName);
        model.setPreviousCompanyNames(previousNameList);

        RegisteredOfficeAddress address = new RegisteredOfficeAddress();
        address.setAddressLine1("Address 1");
        address.setAddressLine2("Address 2");
        address.setCareOf("care of");
        address.setCountry("country");
        address.setLocality("locality");
        address.setPoBox("po box");
        address.setPostalCode("post code");
        address.setPremises("premises");
        address.setRegion("region");
        model.setRegisteredOfficeAddress(address);

        SicCodes sicCodes = new SicCodes();
        sicCodes.setSic1("sic 1");
        sicCodes.setSic2("sic 2");
        sicCodes.setSic3("sic 3");
        sicCodes.setSic4("sic 4");
        sicCodes.setSic5("sic 5");
        List<SicCodes> sicCodeList = new ArrayList<>();
        sicCodeList.add(sicCodes);
        model.setSicCodes(sicCodeList);
        return model;
    }

}
