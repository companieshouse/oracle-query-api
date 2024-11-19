package uk.gov.ch.model.corporatebody.sqldatamodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnnualReturnDates {

    @JsonProperty("latest_made_up_to")
    private String latestMadeUpTo;

    public String getLatestMadeUpTo() {
        return latestMadeUpTo;
    }

    public void setLatestMadeUpTo(String latestMadeUpTo) {
        this.latestMadeUpTo = latestMadeUpTo;
    }
}
