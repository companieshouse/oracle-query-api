package uk.gov.ch.controller.update;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.ch.exception.BeneficialOwnerNotFoundException;
import uk.gov.ch.model.update.OverseasEntityBeneficialOwner;
import uk.gov.ch.service.update.impl.OverseasEntityBeneficialOwnerServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OverseasEntityBeneficialOwnerControllerTest {
    private static final String COMPANY_NUMBER = "OE123456";

    @Mock
    private OverseasEntityBeneficialOwnerServiceImpl overseasEntityBeneficialOwnerService;

    @InjectMocks
    private OverseasEntityBeneficialOwnerController overseasEntityBeneficialOwnerController;

    @Test
    @DisplayName("Get beneficial owners - overseas entity with no beneficial owners")
    void testGetOverseasEntityBeneficialOwnersMethodReturnsCountError() throws BeneficialOwnerNotFoundException {
        when(overseasEntityBeneficialOwnerService.getBeneficialOwners(COMPANY_NUMBER))
                .thenThrow(new BeneficialOwnerNotFoundException("No beneficial owners were found."));

        ResponseEntity responseEntity = overseasEntityBeneficialOwnerController
                .getOverseasEntityBeneficialOwners(COMPANY_NUMBER);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Get beneficial owners - beneficial owners returned for overseas entity")
    void testGetOverseasEntityBeneficialOwnersMethodReturnsExpectedData() throws BeneficialOwnerNotFoundException {
        List<OverseasEntityBeneficialOwner> beneficialOwners = new ArrayList();
        beneficialOwners.add(new OverseasEntityBeneficialOwner());
        beneficialOwners.add(new OverseasEntityBeneficialOwner());

        when(overseasEntityBeneficialOwnerService.getBeneficialOwners(COMPANY_NUMBER)).thenReturn(beneficialOwners);

        ResponseEntity responseEntity = overseasEntityBeneficialOwnerController
                .getOverseasEntityBeneficialOwners(COMPANY_NUMBER);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(beneficialOwners, responseEntity.getBody());
    }
}
