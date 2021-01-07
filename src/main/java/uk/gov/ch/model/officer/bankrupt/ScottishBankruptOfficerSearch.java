package uk.gov.ch.model.officer.bankrupt;

import org.springframework.stereotype.Component;

/**
 * Criteria for searching for bankrupt officers
 * (filtering and pagination).
 */
@Component
public class ScottishBankruptOfficerSearch {

    private int startIndex;
    private int itemsPerPage;
    private ScottishBankruptOfficerSearchFilters filters;

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public ScottishBankruptOfficerSearchFilters getFilters() {
        return filters;
    }

    public void setFilters(ScottishBankruptOfficerSearchFilters filters) {
        this.filters = filters;
    }
}
