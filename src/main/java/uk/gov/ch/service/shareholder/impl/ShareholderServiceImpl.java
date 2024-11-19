package uk.gov.ch.service.shareholder.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.ch.model.shareholder.Shareholder;
import uk.gov.ch.repository.shareholder.ShareholderRepository;
import uk.gov.ch.service.shareholder.ShareholderService;

@Service
public class ShareholderServiceImpl implements ShareholderService {

    @Autowired
    private ShareholderRepository shareholderRepository;

    @Override
    public int getShareholderCount(String incorporationNumber) {
        return shareholderRepository.getShareholderCount(incorporationNumber);
    }

    public List<Shareholder> getShareholders(String incorporationNumber, Pageable pageable) {
        return shareholderRepository.getShareholders(incorporationNumber, pageable).toList();
    }

}
