package uk.gov.ch.model.emergencyauthcode.jsondatamodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CorporateBodyEFilingStatus {

    @JsonProperty("efiling_found_in_period")
    private Boolean efilingFoundInPeriod;

    public Boolean getEfilingFoundInPeriod() {
        return efilingFoundInPeriod;
    }

    public void setEfilingFoundInPeriod(Boolean efilingFoundInPeriod) {
        this.efilingFoundInPeriod = efilingFoundInPeriod;
    }
}
