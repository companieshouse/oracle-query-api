package uk.gov.ch.model.corporatebody.sqldatamodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountingDates {

    @JsonProperty("next_period_start_on")
    private String nextPeriodStartOn;

    @JsonProperty("next_period_end_on")
    private String nextPeriodEndOn;

    @JsonProperty("next_due")
    private String nextDue;

    @JsonProperty("last_period_start_on")
    private String lastPeriodStartOn;

    @JsonProperty("last_period_end_on")
    private String lastPeriodEndOn;

    public String getNextPeriodStartOn() {
        return nextPeriodStartOn;
    }

    public void setNextPeriodStartOn(String nextPeriodStartOn) {
        this.nextPeriodStartOn = nextPeriodStartOn;
    }

    public String getNextPeriodEndOn() {
        return nextPeriodEndOn;
    }

    public void setNextPeriodEndOn(String nextPeriodEndOn) {
        this.nextPeriodEndOn = nextPeriodEndOn;
    }

    public String getNextDue() {
        return nextDue;
    }

    public void setNextDue(String nextDue) {
        this.nextDue = nextDue;
    }

    public String getLastPeriodStartOn() {
        return lastPeriodStartOn;
    }

    public void setLastPeriodStartOn(String lastPeriodStartOn) {
        this.lastPeriodStartOn = lastPeriodStartOn;
    }

    public String getLastPeriodEndOn() {
        return lastPeriodEndOn;
    }

    public void setLastPeriodEndOn(String lastPeriodEndOn) {
        this.lastPeriodEndOn = lastPeriodEndOn;
    }
}
