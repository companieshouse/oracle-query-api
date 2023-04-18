package uk.gov.ch.service.update.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.BeneficialOwnerNotFoundException;
import uk.gov.ch.model.update.OverseasEntityBeneficialOwner;
import uk.gov.ch.repository.update.OverseasEntityBeneficialOwnerRepository;
import uk.gov.ch.service.update.OverseasEntityBeneficialOwnerService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;
import uk.gov.companieshouse.logging.util.DataMap;

import java.util.List;

@Service
public class OverseasEntityBeneficialOwnerServiceImpl implements OverseasEntityBeneficialOwnerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private OverseasEntityBeneficialOwnerRepository beneficialOwnerDataRepository;

    @Override
    public List<OverseasEntityBeneficialOwner> getBeneficialOwners(String companyNumber)
        throws BeneficialOwnerNotFoundException {
        List<OverseasEntityBeneficialOwner> beneficialOwners = beneficialOwnerDataRepository.getBeneficialOwners(companyNumber);

        DataMap dataMap = new DataMap.Builder().build();
        dataMap.getLogMap().put("companyNumber", companyNumber);
        LOGGER.debugContext(companyNumber,
                String.format("getBeneficialOwner query list size returned = %s", beneficialOwners.size()),
                dataMap.getLogMap());

        if (beneficialOwners.isEmpty()) {
            throw new BeneficialOwnerNotFoundException("No beneficial owners were found.");
        } else {
            return beneficialOwners;
        }
    }
}
