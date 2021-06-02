package uk.gov.ch.service.corporatebody.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.repository.corporatebody.CorporateBodyRepository;
import uk.gov.ch.exception.CorporateBodyNotFoundException;
import uk.gov.ch.service.corporatebody.CorporateBodyService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@Service
public class CorporateBodyServiceImpl implements CorporateBodyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private CorporateBodyRepository corporateBodyRepository;

    @Override
    public Long getActionCode(String companyNumber) throws CorporateBodyNotFoundException {
        LOGGER.info("Calling DAO to retrieve action code for company number " + companyNumber);

        return corporateBodyRepository.getActionCode(companyNumber);
    }

    @Override
    public Long getTradedStatus(String companyNumber) throws CorporateBodyNotFoundException {
        LOGGER.info("Calling DAO to retrieve traded status for company number " + companyNumber);

        return corporateBodyRepository.getTradedStatus(companyNumber);    }
}
