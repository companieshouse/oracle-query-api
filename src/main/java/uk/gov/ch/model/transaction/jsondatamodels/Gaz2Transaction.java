package uk.gov.ch.model.transaction.jsondatamodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Gaz2Transaction {

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("status_type")
    private String statusType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    @Override
    public String toString() {
        return "Gaz2Transaction{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", statusType='" + statusType + '\'' +
                '}';
    }
}
