package uk.gov.ch.service.officer.active.impl;

import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.ch.model.officer.active.ActiveOfficerDetails;
import uk.gov.ch.repository.officers.ActiveOfficerDetailsRepository;
import uk.gov.ch.service.officer.active.ActiveOfficerDetailsService;

public class ActiveOfficerDetailsServiceImpl implements ActiveOfficerDetailsService {

    @Autowired
    private ActiveOfficerDetailsRepository activeOfficerDetailsRepository;

    @Override
    public ActiveOfficerDetails getActiveOfficerDetails(String companyNumber) {
        return activeOfficerDetailsRepository.getActiveOfficerDetails(companyNumber);
    }
}
