package uk.gov.ch.service.register.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.ch.model.register.RegisterLocation;
import uk.gov.ch.repository.register.RegisterLocationRepository;
import uk.gov.ch.service.register.RegisterLocationService;

@Service
public class RegisterLocationServiceImpl implements RegisterLocationService {

    @Autowired
    private RegisterLocationRepository registerLocationRepository;

    @Override
    public List<RegisterLocation> getRegisterLocation(String companyNumber, Pageable pageable) {
        Page<RegisterLocation> registerLocationPage = registerLocationRepository.getRegisterLocation(
                companyNumber, pageable);
        return registerLocationPage.getContent();
    }
}
