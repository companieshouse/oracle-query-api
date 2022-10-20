package uk.gov.ch.service.officer.bankrupt.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerDetails;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerDataModel;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearch;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchFilters;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchResults;
import uk.gov.ch.repository.officers.ScottishBankruptOfficersRepository;
import uk.gov.ch.service.officer.bankrupt.BankruptOfficerService;
import uk.gov.ch.transformers.officer.bankrupt.BankruptOfficersTransformer;

import java.util.Optional;

@Service
public class ScottishBankruptOfficerServiceImpl implements BankruptOfficerService {

    @Autowired
    private ScottishBankruptOfficersRepository scottishBankruptOfficersRepository;

    @Autowired
    private BankruptOfficersTransformer bankruptOfficersTransformer;

    @Override
    public ScottishBankruptOfficerSearchResults getScottishBankruptOfficers(ScottishBankruptOfficerSearch search) {
        Pageable page =  PageRequest.of(search.getStartIndex(), search.getItemsPerPage());

        ScottishBankruptOfficerSearchFilters filters = search.getFilters();
        Page<ScottishBankruptOfficerDataModel> dataModel = scottishBankruptOfficersRepository.findScottishBankruptOfficers(
            filters.getForename1(), filters.getSurname(),  filters.getAlias(),filters.getFromDateOfBirth(), filters.getToDateOfBirth(),
                filters.getPostcode(), page);

        return bankruptOfficersTransformer.convertToSearchResults(dataModel);
    }

    public ScottishBankruptOfficerDetails getScottishBankruptOfficer(String ephemeralId){

       Optional<ScottishBankruptOfficerDataModel> officerModel = scottishBankruptOfficersRepository.findById(ephemeralId);

       if (!officerModel.isPresent()) {
           return null;
       }

       return bankruptOfficersTransformer.convertToDetails(officerModel.get());
    }


}
