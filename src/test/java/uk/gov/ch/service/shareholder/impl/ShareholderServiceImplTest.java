package uk.gov.ch.service.shareholder.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import uk.gov.ch.repository.shareholder.ShareholderRepository;
import uk.gov.ch.model.shareholder.Shareholder;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ShareholderServiceImplTest {

    private static final String COMPANY_NUMBER = "123456789";

    @Mock
    private ShareholderRepository shareholderRepository;

    @InjectMocks
    private ShareholderServiceImpl shareholderService;

    @Test
    void getShareholderCount() {
        when(shareholderRepository.getShareholderCount(any())).thenReturn(3);

        int result = shareholderService.getShareholderCount(COMPANY_NUMBER);

        assertEquals(3, result);
    }

   @Test
    void getShareholders() {
        List<Shareholder> expectedList = new ArrayList<Shareholder>();
        expectedList.add(new Shareholder());

        when(shareholderRepository.getShareholders(any())).thenReturn(expectedList);

        List<Shareholder> result = shareholderService.getShareholders(COMPANY_NUMBER);

        assertEquals(expectedList, result);
    }
}