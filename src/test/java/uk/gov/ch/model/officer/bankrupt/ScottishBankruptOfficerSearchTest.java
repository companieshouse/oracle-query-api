package uk.gov.ch.model.officer.bankrupt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScottishBankruptOfficerSearchTest {

    private ScottishBankruptOfficerSearch scottishBankruptOfficerSearchUnderTest;

    @BeforeEach
    void setUp() {
        scottishBankruptOfficerSearchUnderTest = new ScottishBankruptOfficerSearch();
    }

    @Test
    void testStartIndexGetterAndSetter() {
        final int startIndex = 0;
        scottishBankruptOfficerSearchUnderTest.setStartIndex(startIndex);
        assertThat(scottishBankruptOfficerSearchUnderTest.getStartIndex()).isEqualTo(startIndex);
    }

    @Test
    void testItemsPerPageGetterAndSetter() {
        final int itemsPerPage = 0;
        scottishBankruptOfficerSearchUnderTest.setItemsPerPage(itemsPerPage);
        assertThat(scottishBankruptOfficerSearchUnderTest.getItemsPerPage()).isEqualTo(itemsPerPage);
    }

    @Test
    void testFiltersGetterAndSetter() {
        final ScottishBankruptOfficerSearchFilters filters = new ScottishBankruptOfficerSearchFilters();
        scottishBankruptOfficerSearchUnderTest.setFilters(filters);
        assertThat(scottishBankruptOfficerSearchUnderTest.getFilters()).isEqualTo(filters);
    }
}
