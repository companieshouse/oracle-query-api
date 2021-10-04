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

    @Autowired
    OfficersRepository officersRepository;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private OfficersApiTransformer transformer;

    @Override
    public OfficersApi getOfficers(String companyNumber) {
        Map<String, Object> debugMap = new HashMap<>();
        debugMap.put("company_number", companyNumber);
        LOGGER.info("Calling package for list of officers", debugMap);
        String result = officersRepository.getOfficers(companyNumber);

        if (result == null || result.isEmpty() || result.contains(COMPANY_NOT_FOUND)) {
            LOGGER.info("Get officers returned a non-processable string");
            return new OfficersApi();
        }

        try {
            JsonNode officersJson = objectMapper.readValue(result, JsonNode.class);
            JsonNode officersNode = officersJson.get("officers");
            List<OfficerDataModel> officerDataModels = objectMapper.convertValue(officersNode,
                    new TypeReference<List<OfficerDataModel>>() {
                    });
            return transformer.convert(officerDataModels);
            
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

}
