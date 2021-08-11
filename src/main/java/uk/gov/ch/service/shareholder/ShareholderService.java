package uk.gov.ch.service.shareholder;
import java.util.List;

import org.springframework.data.domain.Pageable;
import uk.gov.ch.model.shareholder.Shareholder;

public interface ShareholderService {

    int getShareholderCount(String incorporationNumber);

    List<Shareholder> getShareholders(String incorporationNumber, Pageable pageable);
}
