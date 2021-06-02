package uk.gov.ch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.gov.ch.dao.ShareholderDao;
import uk.gov.ch.model.Shareholder;
import uk.gov.ch.service.ShareholderService;

@Service
public class ShareholderServiceImpl implements ShareholderService {

    @Autowired
    private ShareholderDao shareholderDao;

    @Override
    public int getShareholderCount(String incorporationNumber) {  
        return shareholderDao.getShareholderCount(incorporationNumber);
    }

    @Override
    public List<Shareholder> getShareholders(String incorporationNumber) {  
        return shareholderDao.getShareholders(incorporationNumber);
    }
    
}
