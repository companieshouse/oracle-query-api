package uk.gov.ch.service.shareholder.impl;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.ch.model.shareholder.Shareholder;
import uk.gov.ch.repository.shareholder.ShareholderRepository;
import uk.gov.ch.service.shareholder.ShareholderService;

@Service
public class ShareholderServiceImpl implements ShareholderService {

    private ShareholderRepository shareholderRepository;

    public ShareholderServiceImpl(ShareholderRepository shareholderRepository) {
        this.shareholderRepository = shareholderRepository;
    }

    @Override
    public int getShareholderCount(String incorporationNumber) {
        return shareholderRepository.getShareholderCount(incorporationNumber);
    }

    public List<Shareholder> getShareholders(String incorporationNumber, Pageable pageable) {
        return shareholderRepository.getShareholders(incorporationNumber, pageable).toList();
    }

}
