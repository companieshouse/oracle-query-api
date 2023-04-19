package uk.gov.ch.controller.update;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.ch.exception.ManagingOfficerCountNotFoundException;
import uk.gov.ch.model.update.OverseasEntityManagingOfficerData;
import uk.gov.ch.service.update.impl.OverseasEntityManagingOfficersServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class OverseasEntityManagingOfficersControllerTest {

    private static final String COMPANY_NUMBER = "OE123456";
    private static final String INVALID_COMPANY_NUMBER = "A";
    @Mock
    private OverseasEntityManagingOfficersServiceImpl overseasEntityManagingOfficersService;

    @InjectMocks
    private OverseasEntityManagingOfficersController overseasEntityManagingOfficersController;

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

}
