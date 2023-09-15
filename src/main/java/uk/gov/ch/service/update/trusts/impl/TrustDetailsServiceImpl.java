package uk.gov.ch.service.update.trusts.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.TrustDataCountNotFoundException;
import uk.gov.ch.model.update.trusts.TrustDetails;
import uk.gov.ch.repository.update.trusts.TrustDetailsRepository;
import uk.gov.ch.service.update.trusts.TrustDetailsService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;
import uk.gov.companieshouse.logging.util.DataMap;

import java.util.List;

@Service
public class TrustDetailsServiceImpl implements TrustDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private TrustDetailsRepository trustDetailsRepository;

    @Override
    public List<TrustDetails> getTrustDetails(String companyNumber)
            throws TrustDataCountNotFoundException {
        List<TrustDetails> trustDetails = trustDetailsRepository.getTrustDetails(companyNumber);

        if (trustDetails.isEmpty()) {
            throw new TrustDataCountNotFoundException("No trusts were found.");
        } else {
            DataMap dataMap = new DataMap.Builder().companyNumber(companyNumber).build();
            LOGGER.debugContext(companyNumber,
                    String.format("getTrustDetails query list size returned = %s", trustDetails.size()),
                    dataMap.getLogMap());

            return trustDetails;
        }
    }
}
