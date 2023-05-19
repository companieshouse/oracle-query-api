package uk.gov.ch.service.update.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.CorporateBodyDetailsEmailAddressNotFoundException;
import uk.gov.ch.model.corporatebody.sqldatamodels.CorporateBodyDetailsEmailAddress;
import uk.gov.ch.model.update.OverseasEntityDataJson;
import uk.gov.ch.repository.corporatebody.CorporateBodyDetailsEmailAddressRepository;
import uk.gov.ch.service.update.OverseasEntityDataService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;
import uk.gov.companieshouse.logging.util.DataMap;

@Service
public class OverseasOverseasEntityDataServiceImpl implements OverseasEntityDataService {

    @Autowired
    private CorporateBodyDetailsEmailAddressRepository corporateBodyDetailsEmailAddressRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    private static final String NOT_FOUND_MESSAGE = "Contact email not found for overseas entity: ";

    @Override
    public OverseasEntityDataJson getEntityEmail(String companyNumber) throws CorporateBodyDetailsEmailAddressNotFoundException {

        DataMap dataMap = new DataMap.Builder().companyNumber(companyNumber).build();

        LOGGER.infoContext(companyNumber, "Calling database during Update process to retrieve overseas entity email address for company " + companyNumber, dataMap.getLogMap());

        OverseasEntityDataJson overseasEntityDataJson = new OverseasEntityDataJson();
        CorporateBodyDetailsEmailAddress corporateBodyDetailsEmailAddress = corporateBodyDetailsEmailAddressRepository.getEmailAddress(companyNumber);

        if (corporateBodyDetailsEmailAddress == null || StringUtils.isBlank(corporateBodyDetailsEmailAddress.getEmailAddress())) {
            LOGGER.errorContext(companyNumber, "No email address found for overseas entity", null, dataMap.getLogMap());
            throw new CorporateBodyDetailsEmailAddressNotFoundException(NOT_FOUND_MESSAGE + companyNumber);
        }

        overseasEntityDataJson.setEmailAddress(corporateBodyDetailsEmailAddress.getEmailAddress());

        return overseasEntityDataJson;
    }
}
