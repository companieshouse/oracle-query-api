package uk.gov.ch.corporatebody.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.corporatebody.dao.CorporateBodyDao;
import uk.gov.ch.corporatebody.exception.CorporateBodyNotFoundException;
import uk.gov.ch.corporatebody.service.CorporateBodyService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@Service
public class CorporateBodyServiceImpl implements CorporateBodyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private CorporateBodyDao corporateBodyDao;


    @Override
    public int getActionCode(String companyNumber) throws CorporateBodyNotFoundException {
        LOGGER.info("Calling DAO to retrieve action code for company number " + companyNumber);

        return corporateBodyDao.getActionCode(companyNumber);
    }
}
