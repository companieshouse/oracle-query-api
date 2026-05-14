package uk.gov.ch.model.officer.bankrupt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ScottishBankruptOfficerSearchResultsTest {

    private ScottishBankruptOfficerSearchResults scottishBankruptOfficerSearchResultsUnderTest;

    @BeforeEach
    void setUp() {
        scottishBankruptOfficerSearchResultsUnderTest = new ScottishBankruptOfficerSearchResults();
    }

    @Test
    void testItemsPerPageGetterAndSetter() {
        final int itemsPerPage = 0;
        scottishBankruptOfficerSearchResultsUnderTest.setItemsPerPage(itemsPerPage);
        assertThat(scottishBankruptOfficerSearchResultsUnderTest.getItemsPerPage()).isEqualTo(itemsPerPage);
    }

    @Test
    void testStartIndexGetterAndSetter() {
        final int startIndex = 0;
        scottishBankruptOfficerSearchResultsUnderTest.setStartIndex(startIndex);
        assertThat(scottishBankruptOfficerSearchResultsUnderTest.getStartIndex()).isEqualTo(startIndex);
    }

    @Test
    void testTotalResultsGetterAndSetter() {
        final long totalResults = 0L;
        scottishBankruptOfficerSearchResultsUnderTest.setTotalResults(totalResults);
        assertThat(scottishBankruptOfficerSearchResultsUnderTest.getTotalResults()).isEqualTo(totalResults);
    }

    @Test
    void testItemsGetterAndSetter() {
        final List<ScottishBankruptOfficerSearchResult> items = List.of(new ScottishBankruptOfficerSearchResult());
        scottishBankruptOfficerSearchResultsUnderTest.setItems(items);
        assertThat(scottishBankruptOfficerSearchResultsUnderTest.getItems()).isEqualTo(items);
    }
}
