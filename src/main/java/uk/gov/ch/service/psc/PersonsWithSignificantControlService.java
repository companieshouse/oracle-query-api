package uk.gov.ch.service.psc;

import uk.gov.ch.exception.ServiceException;
import uk.gov.ch.model.psc.PersonWithSignificantControl;

import java.util.List;

public interface PersonsWithSignificantControlService {

     List<PersonWithSignificantControl> getPersonsWithSignificantControl(String companyNumber) throws ServiceException;
}

