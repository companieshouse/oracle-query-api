package uk.gov.ch.service.corporatebody;

import uk.gov.ch.exception.RegisteredEmailAddressNotFoundException;
import uk.gov.ch.model.corporatebody.sqldatamodels.RegisteredEmailAddressJson;

public interface RegisteredEmailAddressService {
    RegisteredEmailAddressJson getRegisteredEmailAddress(String incorporationNumber) throws RegisteredEmailAddressNotFoundException;
}
