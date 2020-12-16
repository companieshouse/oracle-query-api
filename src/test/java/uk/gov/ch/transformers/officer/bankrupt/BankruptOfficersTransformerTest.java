package uk.gov.ch.transformers.officer.bankrupt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchDataModel;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchResult;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchResults;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BankruptOfficersTransformerTest {


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
    private static final LocalDate DATE_OF_BIRTH = LocalDate.now();


    @BeforeEach
    public void setUp() {
        transformer = new BankruptOfficersTransformer();

    }

    @Test
    @DisplayName("Convert officer")
    public void testConvertOfficer() {
        ScottishBankruptOfficerSearchDataModel newOfficer = createOfficer();
        ScottishBankruptOfficerSearchResult convertedOfficer = transformer.convert(newOfficer);
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
    @DisplayName("Convert multiple officers")
    public void testConvertMultipleOfficers() {
        List<ScottishBankruptOfficerSearchDataModel> dataModelList = new ArrayList<>();

        dataModelList.add(createOfficer());
        dataModelList.add(createOfficer());
        dataModelList.add(createOfficer());
        dataModelList.add(createOfficer());
        dataModelList.add(createOfficer());
        Page<ScottishBankruptOfficerSearchDataModel> page = new PageImpl<ScottishBankruptOfficerSearchDataModel>(dataModelList);
        ScottishBankruptOfficerSearchResults convertedPage = transformer.convert(page);
        assertEquals(5, convertedPage.getItems().size());
        assertEquals(5, convertedPage.getTotalResults());
        assertEquals(0, convertedPage.getStartIndex());
        assertEquals(5, convertedPage.getItemsPerPage());
    }

    private ScottishBankruptOfficerSearchDataModel createOfficer() {

        ScottishBankruptOfficerSearchDataModel dataModel = new ScottishBankruptOfficerSearchDataModel();

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

        return dataModel;


    }

}
