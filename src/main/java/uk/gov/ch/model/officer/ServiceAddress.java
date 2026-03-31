package uk.gov.ch.model.officer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.ch.model.common.AddressBase;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceAddress extends AddressBase {

    @JsonProperty("usual_country_of_residence")
    private String usualCountryOfResidence;

    public String getUsualCountryOfResidence() {
        return usualCountryOfResidence;
    }

    public void setUsualCountryOfResidence(String usualCountryOfResidence) {
        this.usualCountryOfResidence = usualCountryOfResidence;
    }
}
