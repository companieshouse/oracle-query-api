package uk.gov.ch.service.psc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.ch.exception.ServiceException;
import uk.gov.ch.model.psc.PersonWithSignificantControl;
import uk.gov.ch.repository.psc.PersonsWithSignificantControlRepository;
import uk.gov.ch.service.psc.PersonsWithSignificantControlService;

import java.util.List;

@Service
public class PersonsWithSignificantControlServiceImpl implements PersonsWithSignificantControlService {

    @Autowired
    private PersonsWithSignificantControlRepository personsWithSignificantControlRepository;

    @Override
    public List<PersonWithSignificantControl> getPersonsWithSignificantControl(String companyNumber) throws ServiceException {
        Pageable pageable = PageRequest.of(0, 10);
        Page<PersonWithSignificantControl> pscPage = personsWithSignificantControlRepository
                .findPersonsWithSignificantControl(companyNumber, pageable);

        return pscPage.getContent();
    }
}
