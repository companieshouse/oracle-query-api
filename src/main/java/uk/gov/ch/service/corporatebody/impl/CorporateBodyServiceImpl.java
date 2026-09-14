package uk.gov.ch.service.corporatebody.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.CompanyProfileMappingException;
import uk.gov.ch.exception.CorporateBodyDetailsEmailAddressNotFoundException;
import uk.gov.ch.exception.CorporateBodyNotFoundException;
import uk.gov.ch.model.corporatebody.sqldatamodels.CompanyProfileModel;
import uk.gov.ch.model.corporatebody.sqldatamodels.CorporateBodyDetails;
import uk.gov.ch.model.corporatebody.sqldatamodels.RegisteredEmailAddressJson;
import uk.gov.ch.repository.corporatebody.CorporateBodyDetailsRepository;
import uk.gov.ch.repository.corporatebody.CorporateBodyRepository;
import uk.gov.ch.service.corporatebody.CorporateBodyService;
import uk.gov.ch.transformers.corporatebody.CorporateBodyTransformer;
import uk.gov.companieshouse.api.model.company.CompanyProfileApi;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;
import uk.gov.companieshouse.logging.util.DataMap;

import java.util.HashMap;
import java.util.Map;

@Service
public class CorporateBodyServiceImpl implements CorporateBodyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OracleQueryApplication.APPLICATION_NAME_SPACE);
    private static final String NOT_FOUND_MESSAGE = "Email address not found for company: ";
    private final CorporateBodyRepository corporateBodyRepository;
    private final JsonMapper jsonMapper;
    private final CorporateBodyTransformer corporateBodyTransformer;
    private final CorporateBodyDetailsRepository corporateBodyDetailsRepository;

    public CorporateBodyServiceImpl(CorporateBodyRepository corporateBodyRepository, JsonMapper jsonMapper, CorporateBodyTransformer corporateBodyTransformer, CorporateBodyDetailsRepository corporateBodyDetailsRepository) {
        this.corporateBodyRepository = corporateBodyRepository;
        this.jsonMapper = jsonMapper;
        this.corporateBodyTransformer = corporateBodyTransformer;
        this.corporateBodyDetailsRepository = corporateBodyDetailsRepository;
    }

    @Override
    public Long getActionCode(String companyNumber) throws CorporateBodyNotFoundException {
        LOGGER.info("Calling DAO to retrieve action code for company number " + companyNumber);

        return corporateBodyRepository.getActionCode(companyNumber);
    }

    @Override
    public Long getTradedStatus(String companyNumber) throws CorporateBodyNotFoundException {
        LOGGER.info("Calling DAO to retrieve traded status for company number " + companyNumber);

        return corporateBodyRepository.getTradedStatus(companyNumber);
    }

    @Override
    public CompanyProfileApi getCompanyProfile(String companyNumber)
            throws CorporateBodyNotFoundException, CompanyProfileMappingException {
        Map<String, Object> debugMap = new HashMap<>();
        debugMap.put("company_number", companyNumber);
        LOGGER.info("Calling DAO to retrieve company profile", debugMap);

        String resultJson = corporateBodyRepository.getCompanyProfile(companyNumber);
        if (resultJson.contains("Company Not Found")) {
            throw new CorporateBodyNotFoundException(companyNumber);
        }

        try {
            JsonNode companyProfileNode = jsonMapper.readValue(resultJson, JsonNode.class);
            CompanyProfileModel companyProfileModel = jsonMapper.convertValue(companyProfileNode, new TypeReference<>() {});
            return corporateBodyTransformer.convert(companyProfileModel);
        } catch (JacksonException e) {
            throw new CompanyProfileMappingException(
                    "Json Processing exception for " + companyNumber);
        }
    }

    @Override
    public RegisteredEmailAddressJson getRegisteredEmailAddress(String companyNumber)
            throws CorporateBodyDetailsEmailAddressNotFoundException {

        DataMap dataMap = new DataMap.Builder().companyNumber(companyNumber).build();

        LOGGER.infoContext(companyNumber,
                "Calling database to retrieve registered email address for company "
                        + companyNumber, dataMap.getLogMap());

        RegisteredEmailAddressJson registeredEmailAddressJson = new RegisteredEmailAddressJson();
        CorporateBodyDetails emailAddress = corporateBodyDetailsRepository.getEmailAddress(
                companyNumber);

        if (emailAddress == null || StringUtils.isBlank(emailAddress.getEmailAddress())) {
            LOGGER.errorContext(companyNumber, "No email address found for company", null,
                    dataMap.getLogMap());
            throw new CorporateBodyDetailsEmailAddressNotFoundException(
                    NOT_FOUND_MESSAGE + companyNumber);
        }

        registeredEmailAddressJson.setRegisteredEmailAddress(emailAddress.getEmailAddress());

        return registeredEmailAddressJson;
    }
}
