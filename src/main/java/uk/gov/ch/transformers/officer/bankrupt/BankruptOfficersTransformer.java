package uk.gov.ch.transformers.officer.bankrupt;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchDataModel;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchResult;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchResults;

import java.util.ArrayList;
import java.util.List;

@Component
public class BankruptOfficersTransformer {

    public ScottishBankruptOfficerSearchResult convert(ScottishBankruptOfficerSearchDataModel scottishBankruptOfficerSearchDataModel) {
        ScottishBankruptOfficerSearchResult scottishBankruptOfficerResult = new ScottishBankruptOfficerSearchResult();

        scottishBankruptOfficerResult.setForename1(scottishBankruptOfficerSearchDataModel.getForename1());
        scottishBankruptOfficerResult.setForename2(scottishBankruptOfficerSearchDataModel.getForename2());
        scottishBankruptOfficerResult.setSurname(scottishBankruptOfficerSearchDataModel.getSurname());
        scottishBankruptOfficerResult.setDateOfBirth(scottishBankruptOfficerSearchDataModel.getDateOfBirth());
        scottishBankruptOfficerResult.setPostcode(scottishBankruptOfficerSearchDataModel.getPostcode());
        scottishBankruptOfficerResult.setAddressLine1(scottishBankruptOfficerSearchDataModel.getAddressLine1());
        scottishBankruptOfficerResult.setAddressLine2(scottishBankruptOfficerSearchDataModel.getAddressLine2());
        scottishBankruptOfficerResult.setAddressLine3(scottishBankruptOfficerSearchDataModel.getAddressLine3());
        scottishBankruptOfficerResult.setCounty(scottishBankruptOfficerSearchDataModel.getCounty());
        scottishBankruptOfficerResult.setTown(scottishBankruptOfficerSearchDataModel.getTown());
        scottishBankruptOfficerResult.setEphemeralKey(scottishBankruptOfficerSearchDataModel.getEphemeralKey());
        return scottishBankruptOfficerResult;
    }

    public ScottishBankruptOfficerSearchResults convert(Page<ScottishBankruptOfficerSearchDataModel> scottishBankruptOfficerSearchPage) {

        List<ScottishBankruptOfficerSearchResult> results = new ArrayList<>();

        for (ScottishBankruptOfficerSearchDataModel scottishBankruptOfficerSearchDataModel : scottishBankruptOfficerSearchPage.toList()) {
            results.add(convert(scottishBankruptOfficerSearchDataModel));
        }
        ScottishBankruptOfficerSearchResults scottishBankruptOfficerSearchResults = new ScottishBankruptOfficerSearchResults();
        scottishBankruptOfficerSearchResults.setItems(results);
        scottishBankruptOfficerSearchResults.setItemsPerPage(scottishBankruptOfficerSearchPage.getNumberOfElements());
        scottishBankruptOfficerSearchResults.setStartIndex(scottishBankruptOfficerSearchPage.getNumber());
        scottishBankruptOfficerSearchResults.setTotalResults(scottishBankruptOfficerSearchPage.getTotalElements());

        return scottishBankruptOfficerSearchResults;

    }
}
