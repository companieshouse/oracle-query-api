package uk.gov.ch.model.emergencyauthcode.jsonDataModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CorporateBodyAppointmentDateOfBirth {

    @JsonProperty("month")
    private String month;

    @JsonProperty("year")
    private String year;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
