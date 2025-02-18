package uk.gov.ch.service.psc;

import java.util.List;
import org.springframework.data.domain.Pageable;
import uk.gov.ch.model.psc.PersonWithSignificantControl;

public interface PersonsWithSignificantControlService {

    List<PersonWithSignificantControl> getPersonsWithSignificantControl(String companyNumber,
            Pageable pageable);
}

