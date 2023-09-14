package uk.gov.ch.service.update.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.TrustDataCountNotFoundException;
import uk.gov.ch.model.update.CorporateTrusteeData;
import uk.gov.ch.repository.update.CorporateTrusteesRepository;
import uk.gov.ch.service.update.CorporateTrusteesService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;
import uk.gov.companieshouse.logging.util.DataMap;


@Service
public class CorporateTrusteesServiceImpl implements CorporateTrusteesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private CorporateTrusteesRepository corporateTrusteesRepository;

    @Override
    public List<CorporateTrusteeData> getCorporateTrusteeData(String trustId)
            throws TrustDataCountNotFoundException {
        List<CorporateTrusteeData> corporateTrusteeDataList = corporateTrusteesRepository.getCorporateTrustees(
                trustId);

        DataMap dataMap = new DataMap.Builder().build();
        dataMap.getLogMap().put("trust_id", trustId);
        LOGGER.debugContext(trustId, "getCorporateTrusteeData query list size returned = "
                + corporateTrusteeDataList.size(), dataMap.getLogMap());

        if (corporateTrusteeDataList.isEmpty()) {
            throw new TrustDataCountNotFoundException(
                    "No corporate trustee data found with trustId " + trustId);
        } else {
            return corporateTrusteeDataList;
        }
    }
}
