package uk.gov.ch.service.update;

import java.util.List;
import uk.gov.ch.exception.BeneficialOwnerCountNotFoundException;
import uk.gov.ch.model.update.OverseasEntityBeneficialOwner;

public interface OverseasEntityBeneficialOwnerService {

    List<OverseasEntityBeneficialOwner> getBeneficialOwners(String companyNumber)
            throws BeneficialOwnerCountNotFoundException;
}
