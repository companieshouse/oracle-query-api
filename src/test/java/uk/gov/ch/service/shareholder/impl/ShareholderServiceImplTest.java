package uk.gov.ch.service.shareholder.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import uk.gov.ch.repository.shareholder.ShareholderRepository;
import uk.gov.ch.model.shareholder.Shareholder;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ShareholderServiceImplTest {

    private static final String COMPANY_NUMBER = "123456789";
    private static final int START_INDEX = 0;
    private static final int ITEMS_PER_PAGE = 10;
    Pageable pageable = PageRequest.of(START_INDEX, ITEMS_PER_PAGE);

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
        Page<Shareholder> expectedList = getMockShareholderRepo(1);
        when(shareholderRepository.getShareholders(COMPANY_NUMBER, pageable)).thenReturn(expectedList);
        List<Shareholder> result = shareholderService.getShareholders(COMPANY_NUMBER, pageable);
        assertEquals(expectedList.getContent(), result);
    }

    private Page<Shareholder> getMockShareholderRepo(int count) {
        List<Shareholder> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Shareholder());
        }
        return new PageImpl<>(list, pageable, 2);
    }
}