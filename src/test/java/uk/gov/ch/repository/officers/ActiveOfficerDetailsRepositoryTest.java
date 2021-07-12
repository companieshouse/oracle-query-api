package uk.gov.ch.repository.officers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import uk.gov.ch.exception.NoActiveOfficersFoundException;
import uk.gov.ch.model.officer.active.ActiveOfficerDetails;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActiveOfficerDetailsRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ActiveOfficerDetailsRepository activeOfficerDetailsRepository;

    private static final String COMPANY_NUMBER = "12345678";

    @Test
    @DisplayName("Get Active Officer - Company With Active Officers")
    void getActiveOfficerDetailsTest() throws NoActiveOfficersFoundException {
        List<ActiveOfficerDetails> expectedList = new ArrayList<>();
        expectedList.add(new ActiveOfficerDetails());
        when(jdbcTemplate.query(eq(ActiveOfficerDetailsRepository.OFFICER_DETAILS_SQL), any(PreparedStatementSetter.class),
                any(BeanPropertyRowMapper.class))).thenReturn(expectedList);
        List<ActiveOfficerDetails> result = activeOfficerDetailsRepository.getActiveOfficerDetails(COMPANY_NUMBER);
        assertEquals(expectedList, result);
    }

    @Test
    @DisplayName("Get Active Officer - Company With No Active Officers")
    void getActiveOfficerDetailsOfCompanyWithNoneTest() throws NoActiveOfficersFoundException {
        List<ActiveOfficerDetails> expectedList = new ArrayList<>();
        when(jdbcTemplate.query(eq(ActiveOfficerDetailsRepository.OFFICER_DETAILS_SQL), any(PreparedStatementSetter.class),
                any(BeanPropertyRowMapper.class))).thenReturn(expectedList);
        List<ActiveOfficerDetails> result = activeOfficerDetailsRepository.getActiveOfficerDetails(COMPANY_NUMBER);
        assertEquals(expectedList, result);
    }

    @Test
    @DisplayName("Get Active Officer - Empty Data Set")
    void getActiveOfficerDetailsEmptyDataSetTest() {
        when(jdbcTemplate.query(eq(ActiveOfficerDetailsRepository.OFFICER_DETAILS_SQL), any(PreparedStatementSetter.class),
                any(BeanPropertyRowMapper.class))).thenThrow(EmptyResultDataAccessException.class);
        assertThrows(NoActiveOfficersFoundException.class, () -> activeOfficerDetailsRepository.getActiveOfficerDetails(COMPANY_NUMBER));
    }
}
