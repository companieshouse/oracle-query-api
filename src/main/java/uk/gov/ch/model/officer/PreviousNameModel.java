package uk.gov.ch.model.officer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PreviousNameModel {
    
    @JsonProperty("previous_surname")
    private String previousSurname;
    
    @JsonProperty("previous_forename")
    private String previousForename;
    
    @JsonProperty("previous_timestamp")
    private String previousTimestamp;

    public String getPreviousSurname() {
        return previousSurname;
    }

    public void setPreviousSurname(String previousSurname) {
        this.previousSurname = previousSurname;
    }

    public String getPreviousForename() {
        return previousForename;
    }

    public void setPreviousForename(String previousForename) {
        this.previousForename = previousForename;
    }

    public String getPreviousTimestamp() {
        return previousTimestamp;
    }

    public void setPreviousTimestamp(String previousTimestamp) {
        this.previousTimestamp = previousTimestamp;
    }
}
