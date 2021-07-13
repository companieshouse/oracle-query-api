package uk.gov.ch.service.officer.active.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.ch.exception.InvalidActiveOfficersCountFoundException;
import uk.gov.ch.model.officer.active.ActiveOfficerDetails;
import uk.gov.ch.repository.officers.ActiveOfficerDetailsRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActiveOfficerDetailsServiceImplTest {

    private static final String COMPANY_NUMBER = "123456789";

    @Mock
    ActiveOfficerDetailsRepository activeOfficerDetailsRepository;

    @InjectMocks
    private ActiveOfficerDetailsServiceImpl activeOfficerDetailsService;

    @Test
    void getActiveOfficerDetailsSingleOfficerCompanyTest() throws InvalidActiveOfficersCountFoundException {
        List<ActiveOfficerDetails> expectedList = new ArrayList<>();
        ActiveOfficerDetails mockOfficer = new ActiveOfficerDetails();
        expectedList.add(mockOfficer);

        when(activeOfficerDetailsRepository.getActiveOfficerDetails(any())).thenReturn(expectedList);
        ActiveOfficerDetails result = activeOfficerDetailsService.getActiveOfficerDetails(COMPANY_NUMBER);
        assertEquals(mockOfficer, result);
    }

    @Test
    void getActiveOfficerDetailsMultiOfficerCompanyTest() throws InvalidActiveOfficersCountFoundException {
        List<ActiveOfficerDetails> expectedList = new ArrayList<>();
        ActiveOfficerDetails mockOfficer = new ActiveOfficerDetails();
        expectedList.add(mockOfficer);
        expectedList.add(new ActiveOfficerDetails());

        when(activeOfficerDetailsRepository.getActiveOfficerDetails(any())).thenReturn(expectedList);
        Assertions.assertThrows(InvalidActiveOfficersCountFoundException.class, () -> {
            activeOfficerDetailsService.getActiveOfficerDetails(COMPANY_NUMBER);
        });
    }

    @Test
    void getActiveOfficerDetailsZeroOfficerCompanyTest() throws InvalidActiveOfficersCountFoundException {
        List<ActiveOfficerDetails> expectedList = new ArrayList<>();

        when(activeOfficerDetailsRepository.getActiveOfficerDetails(any())).thenReturn(expectedList);
        Assertions.assertThrows(InvalidActiveOfficersCountFoundException.class, () -> {
            activeOfficerDetailsService.getActiveOfficerDetails(COMPANY_NUMBER);
        });
    }
}
