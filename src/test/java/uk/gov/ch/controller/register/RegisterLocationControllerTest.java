package uk.gov.ch.controller.register;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.ch.model.register.RegisterLocation;
import uk.gov.ch.service.register.RegisterLocationService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RegisterLocationControllerTest {
    private static final String COMPANY_NUMBER = "12345678";
    private static final int START_INDEX = 0;
    private static final int ITEMS_PER_PAGE = 10;

    @InjectMocks
    RegisterLocationController registerLocationController;

    @Mock
    RegisterLocationService registerLocationService;

    @Test
    @DisplayName("Register Location Controller test")
    void testGetRegisterLocation() {
        Pageable pageable = PageRequest.of(START_INDEX, ITEMS_PER_PAGE);
        List<RegisterLocation> testRegisterLocation = new ArrayList<>();
        when(registerLocationService.
                getRegisterLocation(COMPANY_NUMBER, pageable)).thenReturn(testRegisterLocation);
        ResponseEntity<List<RegisterLocation>> response =
                registerLocationController.getRegisterLocation(COMPANY_NUMBER, START_INDEX, ITEMS_PER_PAGE);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
