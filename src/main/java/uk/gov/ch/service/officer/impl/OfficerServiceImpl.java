package uk.gov.ch.service.officer.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.NoOfficersExistingException;
import uk.gov.ch.exception.OfficersMappingException;
import uk.gov.ch.model.officer.OfficerDataModel;
import uk.gov.ch.repository.officers.OfficersRepository;
import uk.gov.ch.service.officer.OfficerService;
import uk.gov.ch.transformers.officer.OfficersApiTransformer;
import uk.gov.companieshouse.api.model.officers.OfficersApi;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@Service
public class OfficerServiceImpl implements OfficerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);
    private static final String COMPANY_NOT_FOUND = "Company Not Found";
    private static final String NO_OFFICER_STRING = "], \"CreatedTime\":";

    @Autowired
    OfficersRepository officersRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OfficersApiTransformer transformer;

    @Override
    public OfficersApi getOfficers(String companyNumber) throws OfficersMappingException, NoOfficersExistingException {
        Map<String, Object> debugMap = new HashMap<>();
        debugMap.put("company_number", companyNumber);
        LOGGER.info("Calling package for list of officers", debugMap);
        String result = officersRepository.getOfficers(companyNumber);

        if (result == null || result.isEmpty() || result.contains(COMPANY_NOT_FOUND)
                || result.startsWith(NO_OFFICER_STRING)) {
            LOGGER.info("Get officers returned a non-processable string");
            throw new NoOfficersExistingException("Non processable response from respository");
        }

        try {
            JsonNode officersJson = objectMapper.readValue(result, JsonNode.class);
            JsonNode officersNode = officersJson.get("officers");
            List<OfficerDataModel> officerDataModels = objectMapper.convertValue(officersNode,
                    new TypeReference<List<OfficerDataModel>>() {
                    });
            return transformer.convert(officerDataModels);

        } catch (JsonProcessingException e) {
            throw new OfficersMappingException("JsonProcessingException encountered when mapping");
        }
    }

}
