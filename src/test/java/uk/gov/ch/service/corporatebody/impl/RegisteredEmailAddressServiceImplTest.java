package uk.gov.ch.service.corporatebody.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.ch.exception.CorporateBodyDetailsEmailAddressNotFoundException;
import uk.gov.ch.model.corporatebody.sqldatamodels.RegisteredEmailAddressJson;
import uk.gov.ch.model.corporatebody.sqldatamodels.CorporateBodyDetails;
import uk.gov.ch.repository.corporatebody.CorporateBodyDetailsRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RegisteredEmailAddressServiceImplTest {

    @InjectMocks
    private CorporateBodyServiceImpl corporateBodyService;

    @Mock
    private CorporateBodyDetailsRepository corporateBodyDetailsRepository;

    private static final String COMPANY_NUMBER = "OE12345678";

    @Test
    @DisplayName("Get registered email address - email was found")
    void testGetEmailAddressFound() throws CorporateBodyDetailsEmailAddressNotFoundException {
        CorporateBodyDetails registeredEmailAddress = new CorporateBodyDetails();
        registeredEmailAddress.setEmailAddress("franksinatra@ratpack.com");

        when(corporateBodyDetailsRepository.getEmailAddress(COMPANY_NUMBER)).thenReturn(registeredEmailAddress);

        RegisteredEmailAddressJson result = corporateBodyService.getRegisteredEmailAddress(COMPANY_NUMBER);

        assertEquals(result.getRegisteredEmailAddress(), registeredEmailAddress.getEmailAddress());
        verify(corporateBodyDetailsRepository, times(1)).getEmailAddress(COMPANY_NUMBER);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Get entity email address - email not found when null or empty")
    void testGetEmailAddressNotFound(String emailAddress) throws CorporateBodyDetailsEmailAddressNotFoundException {
        CorporateBodyDetails dataWithNoEmail = new CorporateBodyDetails();
        dataWithNoEmail.setEmailAddress(emailAddress);

        when(corporateBodyDetailsRepository.getEmailAddress(COMPANY_NUMBER)).thenReturn(dataWithNoEmail);

        assertThrows(CorporateBodyDetailsEmailAddressNotFoundException.class, () -> corporateBodyService.getRegisteredEmailAddress(COMPANY_NUMBER));

        verify(corporateBodyDetailsRepository, times(1)).getEmailAddress(COMPANY_NUMBER);
    }
}
