package uk.gov.ch.service.update.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.ch.exception.OverseasEntityEmailAddressNotFoundException;
import uk.gov.ch.model.update.OverseasEntityData;
import uk.gov.ch.model.update.OverseasEntityDataJson;
import uk.gov.ch.repository.update.OverseasEntityDataRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OverseasEntityDataServiceImplTest {

    @InjectMocks
    private OverseasOverseasEntityDataServiceImpl entityDataService;

    @Mock
    private OverseasEntityDataRepository overseasEntityDataRepository;

    private static final String COMPANY_NUMBER = "OE12345678";

    @Test
    @DisplayName("Get entity email address - email was found")
    void testGetEmailAddressFound() throws OverseasEntityEmailAddressNotFoundException {
        OverseasEntityData overseasEntityData = new OverseasEntityData();
        overseasEntityData.setEmailAddress("franksinatra@ratpack.com");

        when(overseasEntityDataRepository.getOverseasEntityData(COMPANY_NUMBER)).thenReturn(overseasEntityData);

        OverseasEntityDataJson result = entityDataService.getEntityEmail(COMPANY_NUMBER);

        assertEquals(result.getEmailAddress(), overseasEntityData.getEmailAddress());
        verify(overseasEntityDataRepository, times(1)).getOverseasEntityData(COMPANY_NUMBER);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Get entity email address - email not found when null or empty")
    void testGetEmailAddressNotFound(String emailAddress) throws OverseasEntityEmailAddressNotFoundException {
        OverseasEntityData dataWithNoEmail = new OverseasEntityData();
        dataWithNoEmail.setEmailAddress(emailAddress);

        when(overseasEntityDataRepository.getOverseasEntityData(COMPANY_NUMBER)).thenReturn(dataWithNoEmail);

        assertThrows(OverseasEntityEmailAddressNotFoundException.class, () -> entityDataService.getEntityEmail(COMPANY_NUMBER));

        verify(overseasEntityDataRepository, times(1)).getOverseasEntityData(COMPANY_NUMBER);
    }
}
