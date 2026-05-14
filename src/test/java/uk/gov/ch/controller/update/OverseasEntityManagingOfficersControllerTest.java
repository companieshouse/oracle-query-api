package uk.gov.ch.controller.update;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.ch.exception.ManagingOfficerCountNotFoundException;
import uk.gov.ch.model.update.OverseasEntityManagingOfficerData;
import uk.gov.ch.service.update.impl.OverseasEntityManagingOfficersServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(OverseasEntityManagingOfficersController.class)
class OverseasEntityManagingOfficersControllerTest {

    private static final String COMPANY_NUMBER = "OE123456";
    private static final String INVALID_COMPANY_NUMBER = "A";
    private static final String INVALID_OE_NUMBER = "OE12#456";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OverseasEntityManagingOfficersServiceImpl overseasEntityManagingOfficersService;

    private OverseasEntityManagingOfficersController overseasEntityManagingOfficersController;

    @BeforeEach
    void setUp() {
        overseasEntityManagingOfficersController = new OverseasEntityManagingOfficersController(overseasEntityManagingOfficersService);
    }

    @Test
    @DisplayName("Get managing officers - overseas entity with no managing officers")
    void testGetManagingOfficerMethodReturnsCountError() throws ManagingOfficerCountNotFoundException {
        when(overseasEntityManagingOfficersService.getOverseasEntityManagingOfficers(INVALID_COMPANY_NUMBER)).thenThrow(new ManagingOfficerCountNotFoundException("No managing officers were found."));
        ResponseEntity responseEntity = overseasEntityManagingOfficersController.getOverseasEntityManagingOfficers(INVALID_COMPANY_NUMBER);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Get managing officers - managing officers returned for overseas entity")
    void testGetManagingOfficerMethodReturnsExpectedData() throws ManagingOfficerCountNotFoundException {
        List<OverseasEntityManagingOfficerData> managingOfficers = new ArrayList();

        managingOfficers.add(new OverseasEntityManagingOfficerData());
        managingOfficers.add(new OverseasEntityManagingOfficerData());
        when(overseasEntityManagingOfficersService.getOverseasEntityManagingOfficers(COMPANY_NUMBER))
                .thenReturn(managingOfficers);
        ResponseEntity responseEntity = overseasEntityManagingOfficersController.getOverseasEntityManagingOfficers(COMPANY_NUMBER);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(managingOfficers, responseEntity.getBody());
    }

    @Test
    @DisplayName("Get managing officers - invalid overseas entity number")
    void testGetManagingOfficerInvalidCompanyNumber() throws Exception {

        mockMvc.perform(get("/overseas-entity/{companyNumber}/managing-officers", INVALID_OE_NUMBER))
            .andExpect(status().isBadRequest());
    }
}
