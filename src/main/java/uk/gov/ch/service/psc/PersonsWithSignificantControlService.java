package uk.gov.ch.service.psc;

import org.springframework.data.domain.Pageable;
import uk.gov.ch.model.psc.PersonWithSignificantControl;

import java.util.List;

public interface PersonsWithSignificantControlService {

     List<PersonWithSignificantControl> getPersonsWithSignificantControl(String companyNumber, Pageable pageable);
}

