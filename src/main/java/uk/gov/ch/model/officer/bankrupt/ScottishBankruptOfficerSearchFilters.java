package uk.gov.ch.model.officer.bankrupt;

import org.springframework.stereotype.Component;

/**
 * Filters to apply when searching for bankrupt officers
 */
@Component
public class ScottishBankruptOfficerSearchFilters {

    private String forename1;
    private String surname;
    private String fromDateOfBirth;
    private String toDateOfBirth;
    private String postcode;
    private String alias;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getForename1() {
        return forename1;
    }

    public void setForename1(String forename1) {
        this.forename1 = forename1;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFromDateOfBirth() {
        return fromDateOfBirth;
    }

    public void setFromDateOfBirth(String fromDateOfBirth) {
        this.fromDateOfBirth = fromDateOfBirth;
    }

    public String getToDateOfBirth() {
        return toDateOfBirth;
    }

    public void setToDateOfBirth(String toDateOfBirth) {
        this.toDateOfBirth = toDateOfBirth;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}

