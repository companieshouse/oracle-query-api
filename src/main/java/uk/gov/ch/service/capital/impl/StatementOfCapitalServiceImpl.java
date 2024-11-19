package uk.gov.ch.service.capital.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.ServiceException;
import uk.gov.ch.exception.StatementOfCapitalNotFoundException;
import uk.gov.ch.model.capital.StatementOfCapital;
import uk.gov.ch.repository.capital.StatementOfCapitalRepository;
import uk.gov.ch.service.capital.StatementOfCapitalService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@Service
public class StatementOfCapitalServiceImpl implements StatementOfCapitalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OracleQueryApplication.APPLICATION_NAME_SPACE);
    private static final String NOT_FOUND_MESSAGE = "Statement of capital result was returned but empty";

    @Autowired
    private StatementOfCapitalRepository statementOfCapitalRepository;

    @Override
    public StatementOfCapital getStatementOfCapital(String incorporationNumber)
            throws StatementOfCapitalNotFoundException, ServiceException {
        List<StatementOfCapital> statementOfCapitalList = statementOfCapitalRepository.getStatementOfCapital(
                incorporationNumber);
        int size = statementOfCapitalList.size();
        if (size == 1) {
            return statementOfCapitalList.get(0);
        } else {
            if (size != 0) {
                throw new ServiceException("Single result not returned");
            } else {
                LOGGER.error(NOT_FOUND_MESSAGE);
                throw new StatementOfCapitalNotFoundException(NOT_FOUND_MESSAGE);
            }
        }
    }
}
