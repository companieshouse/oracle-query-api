package uk.gov.ch.model.emergencyauthcode.jsondatamodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CorporateBodyAppointments {

    @JsonProperty("items_per_page")
    private int itemsPerPage;

    @JsonProperty("start_index")
    private int startIndex;

    @JsonProperty("total_results")
    private int totalResults;

    @JsonProperty("items")
    private List<CorporateBodyAppointment> items;

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

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<CorporateBodyAppointment> getItems() {
        return items;
    }

    public void setItems(List<CorporateBodyAppointment> items) {
        this.items = items;
    }
}
