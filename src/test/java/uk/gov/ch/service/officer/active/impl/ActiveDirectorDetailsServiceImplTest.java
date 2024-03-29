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
import uk.gov.ch.model.officer.active.ActiveDirectorDetails;
import uk.gov.ch.repository.officers.ActiveDirectorDetailsRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActiveDirectorDetailsServiceImplTest {

    private static final String COMPANY_NUMBER = "123456789";
    private static final int START_INDEX = 0;
    private static final int ITEMS_PER_PAGE = 10;
    private Pageable pageable = PageRequest.of(START_INDEX, ITEMS_PER_PAGE);

    @Mock
    private ActiveDirectorDetailsRepository activeDirectorDetailsRepository;

    @InjectMocks
    private ActiveDirectorDetailsServiceImpl activeDirectorDetailsService;

    @Test
    void getActiveDirectorDetailsSingleOfficerCompanyTest() throws InvalidActiveOfficersCountFoundException {
        Page<ActiveDirectorDetails> expectedPage = getMockActiveDirectorRepo(1);

        when(activeDirectorDetailsRepository.getActiveDirectorDetails(COMPANY_NUMBER, pageable)).thenReturn(expectedPage);
        ActiveDirectorDetails result = activeDirectorDetailsService.getActiveDirectorDetails(COMPANY_NUMBER, pageable);
        assertEquals(expectedPage.getContent().get(0), result);
    }

    @Test
    void getActiveDirectorDetailsMultiOfficerCompanyTest() {
        Page<ActiveDirectorDetails> expectedList = getMockActiveDirectorRepo(2);

        when(activeDirectorDetailsRepository.getActiveDirectorDetails(COMPANY_NUMBER, pageable)).thenReturn(expectedList);
        Assertions.assertThrows(InvalidActiveOfficersCountFoundException.class, () ->
                activeDirectorDetailsService.getActiveDirectorDetails(COMPANY_NUMBER, pageable)
        );
    }

    @Test
    void getActiveDirectorDetailsZeroOfficerCompanyTest() {
        Page<ActiveDirectorDetails> expectedList = getMockActiveDirectorRepo(0);

        when(activeDirectorDetailsRepository.getActiveDirectorDetails(COMPANY_NUMBER, pageable)).thenReturn(expectedList);
        Assertions.assertThrows(InvalidActiveOfficersCountFoundException.class, () ->
                activeDirectorDetailsService.getActiveDirectorDetails(COMPANY_NUMBER, pageable)
        );
    }

    private Page<ActiveDirectorDetails> getMockActiveDirectorRepo(int count) {
        List<ActiveDirectorDetails> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new ActiveDirectorDetails());
        }
        return new PageImpl<>(list, pageable, 2);
    }
}
