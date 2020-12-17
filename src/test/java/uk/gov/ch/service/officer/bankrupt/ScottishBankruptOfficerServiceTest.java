package uk.gov.ch.service.officer.bankrupt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyAppointment;
import uk.gov.ch.model.emergencyauthcode.sqldatamodels.CorporateBodyAppointmentDataModel;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerDataModel;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerDetails;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearch;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchFilters;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchResult;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchResults;
import uk.gov.ch.repository.officers.ScottishBankruptOfficersRepository;
import uk.gov.ch.service.officer.bankrupt.impl.ScottishBankruptOfficerService;
import uk.gov.ch.transformers.officer.bankrupt.BankruptOfficersTransformer;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ScottishBankruptOfficerServiceTest {

    @Mock
    private ScottishBankruptOfficersRepository repo;

    @Mock
    private BankruptOfficersTransformer transformer;

    @Mock
    private Page<ScottishBankruptOfficerDataModel> page;

    @InjectMocks
    private ScottishBankruptOfficerService service;

    private static final int START_INDEX = 1;
    private static final int ITEMS_PER_PAGE = 2;
    private static final String FORENAME = "forename";
    private static final String SURNAME = "surname";
    private static final String DATE_OF_BIRTH = "2020-01-01";
    private static final String POSTCODE = "postcode";
    private static final String EPHEMERALKEY = "0123456";

    @Test
    @DisplayName("Search for bankrupt officers")
    public void testScottishBankruptSearch() {
        when(repo.findScottishBankruptOfficers(eq(FORENAME), eq(SURNAME), eq(DATE_OF_BIRTH), eq(POSTCODE), any(Pageable.class))).thenReturn(page);

        ScottishBankruptOfficerSearchResults expectedResults = new ScottishBankruptOfficerSearchResults();
        when(transformer.convertToSearchResults(page)).thenReturn(expectedResults);

        ScottishBankruptOfficerSearchFilters filters = new ScottishBankruptOfficerSearchFilters();
        filters.setForename1(FORENAME);
        filters.setSurname(SURNAME);
        filters.setDateOfBirth(DATE_OF_BIRTH);
        filters.setPostcode(POSTCODE);

        ScottishBankruptOfficerSearch search = new ScottishBankruptOfficerSearch();
        search.setStartIndex(START_INDEX);
        search.setItemsPerPage(ITEMS_PER_PAGE);
        search.setFilters(filters);

        ScottishBankruptOfficerSearchResults results = service.getScottishBankruptOfficers(search);
        assertEquals(expectedResults, results);

        ArgumentCaptor<Pageable> captor = ArgumentCaptor.forClass(Pageable.class);
        verify(repo).findScottishBankruptOfficers(eq(FORENAME), eq(SURNAME), eq(DATE_OF_BIRTH), eq(POSTCODE), captor.capture());
        Pageable pageable = captor.getValue();
        assertEquals(START_INDEX, pageable.getPageNumber());
        assertEquals(ITEMS_PER_PAGE, pageable.getPageSize());
    }

    @Test
    @DisplayName("Search for bankrupt officer by id")
    public void testScottishBankruptSearchByID() {
        ScottishBankruptOfficerDataModel mockRepoResponse = new ScottishBankruptOfficerDataModel() {{
            setEphemeralKey(EPHEMERALKEY);
        }};
        when(repo.findById(eq(EPHEMERALKEY))).thenReturn(Optional.of(mockRepoResponse));
        ScottishBankruptOfficerDetails mockTransformerResponse = new ScottishBankruptOfficerDetails() {{
            setEphemeralKey(EPHEMERALKEY);

        }};
        when(transformer.convertToDetails(mockRepoResponse)).thenReturn(mockTransformerResponse);

        ScottishBankruptOfficerDetails result = service.getScottishBankruptOfficer(EPHEMERALKEY);
        assertEquals(EPHEMERALKEY, result.getEphemeralKey());


    }
}
