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
import uk.gov.ch.exception.RegisteredEmailAddressNotFoundException;
import uk.gov.ch.model.corporatebody.sqldatamodels.RegisteredEmailAddress;
import uk.gov.ch.model.corporatebody.sqldatamodels.RegisteredEmailAddressJson;
import uk.gov.ch.repository.corporatebody.RegisteredEmailAddressRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RegisteredEmailAddressServiceImplTest {

    @InjectMocks
    private RegisteredEmailAddressServiceImpl registeredEmailAddressService;

    @Mock
    private RegisteredEmailAddressRepository registeredEmailAddressRepository;

    private static final String COMPANY_NUMBER = "OE12345678";

    @Test
    @DisplayName("Get registered email address - email was found")
    void testGetEmailAddressFound() throws RegisteredEmailAddressNotFoundException {
        RegisteredEmailAddress registeredEmailAddress = new RegisteredEmailAddress();
        registeredEmailAddress.setRegisteredEmailAddress("franksinatra@ratpack.com");

        when(registeredEmailAddressRepository.getRegisteredEmailAddress(COMPANY_NUMBER)).thenReturn(registeredEmailAddress);

        RegisteredEmailAddressJson result = registeredEmailAddressService.getRegisteredEmailAddress(COMPANY_NUMBER);

        assertEquals(result.getRegisteredEmailAddress(), registeredEmailAddress.getRegisteredEmailAddress());
        verify(registeredEmailAddressRepository, times(1)).getRegisteredEmailAddress(COMPANY_NUMBER);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Get entity email address - email not found when null or empty")
    void testGetEmailAddressNotFound(String emailAddress) throws RegisteredEmailAddressNotFoundException {
        RegisteredEmailAddress dataWithNoEmail = new RegisteredEmailAddress();
        dataWithNoEmail.setRegisteredEmailAddress(emailAddress);

        when(registeredEmailAddressRepository.getRegisteredEmailAddress(COMPANY_NUMBER)).thenReturn(dataWithNoEmail);

        assertThrows(RegisteredEmailAddressNotFoundException.class, () -> registeredEmailAddressService.getRegisteredEmailAddress(COMPANY_NUMBER));

        verify(registeredEmailAddressRepository, times(1)).getRegisteredEmailAddress(COMPANY_NUMBER);
    }
}
