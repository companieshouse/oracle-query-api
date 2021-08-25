package uk.gov.ch.service.register;

import org.springframework.data.domain.Pageable;
import uk.gov.ch.model.register.RegisterLocation;

import java.util.List;

public interface RegisterLocationService {
    List<RegisterLocation> getRegisterLocation(String companyNumber, Pageable pageable);
}
