package uk.gov.ch.service.officer.bankrupt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearch;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchDataModel;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchResults;
import uk.gov.ch.repository.officers.ScottishBankruptOfficersRepository;
import uk.gov.ch.service.officer.bankrupt.impl.ScottishBankruptOfficerService;
import uk.gov.ch.transformers.officer.bankrupt.BankruptOfficersTransformer;

@ExtendWith(MockitoExtension.class)
public class ScottishBankruptOfficerServiceTest {

    @Mock
    private ScottishBankruptOfficersRepository repo;

    @Mock
    private BankruptOfficersTransformer transformer;

    @Mock
    private Page<ScottishBankruptOfficerSearchDataModel> page;

    @InjectMocks
    private ScottishBankruptOfficerService service;

    private static final String FORENAME ="forename";
    private static final String SURNAME ="surname";
    private static final String DATE_OF_BIRTH="2020-01-01";
    private static final String POSTCODE="postcode";


    @Test
    @DisplayName("Search for bankrupt officer")
    public void testScottishBankruptSearch(){
        when(repo.findScottishBankruptOfficers(eq(FORENAME),eq(SURNAME),eq(DATE_OF_BIRTH),eq(POSTCODE),any(Pageable.class))).thenReturn(page);

        ScottishBankruptOfficerSearchResults expectedResults = new ScottishBankruptOfficerSearchResults();
        when(transformer.convert(page)).thenReturn(expectedResults);

        ScottishBankruptOfficerSearch search = new ScottishBankruptOfficerSearch();
        search.setForename1(FORENAME);
        search.setSurname(SURNAME);
        search.setDateOfBirth(DATE_OF_BIRTH);
        search.setPostcode(POSTCODE);
        ScottishBankruptOfficerSearchResults results = service.getScottishBankruptOfficers(search);

        assertEquals(expectedResults, results);

    }

}
