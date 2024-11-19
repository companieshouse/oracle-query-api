package uk.gov.ch.model.corporatebody.sqldatamodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SicCodes {

    @JsonProperty("sic_1")
    private String sic1;

    @JsonProperty("sic_2")
    private String sic2;

    @JsonProperty("sic_3")
    private String sic3;

    @JsonProperty("sic_4")
    private String sic4;

    @JsonProperty("sic_5")
    private String sic5;

    public String getSic1() {
        return sic1;
    }

    public void setSic1(String sic1) {
        this.sic1 = sic1;
    }

    public String getSic2() {
        return sic2;
    }

    public void setSic2(String sic2) {
        this.sic2 = sic2;
    }

    public String getSic3() {
        return sic3;
    }

    public void setSic3(String sic3) {
        this.sic3 = sic3;
    }

    public String getSic4() {
        return sic4;
    }

    public void setSic4(String sic4) {
        this.sic4 = sic4;
    }

    public String getSic5() {
        return sic5;
    }

    public void setSic5(String sic5) {
        this.sic5 = sic5;
    }
}
