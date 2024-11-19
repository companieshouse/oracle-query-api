package uk.gov.ch.service.update.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.BeneficialOwnerCountNotFoundException;
import uk.gov.ch.model.update.OverseasEntityBeneficialOwner;
import uk.gov.ch.repository.update.OverseasEntityBeneficialOwnerRepository;
import uk.gov.ch.service.update.OverseasEntityBeneficialOwnerService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;
import uk.gov.companieshouse.logging.util.DataMap;

@Service
public class OverseasEntityBeneficialOwnerServiceImpl implements
        OverseasEntityBeneficialOwnerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private OverseasEntityBeneficialOwnerRepository beneficialOwnerDataRepository;

    @Override
    public List<OverseasEntityBeneficialOwner> getBeneficialOwners(String companyNumber)
            throws BeneficialOwnerCountNotFoundException {
        List<OverseasEntityBeneficialOwner> beneficialOwners = beneficialOwnerDataRepository.getBeneficialOwners(
                companyNumber);

        DataMap dataMap = new DataMap.Builder().companyNumber(companyNumber).build();
        LOGGER.debugContext(companyNumber,
                String.format("getBeneficialOwner query list size returned = %s",
                        beneficialOwners.size()),
                dataMap.getLogMap());

        if (beneficialOwners.isEmpty()) {
            throw new BeneficialOwnerCountNotFoundException("No beneficial owners were found.");
        } else {
            return beneficialOwners;
        }
    }
}
