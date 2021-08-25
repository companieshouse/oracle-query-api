package uk.gov.ch.service.register.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import uk.gov.ch.model.psc.PersonWithSignificantControl;
import uk.gov.ch.model.register.RegisterLocation;
import uk.gov.ch.repository.register.RegisterLocationRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegisterLocationServiceImplTest {

    private static final String COMPANY_NUMBER = "12345678";
    private static final int START_INDEX = 0;
    private static final int ITEMS_PER_PAGE = 10;

    @InjectMocks
    RegisterLocationServiceImpl registerLocationServiceimpl;

    @Mock
    RegisterLocationRepository registerLocationRepository;

    Pageable pageable = PageRequest.of(START_INDEX, ITEMS_PER_PAGE);

    @Test
    @DisplayName("Register Location Service Impl test")
    void testGetRegisterLocation() {
        Page<RegisterLocation> testRegLocationPagess = getMockRegLocationRepo();
        when(registerLocationRepository
                .getRegisterLocation(COMPANY_NUMBER, pageable))
                .thenReturn(testRegLocationPagess);
        List<RegisterLocation> response = registerLocationServiceimpl.getRegisterLocation(COMPANY_NUMBER, pageable);
        assertFalse(response.isEmpty());
        assertEquals(3, response.size());
    }

    private Page<RegisterLocation> getMockRegLocationRepo() {
        List<RegisterLocation> registerLocation = new ArrayList<>();
        registerLocation.add(new RegisterLocation());
        registerLocation.add(new RegisterLocation());
        registerLocation.add(new RegisterLocation());

        return new PageImpl<>(registerLocation, pageable, 2);
    }
}
