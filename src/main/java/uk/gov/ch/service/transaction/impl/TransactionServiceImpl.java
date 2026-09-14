package uk.gov.ch.service.transaction.impl;

import org.springframework.stereotype.Service;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.TransactionMappingException;
import uk.gov.ch.model.transaction.jsondatamodels.FilingHistoryTransaction;
import uk.gov.ch.repository.transaction.TransactionRepository;
import uk.gov.ch.service.transaction.TransactionService;
import uk.gov.ch.transformers.transaction.TransactionTransformer;
import uk.gov.companieshouse.api.model.filinghistory.FilingHistoryApi;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);
    private static final String MESSAGE = "message";
    private static final String COMPANY_HAS_NO_TRANSACTIONS = "Company has no transactions";
    private static final String COMPANY_NOT_FOUND = "Company Not Found";

    private final TransactionRepository transactionRepository;

    private final TransactionTransformer transactionTransformer;

    private final JsonMapper jsonMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionTransformer transactionTransformer, JsonMapper jsonMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionTransformer = transactionTransformer;
        this.jsonMapper = jsonMapper;
    }

    @Override
    public FilingHistoryApi getTransactions(String companyNumber)
            throws TransactionMappingException {
        Map<String, Object> logMap = new HashMap<>();
        logMap.put("company_number", companyNumber);
        LOGGER.info("Calling package for transaction history", logMap);
        String result = transactionRepository.getTransactionJson(companyNumber);
        FilingHistoryApi response = new FilingHistoryApi();
        if (result == null || result.isEmpty() || result.equalsIgnoreCase(
                COMPANY_HAS_NO_TRANSACTIONS)
                || result.equalsIgnoreCase(COMPANY_NOT_FOUND)) {
            logMap.remove(MESSAGE);
            LOGGER.info("Null or empty response from repository", logMap);
            return response;
        }
        List<FilingHistoryTransaction> filingHistoryTransactions = null;

        try {
            JsonNode filingHistoryJson = jsonMapper.readValue(result, JsonNode.class);
            JsonNode filingHistoryNode = filingHistoryJson.get("filing_history");
            filingHistoryTransactions = jsonMapper.convertValue(filingHistoryNode,
                    new TypeReference<List<FilingHistoryTransaction>>() {
                    });
            response = transactionTransformer.convertToFilingHistoryApi(filingHistoryTransactions);

        } catch (DatabindException e) {
            logMap.remove(MESSAGE);
            LOGGER.info("JSON Mapping Exception on response", logMap);
            throw new TransactionMappingException(e.getOriginalMessage());
        } catch (JacksonException e) {
            logMap.remove(MESSAGE);
            LOGGER.info("JSON Processing Exception on response", logMap);
            throw new TransactionMappingException(e.getOriginalMessage());
        }
        return response;
    }

}
