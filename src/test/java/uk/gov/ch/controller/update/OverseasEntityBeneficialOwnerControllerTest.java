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
import uk.gov.ch.exception.BeneficialOwnerCountNotFoundException;
import uk.gov.ch.model.update.OverseasEntityBeneficialOwner;
import uk.gov.ch.service.update.impl.OverseasEntityBeneficialOwnerServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(OverseasEntityBeneficialOwnerController.class)
class OverseasEntityBeneficialOwnerControllerTest {
    private static final String COMPANY_NUMBER = "OE123456";
    private static final String INVALID_COMPANY_NUMBER = "OE12#456";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OverseasEntityBeneficialOwnerServiceImpl overseasEntityBeneficialOwnerService;

    private OverseasEntityBeneficialOwnerController overseasEntityBeneficialOwnerController;

    @BeforeEach
    void setUp() {
        overseasEntityBeneficialOwnerController = new OverseasEntityBeneficialOwnerController(overseasEntityBeneficialOwnerService);
    }

    @Test
    @DisplayName("Get beneficial owners - overseas entity with no beneficial owners")
    void testGetOverseasEntityBeneficialOwnersMethodReturnsCountError() throws BeneficialOwnerCountNotFoundException {
        when(overseasEntityBeneficialOwnerService.getBeneficialOwners(COMPANY_NUMBER))
                .thenThrow(new BeneficialOwnerCountNotFoundException("No beneficial owners were found."));

        ResponseEntity responseEntity = overseasEntityBeneficialOwnerController
                .getOverseasEntityBeneficialOwners(COMPANY_NUMBER);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Get beneficial owners - beneficial owners returned for overseas entity")
    void testGetOverseasEntityBeneficialOwnersMethodReturnsExpectedData() throws BeneficialOwnerCountNotFoundException {
        List<OverseasEntityBeneficialOwner> beneficialOwners = new ArrayList();
        beneficialOwners.add(new OverseasEntityBeneficialOwner());
        beneficialOwners.add(new OverseasEntityBeneficialOwner());

        when(overseasEntityBeneficialOwnerService.getBeneficialOwners(COMPANY_NUMBER)).thenReturn(beneficialOwners);

        ResponseEntity responseEntity = overseasEntityBeneficialOwnerController
                .getOverseasEntityBeneficialOwners(COMPANY_NUMBER);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(beneficialOwners, responseEntity.getBody());
    }

    @Test
    @DisplayName("Get beneficial owners - invalid overseas entity number")
    void testGetOverseasEntityBeneficialOwnersInvalidCompanyNumber() throws Exception {

        mockMvc.perform(get("/overseas-entity/{companyNumber}/beneficial-owners", INVALID_COMPANY_NUMBER))
            .andExpect(status().isBadRequest());
    }

}
