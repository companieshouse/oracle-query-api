package uk.gov.ch.controller.corporatebody;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.ch.exception.RegisteredEmailAddressNotFoundException;
import uk.gov.ch.model.corporatebody.sqldatamodels.RegisteredEmailAddressJson;
import uk.gov.ch.service.corporatebody.impl.RegisteredEmailAddressServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest(RegisteredEmailAddressController.class)
class RegisteredEmailAddressControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private RegisteredEmailAddressServiceImpl registeredEmailAddressService;

    @InjectMocks
    private RegisteredEmailAddressController registeredEmailAddressController;

    private static final String VALID_COMPANY_NUMBER = "OE123456";

    @Test
    @DisplayName("Get registered email address - email was found")
    void testGetRegisteredEmailAddressMethodReturnsExpectedRegisteredEmailAddress() throws RegisteredEmailAddressNotFoundException {
        RegisteredEmailAddressJson expectedRegisteredEmailAddress = new RegisteredEmailAddressJson();
        expectedRegisteredEmailAddress.setRegisteredEmailAddress("frank.sinatra@ratpack.com");

        when(registeredEmailAddressService.getRegisteredEmailAddress(VALID_COMPANY_NUMBER)).thenReturn(expectedRegisteredEmailAddress);

        ResponseEntity<RegisteredEmailAddressJson> responseEntity = registeredEmailAddressController.getRegisteredEmailAddress(VALID_COMPANY_NUMBER);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedRegisteredEmailAddress, responseEntity.getBody());
    }

    @Test
    @DisplayName("Throws 404 when registered email not found")
    void testRegisteredEmailAddressNotFound() throws RegisteredEmailAddressNotFoundException {

        when(registeredEmailAddressService.getRegisteredEmailAddress(VALID_COMPANY_NUMBER)).thenThrow(new RegisteredEmailAddressNotFoundException("No registered email address found for company " + VALID_COMPANY_NUMBER));

        ResponseEntity<RegisteredEmailAddressJson> response = registeredEmailAddressController.getRegisteredEmailAddress(VALID_COMPANY_NUMBER);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    //MockMvc tests for HTTP web layer.
    @Test
    void getRegisteredEmailAddress_success() throws Exception {
        RegisteredEmailAddressJson registeredEmailAddressJson = new RegisteredEmailAddressJson();
        registeredEmailAddressJson.setRegisteredEmailAddress("test@example.com");

        when(registeredEmailAddressService.getRegisteredEmailAddress(VALID_COMPANY_NUMBER)).thenReturn(registeredEmailAddressJson);

        mockMvc.perform(get("/company/{companyNumber}/registered-email-address", VALID_COMPANY_NUMBER).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.registered_email_address").value("test@example.com"));

        verify(registeredEmailAddressService, times(1)).getRegisteredEmailAddress(VALID_COMPANY_NUMBER);
    }

    @Test
    void getRegisteredEmailAddress_notFound() throws Exception {
        when(registeredEmailAddressService.getRegisteredEmailAddress(VALID_COMPANY_NUMBER)).thenThrow(RegisteredEmailAddressNotFoundException.class);

        mockMvc.perform(get("/company/{companyNumber}/registered-email-address", VALID_COMPANY_NUMBER).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(registeredEmailAddressService, times(1)).getRegisteredEmailAddress(VALID_COMPANY_NUMBER);
    }
}