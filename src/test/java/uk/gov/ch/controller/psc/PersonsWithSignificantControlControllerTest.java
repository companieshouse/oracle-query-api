package uk.gov.ch.controller.psc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.ch.exception.ServiceException;
import uk.gov.ch.model.psc.PersonWithSignificantControl;
import uk.gov.ch.service.psc.PersonsWithSignificantControlService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonsWithSignificantControlControllerTest {
    private static final String COMPANY_NUMBER = "12345678";

    @InjectMocks
    PersonsWithSignificantControlController personsWithSignificantControlController;

    @Mock
    PersonsWithSignificantControlService personsWithSignificantControlService;

    @Test
    @DisplayName("Get persons with significant control controller")
    void testGetPeopleWithSigniificantControl() throws ServiceException {
        List<PersonWithSignificantControl> testPscs = new ArrayList<>();
        when(personsWithSignificantControlService.
                getPersonsWithSignificantControl(COMPANY_NUMBER)).thenReturn(testPscs);
        ResponseEntity<List<PersonWithSignificantControl>> response =
                personsWithSignificantControlController.getPeopleWithSigniificantControl(COMPANY_NUMBER);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
