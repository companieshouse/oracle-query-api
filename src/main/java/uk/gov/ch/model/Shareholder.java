package uk.gov.ch.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.ALWAYS)
public class Shareholder {

    private String forename1;
    private String forename2;
    private String surname;
    private int addressId;
    private int shares;
    private int shareClassTypeId;
    private int currencyTypeId;

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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public int getShareClassTypeId() {
        return shareClassTypeId;
    }

    public void setShareClassTypeId(int shareClassTypeId) {
        this.shareClassTypeId = shareClassTypeId;
    }

    public int getCurrencyTypeId() {
        return currencyTypeId;
    }

    public void setCurrencyTypeId(int currencyTypeId) {
        this.currencyTypeId = currencyTypeId;
    }

}
