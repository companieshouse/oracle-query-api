package uk.gov.ch.controller.update;

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
import uk.gov.ch.exception.OverseasEntityEmailAddressNotFoundException;
import uk.gov.ch.model.update.OverseasEntityDataJson;
import uk.gov.ch.service.update.impl.OverseasOverseasEntityDataServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest(OverseasEntityDataController.class)
class OverseasEntityDataControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private OverseasOverseasEntityDataServiceImpl entityDataService;

    @InjectMocks
    private OverseasEntityDataController overseasEntityDataController;

    private static final String VALID_COMPANY_NUMBER = "OE123456";
    private static final String INVALID_COMPANY_NUMBER = "AB123456";

    @Test
    @DisplayName("Get entity email address - email was found")
    void testGetEntityEmailMethodReturnsExpectedEntityData() throws OverseasEntityEmailAddressNotFoundException {
        OverseasEntityDataJson expectedOverseasEntityData = new OverseasEntityDataJson();
        expectedOverseasEntityData.setEmailAddress("frank.sinatra@ratpack.com");

        when(entityDataService.getEntityEmail(VALID_COMPANY_NUMBER)).thenReturn(expectedOverseasEntityData);

        ResponseEntity<OverseasEntityDataJson> responseEntity = overseasEntityDataController.getEntityEmail(VALID_COMPANY_NUMBER);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedOverseasEntityData, responseEntity.getBody());
    }

    @Test
    @DisplayName("Throws 404 when entity email not found")
    void testEntityEmailNotFound() throws OverseasEntityEmailAddressNotFoundException {

        when(entityDataService.getEntityEmail(VALID_COMPANY_NUMBER)).thenThrow(new OverseasEntityEmailAddressNotFoundException("No entity email address found for company " + VALID_COMPANY_NUMBER));

        ResponseEntity<OverseasEntityDataJson> response = overseasEntityDataController.getEntityEmail(VALID_COMPANY_NUMBER);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    //MockMvc tests for HTTP web layer.
    @Test
    void getEntityEmail_success() throws Exception {
        OverseasEntityDataJson overseasEntityDataJson = new OverseasEntityDataJson();
        overseasEntityDataJson.setEmailAddress("test@example.com");

        when(entityDataService.getEntityEmail(VALID_COMPANY_NUMBER)).thenReturn(overseasEntityDataJson);

        mockMvc.perform(get("/overseas-entity/{companyNumber}/entity-data", VALID_COMPANY_NUMBER).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email_address").value("test@example.com"));

        verify(entityDataService, times(1)).getEntityEmail(VALID_COMPANY_NUMBER);
    }

    @Test
    void getEntityEmail_notFound() throws Exception {
        when(entityDataService.getEntityEmail(VALID_COMPANY_NUMBER)).thenThrow(OverseasEntityEmailAddressNotFoundException.class);

        mockMvc.perform(get("/overseas-entity/{companyNumber}/entity-data", VALID_COMPANY_NUMBER).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(entityDataService, times(1)).getEntityEmail(VALID_COMPANY_NUMBER);
    }

    @Test
    void getEntityEmail_invalidCompanyNumber() throws Exception {
        mockMvc.perform(get("/overseas-entity/{companyNumber}/entity-data", INVALID_COMPANY_NUMBER).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(entityDataService, times(0)).getEntityEmail(anyString());
    }
}