package uk.gov.ch.service.transaction.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.model.transaction.jsondatamodels.Gaz2Transaction;
import uk.gov.ch.model.transaction.sqldatamodels.Gaz2TransactionDataModel;
import uk.gov.ch.repository.transaction.Gaz2TransactionRepository;
import uk.gov.ch.service.transaction.Gaz2RequestedService;
import uk.gov.ch.transformers.transaction.TransactionTransformer;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@Service
public class Gaz2RequestedServiceImpl implements Gaz2RequestedService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    private final Gaz2TransactionRepository gaz2TransactionRepository;
    private final TransactionTransformer transactionTransformer;

    @Autowired
    public Gaz2RequestedServiceImpl(Gaz2TransactionRepository gaz2TransactionRepository, TransactionTransformer transactionTransformer) {
        this.gaz2TransactionRepository = gaz2TransactionRepository;
        this.transactionTransformer = transactionTransformer;
    }

    @Override
    public Gaz2Transaction getRequestedGaz2(String companyNumber) {

        LOGGER.info("Calling repository to retrieve requested Gaz2 transaction for company number " + companyNumber);

        Gaz2TransactionDataModel gaz2TransactionDataModel = gaz2TransactionRepository.findRequestedGaz2(companyNumber);

        if (gaz2TransactionDataModel == null) {
            return null;
        }

        return transactionTransformer.convert(gaz2TransactionDataModel);
    }
}
