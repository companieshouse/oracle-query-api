package uk.gov.ch.model.common;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@MappedSuperclass
public abstract class NameBase {

    @Column(name = "FORENAME_1")
    @JsonProperty("fore_name_1")
    private String forename1;

    @Column(name = "FORENAME_2")
    @JsonProperty("fore_name_2")
    private String forename2;

    @Column(name = "SURNAME")
    private String surname;

    public String getForename1() {
        return forename1;
    }

    public void setForename1(String forename1) {
        this.forename1 = forename1;
    }

    public String getForename2() {
        return forename2;
    }

    public void setForename2(String forename2) {
        this.forename2 = forename2;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
