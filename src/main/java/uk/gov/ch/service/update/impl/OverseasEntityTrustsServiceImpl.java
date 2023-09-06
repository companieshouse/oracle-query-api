package uk.gov.ch.service.update.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.TrustsCountNotFoundException;
import uk.gov.ch.model.update.OverseasEntityTrust;
import uk.gov.ch.repository.update.OverseasEntityTrustsRepository;
import uk.gov.ch.service.update.OverseasEntityTrustsService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;
import uk.gov.companieshouse.logging.util.DataMap;

import java.util.List;

@Service
public class OverseasEntityTrustsServiceImpl implements OverseasEntityTrustsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private OverseasEntityTrustsRepository overseasEntityTrustsRepository;

    @Override
    public List<OverseasEntityTrust> getTrusts(String companyNumber)
        throws TrustsCountNotFoundException {
        List<OverseasEntityTrust> trusts = overseasEntityTrustsRepository.getTrusts(companyNumber);

        DataMap dataMap = new DataMap.Builder().companyNumber(companyNumber).build();
        LOGGER.debugContext(companyNumber,
                String.format("getTrusts query list size returned = %s", trusts.size()),
                dataMap.getLogMap());

        if (trusts.isEmpty()) {
            throw new TrustsCountNotFoundException("No trusts were found.");
        } else {
            return trusts;
        }
    }
}
