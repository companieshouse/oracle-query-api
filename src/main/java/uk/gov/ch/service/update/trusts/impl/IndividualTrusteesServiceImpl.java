package uk.gov.ch.service.update.trusts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.TrustDataCountNotFoundException;
import uk.gov.ch.model.update.trusts.IndividualTrusteeData;
import uk.gov.ch.repository.update.trusts.IndividualTrusteesRepository;
import uk.gov.ch.service.update.trusts.IndividualTrusteesService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;
import uk.gov.companieshouse.logging.util.DataMap;

@Service
public class IndividualTrusteesServiceImpl implements IndividualTrusteesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OracleQueryApplication.APPLICATION_NAME_SPACE);
    @Autowired
    private IndividualTrusteesRepository individualTrusteesRepository;

    @Override
    public List<IndividualTrusteeData> getIndividualTrustees(String trustId)
            throws TrustDataCountNotFoundException {
        List<IndividualTrusteeData> individualTrustees = individualTrusteesRepository.getIndividualTrustees(
                trustId);

        if (individualTrustees == null || individualTrustees.isEmpty()) {
            throw new TrustDataCountNotFoundException("No individual trustees were found");
        } else {
            DataMap dataMap = new DataMap.Builder().build();
            dataMap.getLogMap().put("trust_id", trustId);
            LOGGER.debugContext(trustId,
                    String.format("getTrustLinkData query list size returned = %s",
                            individualTrustees.size()),
                    dataMap.getLogMap());

            return individualTrustees;
        }
    }
}
