package uk.gov.ch.service.update;

import uk.gov.ch.exception.ManagingOfficerCountNotFoundException;
import uk.gov.ch.model.update.OverseasEntityManagingOfficerData;

import java.util.List;

public interface OverseasEntityManagingOfficersService {
    List<OverseasEntityManagingOfficerData> getOverseasEntityManagingOfficers(String companyNumber)
            throws ManagingOfficerCountNotFoundException;
}
