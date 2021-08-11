package uk.gov.ch.service.transaction.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.TransactionMappingException;
import uk.gov.ch.model.transaction.jsondatamodels.FilingHistoryTransaction;
import uk.gov.ch.repository.transaction.TransactionRepository;
import uk.gov.ch.service.transaction.TransactionService;
import uk.gov.ch.transformers.transaction.TransactionTransformer;
import uk.gov.companieshouse.api.model.filinghistory.FilingApi;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);
    private static final String MESSAGE = "message";
    private static final String COMPANY_NOT_FOUND = "Company Not Found";

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionTransformer transactionTransformer;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<FilingApi> getTransactions(String companyNumber) throws TransactionMappingException {
        Map<String, Object> logMap = new HashMap<>();
        logMap.put("company_number", companyNumber);
        LOGGER.info("Calling package for transaction history", logMap);
        String result = transactionRepository.getTransactionJson(companyNumber);
        List<FilingApi> response = new ArrayList<>();
        if (result == null || result.isEmpty() || result.equalsIgnoreCase(COMPANY_NOT_FOUND)) {
            logMap.remove(MESSAGE);
            LOGGER.info("Null or empty response from repository", logMap);
            return response;
        }
        List<FilingHistoryTransaction> filingHistoryTransactions = null;

        try {
            JsonNode filingHistoryJson = objectMapper.readValue(result, JsonNode.class);
            JsonNode filingHistoryNode = filingHistoryJson.get("filing_history");
            filingHistoryTransactions = objectMapper.convertValue(filingHistoryNode,
                    new TypeReference<List<FilingHistoryTransaction>>() {
                    });
            logMap.put("transaction-history", filingHistoryTransactions);
            for (FilingHistoryTransaction fht : filingHistoryTransactions) {
                response.add(transactionTransformer.convert(fht));
            }
        } catch (JsonMappingException e) {
            logMap.remove(MESSAGE);
            LOGGER.info("JSON Mapping Exception on response", logMap);
            throw new TransactionMappingException(e.getOriginalMessage());
        } catch (JsonProcessingException e) {
            logMap.remove(MESSAGE);
            LOGGER.info("JSON Processing Exception on response", logMap);
            throw new TransactionMappingException(e.getOriginalMessage());
        }
        return response;
    }

}
