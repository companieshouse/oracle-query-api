package uk.gov.ch.model.shareholder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import uk.gov.ch.model.common.NameBase;

@Entity
@AttributeOverride(name = "forename1", column = @Column(name = "fore_name_1"))
@AttributeOverride(name = "forename2", column = @Column(name = "fore_name_2"))
@AttributeOverride(name = "surname", column = @Column(name = "surname"))
public class Shareholder extends NameBase {

    @Id
    @Column(name = "shareholder_id")
    @JsonIgnore
    private Long shareholderId;
    @Column(name = "shares")
    private long shares;
    @Column(name = "class_of_shares")
    @JsonProperty("class_of_shares")
    private String classOfShares;
    @Column(name = "currency")
    private String currency;

    public Long getShareholderId() {
        return shareholderId;
    }

    public void setShareholderId(Long shareholderId) {
        this.shareholderId = shareholderId;
    }

    public long getShares() {
        return shares;
    }

    public void setShares(long shares) {
        this.shares = shares;
    }

    public String getClassOfShares() {
        return classOfShares;
    }

    public void setClassOfShares(String classOfShares) {
        this.classOfShares = classOfShares;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
