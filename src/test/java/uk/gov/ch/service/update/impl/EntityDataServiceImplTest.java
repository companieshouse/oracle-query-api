package uk.gov.ch.service.update.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.ch.exception.EntityEmailNotFoundException;
import uk.gov.ch.model.update.EntityData;
import uk.gov.ch.repository.update.EntityDataRepository;
import uk.gov.ch.update.impl.EntityDataServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EntityDataServiceImplTest {

    @InjectMocks
    private EntityDataServiceImpl entityDataService;

    @Mock
    private EntityDataRepository entityDataRepository;

    private static final String INCORPORATION_NUMBER = "12345678";

    @Test
    @DisplayName("Get entity email address - email was found")
    void testGetEmailAddressFound() throws EntityEmailNotFoundException {
        EntityData entityData = new EntityData();
        entityData.setEmailAddress("FrankSinatra@ratpack.com");
        when(entityDataRepository.getEmailAddress(INCORPORATION_NUMBER)).thenReturn(entityData);

        EntityData result = entityDataService.getEntityEmail(INCORPORATION_NUMBER);

        assertEquals(entityData, result);
    }

    @Test
    @DisplayName("Get entity email address - email not found")
    void testGetEmailAddressNotFound() {

        when(entityDataRepository.getEmailAddress(INCORPORATION_NUMBER)).thenReturn(new EntityData());

        assertThrows(EntityEmailNotFoundException.class, () -> {entityDataService.getEntityEmail(INCORPORATION_NUMBER);});
    }
}
