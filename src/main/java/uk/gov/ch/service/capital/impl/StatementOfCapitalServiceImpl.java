package uk.gov.ch.service.capital.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.StatementOfCapitalNotFoundException;
import uk.gov.ch.model.capital.StatementOfCapital;
import uk.gov.ch.repository.capital.StatementOfCapitalRepository;
import uk.gov.ch.service.capital.StatementOfCapitalService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

import java.util.List;

@Service
public class StatementOfCapitalServiceImpl implements StatementOfCapitalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private StatementOfCapitalRepository statementOfCapitalRepository;

    @Override
    public StatementOfCapital getStatementOfCapital(String incorporationNumber) throws StatementOfCapitalNotFoundException {
        List<StatementOfCapital> statementOfCapitalList = statementOfCapitalRepository.getStatementOfCapital(incorporationNumber);
        int size = statementOfCapitalList.size();
        if (size == 1) {
          return statementOfCapitalList.get(0);
        } else {
           LOGGER.error("Single result not found when getting statement of capital");
           throw new StatementOfCapitalNotFoundException("Statement of capital results " + size);
        }
    }
}
