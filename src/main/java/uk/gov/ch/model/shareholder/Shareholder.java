package uk.gov.ch.model.shareholder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Shareholder {

    @Id
    @Column(name = "shareholder_id")
    @JsonIgnore
    private Long shareholderId;
    @Column(name = "fore_name_1")
    @JsonProperty("fore_name_1")
    private String forename1;
    @Column(name = "fore_name_2")
    @JsonProperty("fore_name_2")
    private String forename2;
    @Column(name = "surname")
    private String surname;
    @Column(name = "shares")
    private long shares;
    @Column(name = "share_class_type_id")
    @JsonProperty("share_class_type_id")
    private long shareClassTypeId;
    @Column(name = "currency_type_id")
    @JsonProperty("currency_type_id")
    private long currencyTypeId;

    public Long getShareholderId() {
        return shareholderId;
    }

    public void setShareholderId(Long shareholderId) {
        this.shareholderId = shareholderId;
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

    public long getShares() {
        return shares;
    }

    public void setShares(long shares) {
        this.shares = shares;
    }

    public long getShareClassTypeId() {
        return shareClassTypeId;
    }

    public void setShareClassTypeId(long shareClassTypeId) {
        this.shareClassTypeId = shareClassTypeId;
    }

    public long getCurrencyTypeId() {
        return currencyTypeId;
    }

    public void setCurrencyTypeId(long currencyTypeId) {
        this.currencyTypeId = currencyTypeId;
    }

}
