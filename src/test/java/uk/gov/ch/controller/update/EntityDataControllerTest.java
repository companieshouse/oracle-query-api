package uk.gov.ch.controller.update;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.ch.exception.EntityEmailNotFoundException;
import uk.gov.ch.model.update.EntityData;
import uk.gov.ch.update.impl.EntityDataServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EntityDataControllerTest {

    @Mock
    private EntityDataServiceImpl entityDataService;

    @InjectMocks
    private EntityDataController entityDataController;

    private static final String INCORPORATION_NUMBER = "12345678";

    @Test
    @DisplayName("Get entity email address - email was found")
    void testGetEntityEmailMethodReturnsExpectedEntityData() throws EntityEmailNotFoundException {
        EntityData expectedEntityData = new EntityData();
        expectedEntityData.setEmailAddress("test@example.com");

        when(entityDataService.getEntityEmail(INCORPORATION_NUMBER)).thenReturn(expectedEntityData);

        ResponseEntity<EntityData> responseEntity = entityDataController.getEntityEmail(INCORPORATION_NUMBER);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedEntityData, responseEntity.getBody());
    }

    @Test
    @DisplayName("Throws 400 when entity email not found")
    void testEntityEmailNotFound() throws EntityEmailNotFoundException {

        when(entityDataService.getEntityEmail(INCORPORATION_NUMBER)).thenThrow(new EntityEmailNotFoundException("No entity email address found for company " + INCORPORATION_NUMBER));

        ResponseEntity<EntityData> response = entityDataController.getEntityEmail(INCORPORATION_NUMBER);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}