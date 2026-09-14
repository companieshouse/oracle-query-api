package uk.gov.ch.service.officer.impl;

import org.springframework.stereotype.Service;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OfficerServiceImpl implements OfficerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OracleQueryApplication.APPLICATION_NAME_SPACE);
    private static final String COMPANY_NOT_FOUND = "Company Not Found";
    private static final String NO_OFFICER_STRING = "], \"CreatedTime\":";

    private final OfficersRepository officersRepository;

    private final JsonMapper jsonMapper;

    private final OfficersApiTransformer transformer;

    public OfficerServiceImpl(OfficersRepository officersRepository, JsonMapper jsonMapper, OfficersApiTransformer transformer) {
        this.officersRepository = officersRepository;
        this.jsonMapper = jsonMapper;
        this.transformer = transformer;
    }

    @Override
    public OfficersApi getOfficers(String companyNumber)
            throws OfficersMappingException, NoOfficersExistingException {
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
            JsonNode officersJson = jsonMapper.readValue(result, JsonNode.class);
            JsonNode officersNode = officersJson.get("officers");
            List<OfficerDataModel> officerDataModels = jsonMapper.convertValue(officersNode, new TypeReference<>() {});
            return transformer.convert(officerDataModels);

        } catch (JacksonException e) {
            throw new OfficersMappingException("JacksonException encountered when mapping");
        }
    }

}
