package uk.gov.ch.service.officer.bankrupt.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearch;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchDataModel;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchResults;
import uk.gov.ch.repository.officers.ScottishBankruptOfficersRepository;
import uk.gov.ch.service.officer.bankrupt.BankruptOfficerService;
import uk.gov.ch.transformers.officer.bankrupt.BankruptOfficersTransformer;

@Service
public class ScottishBankruptOfficerService implements BankruptOfficerService {

    @Autowired
    private ScottishBankruptOfficersRepository scottishBankruptOfficersRepository;

    @Autowired
    private BankruptOfficersTransformer bankruptOfficersTransformer;

    @Override
    public ScottishBankruptOfficerSearchResults getScottishBankruptOfficers(ScottishBankruptOfficerSearch search) {
        Pageable page =  PageRequest.of(0,10);
        Page<ScottishBankruptOfficerSearchDataModel> dataModel = scottishBankruptOfficersRepository.findScottishBankruptOfficers(
             search.getForename1(), search.getSurname(), search.getDateOfBirth(), search.getPostcode(), page);

        return bankruptOfficersTransformer.convert(dataModel);
    }

}
