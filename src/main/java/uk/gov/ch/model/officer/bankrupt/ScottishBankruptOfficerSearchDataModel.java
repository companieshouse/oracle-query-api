package uk.gov.ch.model.officer.bankrupt;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A class to represent the results of a Scottish
 * bankrupt officer search.
 */
@Entity
public class ScottishBankruptOfficerSearchDataModel {

    @Id
    @Column(name = "EPHEMERAL_KEY")
    private String ephemeralKey;

    @Column(name = "FORENAME_1")
    private String forename1;

    @Column(name = "FORENAME_2")
    private String forename2;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "ADDRESS_LINE_1")
    private String addressLine1;

    @Column(name = "ADDRESS_LINE_2")
    private String addressLine2;

    @Column(name = "ADDRESS_LINE_3")
    private String addressLine3;

    @Column(name = "ADDRESS_TOWN")
    private String addressTown;

    @Column(name = "ADDRESS_COUNTY")
    private String addressCounty;

    @Column(name = "ADDRESS_POSTCODE")
    private String addressPostcode;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    public void setEphemeralKey(String ephemeralKey) {
        this.ephemeralKey = ephemeralKey;
    }

    public String getEphemeralKey() {
        return ephemeralKey;
    }

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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getAddressTown() {
        return addressTown;
    }

    public void setAddressTown(String town) {
        this.addressTown = town;
    }

    public String getAddressCounty() {
        return addressCounty;
    }

    public void setAddressCounty(String county) {
        this.addressCounty = county;
    }

    public String getAddressPostcode() {
        return addressPostcode;
    }

    public void setAddressPostcode(String postcode) {
        this.addressPostcode = postcode;
    }


    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}