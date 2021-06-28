package uk.gov.ch.model.capital;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatementOfCapital {

    @JsonProperty("id")
    private String id;

    @JsonProperty("class_of_shares")
    private String classOfShares;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("number_allotted")
    private int numberAllotted;

    @JsonProperty("aggregate_nominal_value")
    private int aggregateNominalValue;

    @JsonProperty("total_currency")
    private String totalCurrency;

    @JsonProperty("total_number_of_shares")
    private int totalNumberOfShares;

    @JsonProperty("total_aggregate_nominal_value")
    private int totalAggregateNominalValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getNumberAllotted() {
        return numberAllotted;
    }

    public void setNumberAllotted(int numberAllotted) {
        this.numberAllotted = numberAllotted;
    }

    public int getAggregateNominalValue() {
        return aggregateNominalValue;
    }

    public void setAggregateNominalValue(int aggregateNominalValue) {
        this.aggregateNominalValue = aggregateNominalValue;
    }

    public String getTotalCurrency() {
        return totalCurrency;
    }

    public void setTotalCurrency(String totalCurrency) {
        this.totalCurrency = totalCurrency;
    }

    public int getTotalNumberOfShares() {
        return totalNumberOfShares;
    }

    public void setTotalNumberOfShares(int totalNumberOfShares) {
        this.totalNumberOfShares = totalNumberOfShares;
    }

    public int getTotalAggregateNominalValue() {
        return totalAggregateNominalValue;
    }

    public void setTotalAggregateNominalValue(int totalAggregateNominalValue) {
        this.totalAggregateNominalValue = totalAggregateNominalValue;
    }
}
