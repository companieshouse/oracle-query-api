package uk.gov.ch.service.corporatebody.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.RegisteredEmailAddressNotFoundException;
import uk.gov.ch.model.corporatebody.sqldatamodels.RegisteredEmailAddress;
import uk.gov.ch.model.corporatebody.sqldatamodels.RegisteredEmailAddressJson;
import uk.gov.ch.repository.corporatebody.RegisteredEmailAddressRepository;
import uk.gov.ch.service.corporatebody.RegisteredEmailAddressService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;
import uk.gov.companieshouse.logging.util.DataMap;

@Service
public class RegisteredEmailAddressServiceImpl implements RegisteredEmailAddressService {

    @Autowired
    private RegisteredEmailAddressRepository registeredEmailAddressRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    private static final String NOT_FOUND_MESSAGE = "Contact email not found for overseas entity: ";

    @Override
    public RegisteredEmailAddressJson getRegisteredEmailAddress(String companyNumber) throws RegisteredEmailAddressNotFoundException {

        DataMap dataMap = new DataMap.Builder().companyNumber(companyNumber).build();

        LOGGER.infoContext(companyNumber, "Calling database during Update process to retrieve registered email address for company " + companyNumber, dataMap.getLogMap());

        RegisteredEmailAddressJson registeredEmailAddressJson = new RegisteredEmailAddressJson();
        RegisteredEmailAddress registeredEmailAddress = registeredEmailAddressRepository.getRegisteredEmailAddress(companyNumber);

        if (registeredEmailAddress == null || StringUtils.isBlank(registeredEmailAddress.getRegisteredEmailAddress())) {
            LOGGER.errorContext(companyNumber, "No email address found for overseas entity", null, dataMap.getLogMap());
            throw new RegisteredEmailAddressNotFoundException(NOT_FOUND_MESSAGE + companyNumber);
        }

        registeredEmailAddressJson.setRegisteredEmailAddress(registeredEmailAddress.getRegisteredEmailAddress());

        return registeredEmailAddressJson;
    }
}
