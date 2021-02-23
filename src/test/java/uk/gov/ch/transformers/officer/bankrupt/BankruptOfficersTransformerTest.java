package uk.gov.ch.transformers.officer.bankrupt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerDetails;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerDataModel;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchResult;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchResults;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

 class BankruptOfficersTransformerTest {


    private BankruptOfficersTransformer transformer;

    private static final String EPHEMERAL_KEY = "key";
    private static final String FORENAME1 = "forename";
    private static final String FORENAME2 = "forename2";
    private static final String SURNAME = "surname";
    private static final String ADDRESS_LINE1 = "address line 1";
    private static final String ADDRESS_LINE2 = "address line 2";
    private static final String ADDRESS_LINE3 = "address line 3";
    private static final String ADDRESS_TOWN = "address town";
    private static final String ADDRESS_COUNTY = "address county";
    private static final String ADDRESS_POSTCODE = "address postcode";
    private static final String ALIAS = "alias";
    private static final String CASE_REFERENCE = "case reference";
    private static final String CASE_TYPE = "case type";
    private static final String BANKRUPTCY_TYPE = "bankruptcy type";
    private static final LocalDate START_DATE = LocalDate.now();
    private static final LocalDate DEBTOR_DISCHARGE = LocalDate.now();
    private static final LocalDate TRUSTEE_DISCHARGE_DATE = LocalDate.now();
    private static final LocalDate DATE_OF_BIRTH = LocalDate.now();
    private static final LocalDate DEBTOR_DISCHARGE_END_OF_TIME = LocalDate.of(9999, 12, 31);


    @BeforeEach
    public void setUp() {
        transformer = new BankruptOfficersTransformer();

    }

    @Test
    @DisplayName("Convert officer result")
    void testConvertOfficerResult() {
        ScottishBankruptOfficerDataModel newOfficer = createOfficer(DEBTOR_DISCHARGE);
        ScottishBankruptOfficerSearchResult convertedOfficer = transformer.convertToSearchResult(newOfficer);

        assertEquals(EPHEMERAL_KEY, convertedOfficer.getEphemeralKey());
        assertEquals(FORENAME1, convertedOfficer.getForename1());
        assertEquals(FORENAME2, convertedOfficer.getForename2());
        assertEquals(SURNAME, convertedOfficer.getSurname());
        assertEquals(ADDRESS_LINE1, convertedOfficer.getAddressLine1());
        assertEquals(ADDRESS_LINE2, convertedOfficer.getAddressLine2());
        assertEquals(ADDRESS_LINE3, convertedOfficer.getAddressLine3());
        assertEquals(ADDRESS_TOWN, convertedOfficer.getTown());
        assertEquals(ADDRESS_COUNTY, convertedOfficer.getCounty());
        assertEquals(ADDRESS_POSTCODE, convertedOfficer.getPostcode());
        assertEquals(DATE_OF_BIRTH, convertedOfficer.getDateOfBirth());

    }

    @Test
    @DisplayName("Convert officer details")
    void testConvertOfficerDetails() {
        ScottishBankruptOfficerDataModel newOfficer = createOfficer(DEBTOR_DISCHARGE);
        ScottishBankruptOfficerDetails convertedOfficer = transformer.convertToDetails(newOfficer);
        assertEquals(EPHEMERAL_KEY, convertedOfficer.getEphemeralKey());
        assertEquals(FORENAME1, convertedOfficer.getForename1());
        assertEquals(FORENAME2, convertedOfficer.getForename2());
        assertEquals(SURNAME, convertedOfficer.getSurname());
        assertEquals(ADDRESS_LINE1, convertedOfficer.getAddressLine1());
        assertEquals(ADDRESS_LINE2, convertedOfficer.getAddressLine2());
        assertEquals(ADDRESS_LINE3, convertedOfficer.getAddressLine3());
        assertEquals(ADDRESS_TOWN, convertedOfficer.getTown());
        assertEquals(ADDRESS_COUNTY, convertedOfficer.getCounty());
        assertEquals(ADDRESS_POSTCODE, convertedOfficer.getPostcode());
        assertEquals(DATE_OF_BIRTH, convertedOfficer.getDateOfBirth());
        assertEquals(ALIAS, convertedOfficer.getAlias());
        assertEquals(CASE_REFERENCE, convertedOfficer.getCaseReference());
        assertEquals(CASE_TYPE, convertedOfficer.getCaseType());
        assertEquals(BANKRUPTCY_TYPE, convertedOfficer.getBankruptcyType());
        assertEquals(START_DATE, convertedOfficer.getStartDate());
        assertEquals(DEBTOR_DISCHARGE, convertedOfficer.getDebtorDischargeDate());
        assertEquals(TRUSTEE_DISCHARGE_DATE, convertedOfficer.getTrusteeDischargeDate());

    }



    @Test
    @DisplayName("Convert multiple officers")
    void testConvertMultipleOfficers() {
        List<ScottishBankruptOfficerDataModel> dataModelList = new ArrayList<>();

        dataModelList.add(createOfficer(DEBTOR_DISCHARGE));
        dataModelList.add(createOfficer(DEBTOR_DISCHARGE));
        dataModelList.add(createOfficer(DEBTOR_DISCHARGE));
        dataModelList.add(createOfficer(DEBTOR_DISCHARGE));
        dataModelList.add(createOfficer(DEBTOR_DISCHARGE));
        Page<ScottishBankruptOfficerDataModel> page = new PageImpl<ScottishBankruptOfficerDataModel>(dataModelList);
        ScottishBankruptOfficerSearchResults convertedPage = transformer.convertToSearchResults(page);
        assertEquals(5, convertedPage.getItems().size());
        assertEquals(5, convertedPage.getTotalResults());
        assertEquals(0, convertedPage.getStartIndex());
        assertEquals(5, convertedPage.getItemsPerPage());
    }

     @Test
     @DisplayName("Convert officer details with a null debtor discharge date")
     void testConvertOfficerDetailsWithANullDebtorDischargeDate() {
         ScottishBankruptOfficerDataModel newOfficer = createOfficer(null);
         ScottishBankruptOfficerDetails convertedOfficer = transformer.convertToDetails(newOfficer);
         assertEquals(EPHEMERAL_KEY, convertedOfficer.getEphemeralKey());
         assertEquals(FORENAME1, convertedOfficer.getForename1());
         assertEquals(FORENAME2, convertedOfficer.getForename2());
         assertEquals(SURNAME, convertedOfficer.getSurname());
         assertEquals(ADDRESS_LINE1, convertedOfficer.getAddressLine1());
         assertEquals(ADDRESS_LINE2, convertedOfficer.getAddressLine2());
         assertEquals(ADDRESS_LINE3, convertedOfficer.getAddressLine3());
         assertEquals(ADDRESS_TOWN, convertedOfficer.getTown());
         assertEquals(ADDRESS_COUNTY, convertedOfficer.getCounty());
         assertEquals(ADDRESS_POSTCODE, convertedOfficer.getPostcode());
         assertEquals(DATE_OF_BIRTH, convertedOfficer.getDateOfBirth());
         assertEquals(ALIAS, convertedOfficer.getAlias());
         assertEquals(CASE_REFERENCE, convertedOfficer.getCaseReference());
         assertEquals(CASE_TYPE, convertedOfficer.getCaseType());
         assertEquals(BANKRUPTCY_TYPE, convertedOfficer.getBankruptcyType());
         assertEquals(START_DATE, convertedOfficer.getStartDate());
         assertNull(convertedOfficer.getDebtorDischargeDate());
         assertEquals(TRUSTEE_DISCHARGE_DATE, convertedOfficer.getTrusteeDischargeDate());
     }
     @Test
     @DisplayName("Convert officer details with end of time debtor discharge date")
     void testConvertOfficerDetailsWithEndOfTimeDebtorDischargeDate() {
         ScottishBankruptOfficerDataModel newOfficer = createOfficer(DEBTOR_DISCHARGE_END_OF_TIME);
         ScottishBankruptOfficerDetails convertedOfficer = transformer.convertToDetails(newOfficer);
         assertEquals(EPHEMERAL_KEY, convertedOfficer.getEphemeralKey());
         assertEquals(FORENAME1, convertedOfficer.getForename1());
         assertEquals(FORENAME2, convertedOfficer.getForename2());
         assertEquals(SURNAME, convertedOfficer.getSurname());
         assertEquals(ADDRESS_LINE1, convertedOfficer.getAddressLine1());
         assertEquals(ADDRESS_LINE2, convertedOfficer.getAddressLine2());
         assertEquals(ADDRESS_LINE3, convertedOfficer.getAddressLine3());
         assertEquals(ADDRESS_TOWN, convertedOfficer.getTown());
         assertEquals(ADDRESS_COUNTY, convertedOfficer.getCounty());
         assertEquals(ADDRESS_POSTCODE, convertedOfficer.getPostcode());
         assertEquals(DATE_OF_BIRTH, convertedOfficer.getDateOfBirth());
         assertEquals(ALIAS, convertedOfficer.getAlias());
         assertEquals(CASE_REFERENCE, convertedOfficer.getCaseReference());
         assertEquals(CASE_TYPE, convertedOfficer.getCaseType());
         assertEquals(BANKRUPTCY_TYPE, convertedOfficer.getBankruptcyType());
         assertEquals(START_DATE, convertedOfficer.getStartDate());
         assertNull(convertedOfficer.getDebtorDischargeDate());
         assertEquals(TRUSTEE_DISCHARGE_DATE, convertedOfficer.getTrusteeDischargeDate());
     }
    

    private ScottishBankruptOfficerDataModel createOfficer(LocalDate debtorDischargeDate) {

        ScottishBankruptOfficerDataModel dataModel = new ScottishBankruptOfficerDataModel();

        dataModel.setEphemeralKey(EPHEMERAL_KEY);
        dataModel.setForename1(FORENAME1);
        dataModel.setForename2(FORENAME2);
        dataModel.setSurname(SURNAME);
        dataModel.setAddressLine1(ADDRESS_LINE1);
        dataModel.setAddressLine2(ADDRESS_LINE2);
        dataModel.setAddressLine3(ADDRESS_LINE3);
        dataModel.setAddressTown(ADDRESS_TOWN);
        dataModel.setAddressCounty(ADDRESS_COUNTY);
        dataModel.setAddressPostcode(ADDRESS_POSTCODE);
        dataModel.setDateOfBirth(DATE_OF_BIRTH);
        dataModel.setAlias(ALIAS);
        dataModel.setCaseReference(CASE_REFERENCE);
        dataModel.setCaseType(CASE_TYPE);
        dataModel.setBankruptcyType(BANKRUPTCY_TYPE);
        dataModel.setStartDate(START_DATE);
        dataModel.setDebtorDischargeDate(debtorDischargeDate);
        dataModel.setTrusteeDischargeDate(TRUSTEE_DISCHARGE_DATE);

        return dataModel;

    }

}
