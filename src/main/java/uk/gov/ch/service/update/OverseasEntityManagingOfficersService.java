package uk.gov.ch.service.update;

import java.util.List;
import uk.gov.ch.exception.ManagingOfficerCountNotFoundException;
import uk.gov.ch.model.update.OverseasEntityManagingOfficerData;

public interface OverseasEntityManagingOfficersService {

    List<OverseasEntityManagingOfficerData> getOverseasEntityManagingOfficers(String companyNumber)
            throws ManagingOfficerCountNotFoundException;
}
