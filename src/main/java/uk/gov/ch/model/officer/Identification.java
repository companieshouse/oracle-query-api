package uk.gov.ch.model.officer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Identification {

    @JsonProperty("EEA")
    private OfficerIdentification eea;
    
    @JsonProperty("non_EEA")
    private OfficerIdentification nonEea;
    
    @JsonProperty("UK_limited_company")
    private OfficerIdentification ukLimitedCompany;
    
    @JsonProperty("other_corporate_body_or_firm")
    private OfficerIdentification otherCorporateBodyOrFirm;

    public OfficerIdentification getEea() {
        return eea;
    }

    public void setEea(OfficerIdentification eea) {
        this.eea = eea;
    }

    public OfficerIdentification getNonEea() {
        return nonEea;
    }

    public void setNonEea(OfficerIdentification nonEea) {
        this.nonEea = nonEea;
    }

    public OfficerIdentification getUkLimitedCompany() {
        return ukLimitedCompany;
    }

    public void setUkLimitedCompany(OfficerIdentification ukLimitedCompany) {
        this.ukLimitedCompany = ukLimitedCompany;
    }

    public OfficerIdentification getOtherCorporateBodyOrFirm() {
        return otherCorporateBodyOrFirm;
    }

    public void setOtherCorporateBodyOrFirm(OfficerIdentification otherCorporateBodyOrFirm) {
        this.otherCorporateBodyOrFirm = otherCorporateBodyOrFirm;
    }
    
}
