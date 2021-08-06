package uk.gov.ch.service.officer.active.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import uk.gov.ch.exception.InvalidActiveOfficersCountFoundException;
import uk.gov.ch.model.officer.active.ActiveOfficerDetails;
import uk.gov.ch.repository.officers.ActiveOfficerDetailsRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActiveOfficerDetailsServiceImplTest {

    private static final String COMPANY_NUMBER = "123456789";
    private static final int START_INDEX = 0;
    private static final int ITEMS_PER_PAGE = 10;
    Pageable pageable = PageRequest.of(START_INDEX, ITEMS_PER_PAGE);

    @Mock
    ActiveOfficerDetailsRepository activeOfficerDetailsRepository;

    @InjectMocks
    private ActiveOfficerDetailsServiceImpl activeOfficerDetailsService;

    @Test
    void getActiveOfficerDetailsSingleOfficerCompanyTest() throws InvalidActiveOfficersCountFoundException {
        Page<ActiveOfficerDetails> expectedPage = getMockActiveOfficerRepo(1);

        when(activeOfficerDetailsRepository.getActiveOfficerDetails(COMPANY_NUMBER, pageable)).thenReturn(expectedPage);
        ActiveOfficerDetails result = activeOfficerDetailsService.getActiveOfficerDetails(COMPANY_NUMBER, pageable);
        assertEquals(expectedPage.getContent().get(0), result);
    }

    @Test
    void getActiveOfficerDetailsMultiOfficerCompanyTest() throws InvalidActiveOfficersCountFoundException {
        Page<ActiveOfficerDetails> expectedList = getMockActiveOfficerRepo(2);

        when(activeOfficerDetailsRepository.getActiveOfficerDetails(COMPANY_NUMBER, pageable)).thenReturn(expectedList);
        Assertions.assertThrows(InvalidActiveOfficersCountFoundException.class, () -> {
            activeOfficerDetailsService.getActiveOfficerDetails(COMPANY_NUMBER, pageable);
        });
    }

    @Test
    void getActiveOfficerDetailsZeroOfficerCompanyTest() throws InvalidActiveOfficersCountFoundException {
        Page<ActiveOfficerDetails> expectedList = getMockActiveOfficerRepo(0);

        when(activeOfficerDetailsRepository.getActiveOfficerDetails(COMPANY_NUMBER, pageable)).thenReturn(expectedList);
        Assertions.assertThrows(InvalidActiveOfficersCountFoundException.class, () -> {
            activeOfficerDetailsService.getActiveOfficerDetails(COMPANY_NUMBER, pageable);
        });
    }

    private Page<ActiveOfficerDetails> getMockActiveOfficerRepo(int count) {
        List<ActiveOfficerDetails> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new ActiveOfficerDetails());
        }
        return new PageImpl<>(list, pageable, 2);
    }
}
