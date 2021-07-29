package uk.gov.ch.service.transaction.impl;

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
import uk.gov.ch.model.transaction.jsondatamodels.FilingHistoryTransaction;
import uk.gov.ch.repository.transaction.TransactionRepository;
import uk.gov.ch.service.transaction.TransactionService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@Service
public class TransactionServiceImpl implements TransactionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public List<FilingHistoryTransaction> getTransactions(String companyNumber) {
		LOGGER.info("Calling package for transaction history for " + companyNumber);
		String result = transactionRepository.getTransactionJson(companyNumber);
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> logMap = new HashMap<>();
		List<FilingHistoryTransaction> filingHistoryTransactions = null;
		try {
			JsonNode filingHistoryJson = objectMapper.readValue(result, JsonNode.class);
			JsonNode filingHistoryNode = filingHistoryJson.get("filing_history");
			filingHistoryTransactions = objectMapper.convertValue(
					filingHistoryNode, new TypeReference<List<FilingHistoryTransaction>>() {});
			logMap.put("transaction-history", filingHistoryTransactions);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info("Result of the call : ", logMap);
		return filingHistoryTransactions;
	}

}
