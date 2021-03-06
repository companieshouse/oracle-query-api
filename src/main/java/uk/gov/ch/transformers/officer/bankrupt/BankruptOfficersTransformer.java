package uk.gov.ch.transformers.officer.bankrupt;

import java.time.LocalDate;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Component;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerDetails;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerDataModel;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchResult;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchResults;

import java.util.ArrayList;
import java.util.List;

@Component
public class BankruptOfficersTransformer {

    private static final LocalDate END_OF_TIME = LocalDate.of(9999, 12, 31);

    public ScottishBankruptOfficerSearchResults convertToSearchResults(Page<ScottishBankruptOfficerDataModel> scottishBankruptOfficerSearchPage) {

        List<ScottishBankruptOfficerSearchResult> results = new ArrayList<>();

        for (ScottishBankruptOfficerDataModel scottishBankruptOfficerSearchDataModel : scottishBankruptOfficerSearchPage.toList()) {
            results.add(convertToSearchResult(scottishBankruptOfficerSearchDataModel));
        }
        ScottishBankruptOfficerSearchResults scottishBankruptOfficerSearchResults = new ScottishBankruptOfficerSearchResults();
        scottishBankruptOfficerSearchResults.setItems(results);
        scottishBankruptOfficerSearchResults.setItemsPerPage(scottishBankruptOfficerSearchPage.getNumberOfElements());
        scottishBankruptOfficerSearchResults.setStartIndex(scottishBankruptOfficerSearchPage.getNumber());
        scottishBankruptOfficerSearchResults.setTotalResults(scottishBankruptOfficerSearchPage.getTotalElements());

        return scottishBankruptOfficerSearchResults;

    }

    public ScottishBankruptOfficerDetails convertToDetails(ScottishBankruptOfficerDataModel scottishBankruptOfficerDetailsDataModel) {
        ScottishBankruptOfficerDetails details = new ScottishBankruptOfficerDetails();

        details.setForename1(scottishBankruptOfficerDetailsDataModel.getForename1());
        details.setForename2(scottishBankruptOfficerDetailsDataModel.getForename2());
        details.setSurname(scottishBankruptOfficerDetailsDataModel.getSurname());
        details.setDateOfBirth(scottishBankruptOfficerDetailsDataModel.getDateOfBirth());
        details.setPostcode(scottishBankruptOfficerDetailsDataModel.getAddressPostcode());
        details.setAddressLine1(scottishBankruptOfficerDetailsDataModel.getAddressLine1());
        details.setAddressLine2(scottishBankruptOfficerDetailsDataModel.getAddressLine2());
        details.setAddressLine3(scottishBankruptOfficerDetailsDataModel.getAddressLine3());
        details.setCounty(scottishBankruptOfficerDetailsDataModel.getAddressCounty());
        details.setTown(scottishBankruptOfficerDetailsDataModel.getAddressTown());
        details.setAlias(scottishBankruptOfficerDetailsDataModel.getAlias());
        details.setCaseReference(scottishBankruptOfficerDetailsDataModel.getCaseReference());
        details.setCaseType(scottishBankruptOfficerDetailsDataModel.getCaseType());
        details.setBankruptcyType(scottishBankruptOfficerDetailsDataModel.getBankruptcyType());
        details.setStartDate(scottishBankruptOfficerDetailsDataModel.getStartDate());
        details.setTrusteeDischargeDate(scottishBankruptOfficerDetailsDataModel.getTrusteeDischargeDate());
        details.setEphemeralKey(scottishBankruptOfficerDetailsDataModel.getEphemeralKey());

        LocalDate debtorDischargeDate = scottishBankruptOfficerDetailsDataModel.getDebtorDischargeDate();

        if (debtorDischargeDate != null && !END_OF_TIME.isEqual(debtorDischargeDate)) {
            details.setDebtorDischargeDate(debtorDischargeDate);
        }

        return details;
    }
    public ScottishBankruptOfficerSearchResult convertToSearchResult(ScottishBankruptOfficerDataModel scottishBankruptOfficerDetailsDataModel) {
        ScottishBankruptOfficerSearchResult details = new ScottishBankruptOfficerSearchResult();

        details.setEphemeralKey(scottishBankruptOfficerDetailsDataModel.getEphemeralKey());
        details.setForename1(scottishBankruptOfficerDetailsDataModel.getForename1());
        details.setForename2(scottishBankruptOfficerDetailsDataModel.getForename2());
        details.setSurname(scottishBankruptOfficerDetailsDataModel.getSurname());
        details.setDateOfBirth(scottishBankruptOfficerDetailsDataModel.getDateOfBirth());
        details.setPostcode(scottishBankruptOfficerDetailsDataModel.getAddressPostcode());
        details.setAddressLine1(scottishBankruptOfficerDetailsDataModel.getAddressLine1());
        details.setAddressLine2(scottishBankruptOfficerDetailsDataModel.getAddressLine2());
        details.setAddressLine3(scottishBankruptOfficerDetailsDataModel.getAddressLine3());
        details.setCounty(scottishBankruptOfficerDetailsDataModel.getAddressCounty());
        details.setTown(scottishBankruptOfficerDetailsDataModel.getAddressTown());

        return details;
    }

}
