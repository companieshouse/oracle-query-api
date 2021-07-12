package uk.gov.ch.service.officer.active.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.ch.exception.NoActiveOfficersFoundException;
import uk.gov.ch.model.officer.active.ActiveOfficerDetails;
import uk.gov.ch.repository.officers.ActiveOfficerDetailsRepository;

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
    void getActiveOfficerDetailsTest() throws NoActiveOfficersFoundException {
        ActiveOfficerDetails mockOfficer = new ActiveOfficerDetails();

        when(activeOfficerDetailsRepository.getActiveOfficerDetails(any())).thenReturn(mockOfficer);

        ActiveOfficerDetails result = activeOfficerDetailsService.getActiveOfficerDetails(COMPANY_NUMBER);

        assertEquals(mockOfficer, result);
    }
}
