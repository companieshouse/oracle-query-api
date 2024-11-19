package uk.gov.ch.model.corporatebody.sqldatamodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfirmationStatementDates {

    @JsonProperty("next_due")
    private String nextDue;

    @JsonProperty("next_made_up_to")
    private String nextMadeUpTo;

    public String getNextDue() {
        return nextDue;
    }

    public void setNextDue(String nextDue) {
        this.nextDue = nextDue;
    }

    public String getNextMadeUpTo() {
        return nextMadeUpTo;
    }

    public void setNextMadeUpTo(String nextMadeUpTo) {
        this.nextMadeUpTo = nextMadeUpTo;
    }
}
