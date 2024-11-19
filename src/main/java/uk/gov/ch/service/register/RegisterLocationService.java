package uk.gov.ch.service.register;

import java.util.List;
import org.springframework.data.domain.Pageable;
import uk.gov.ch.model.register.RegisterLocation;

public interface RegisterLocationService {

    List<RegisterLocation> getRegisterLocation(String companyNumber, Pageable pageable);
}
