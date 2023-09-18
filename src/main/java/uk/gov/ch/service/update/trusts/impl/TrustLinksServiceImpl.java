package uk.gov.ch.service.update.trusts.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.TrustDataCountNotFoundException;
import uk.gov.ch.model.update.trusts.TrustLinkData;
import uk.gov.ch.repository.update.trusts.TrustLinksRepository;
import uk.gov.ch.service.update.trusts.TrustLinksService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;
import uk.gov.companieshouse.logging.util.DataMap;

@Service
public class TrustLinksServiceImpl implements TrustLinksService {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OracleQueryApplication.APPLICATION_NAME_SPACE);
    @Autowired
    private TrustLinksRepository individualTrusteesRepository;

    @Override
    public List<TrustLinkData> getTrustLinkData(String oeNumber)
            throws TrustDataCountNotFoundException {
        List<TrustLinkData> trustLinks = individualTrusteesRepository.getTrustLinks(oeNumber);

        if (trustLinks == null || trustLinks.isEmpty()) {
            throw new TrustDataCountNotFoundException("No trust links were found");
        } else {
            DataMap dataMap = new DataMap.Builder().companyNumber(oeNumber).build();
            LOGGER.infoContext(oeNumber,
                    "getTrustLinks query list size returned = " + trustLinks.size(),
                    dataMap.getLogMap());

            return trustLinks;
        }
    }
}
