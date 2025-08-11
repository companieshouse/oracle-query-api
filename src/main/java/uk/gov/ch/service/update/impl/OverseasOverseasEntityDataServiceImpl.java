package uk.gov.ch.service.update.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.CorporateBodyDetailsEmailAddressNotFoundException;
import uk.gov.ch.model.corporatebody.sqldatamodels.CorporateBodyDetails;
import uk.gov.ch.model.update.OverseasEntityDataJson;
import uk.gov.ch.repository.corporatebody.CorporateBodyDetailsRepository;
import uk.gov.ch.service.update.OverseasEntityDataService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;
import uk.gov.companieshouse.logging.util.DataMap;

@Service
public class OverseasOverseasEntityDataServiceImpl implements OverseasEntityDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OracleQueryApplication.APPLICATION_NAME_SPACE);
    private static final String NOT_FOUND_MESSAGE = "Contact email not found for overseas entity: ";
    @Autowired
    private CorporateBodyDetailsRepository corporateBodyDetailsRepository;

    @Override
    public OverseasEntityDataJson getEntityEmail(String companyNumber)
            throws CorporateBodyDetailsEmailAddressNotFoundException {

        DataMap dataMap = new DataMap.Builder().companyNumber(companyNumber).build();

        LOGGER.infoContext(companyNumber,
                "Calling database to retrieve overseas entity email address for company "
                        + companyNumber, dataMap.getLogMap());

        OverseasEntityDataJson overseasEntityDataJson = new OverseasEntityDataJson();
        CorporateBodyDetails corporateBodyDetails = corporateBodyDetailsRepository.getEmailAddress(
                companyNumber);

        if (corporateBodyDetails == null || StringUtils.isBlank(
                corporateBodyDetails.getEmailAddress())) {
            LOGGER.errorContext(companyNumber, "No email address found for overseas entity", null,
                    dataMap.getLogMap());
            throw new CorporateBodyDetailsEmailAddressNotFoundException(
                    NOT_FOUND_MESSAGE + companyNumber);
        }

        overseasEntityDataJson.setEmailAddress(corporateBodyDetails.getEmailAddress());

        return overseasEntityDataJson;
    }
}
