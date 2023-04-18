package uk.gov.ch.service.update;

import uk.gov.ch.exception.BeneficialOwnerNotFoundException;
import uk.gov.ch.model.update.OverseasEntityBeneficialOwner;

import java.util.List;

public interface OverseasEntityBeneficialOwnerService {
    List<OverseasEntityBeneficialOwner> getBeneficialOwners(String companyNumber)
            throws BeneficialOwnerNotFoundException;
}
