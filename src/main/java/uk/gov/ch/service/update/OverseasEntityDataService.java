package uk.gov.ch.service.update;

import uk.gov.ch.exception.OverseasEntityEmailAddressNotFoundException;
import uk.gov.ch.model.update.OverseasEntityDataJson;

public interface OverseasEntityDataService {
    OverseasEntityDataJson getEntityEmail(String incorporationNumber) throws OverseasEntityEmailAddressNotFoundException;
}
