package uk.gov.ch.model.officer.bankrupt;

import java.util.List;

public class ScottishBankruptOfficerSearchResults {

    private int itemsPerPage;
    private int startIndex;
    private long totalResults;
    private List<ScottishBankruptOfficerSearchResult> items;

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public List<ScottishBankruptOfficerSearchResult> getItems() {
        return items;
    }

    public void setItems(List<ScottishBankruptOfficerSearchResult> items) {
        this.items = items;
    }
}
