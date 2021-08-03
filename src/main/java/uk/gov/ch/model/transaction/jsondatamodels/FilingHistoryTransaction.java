package uk.gov.ch.model.transaction.jsondatamodels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilingHistoryTransaction {

    @JsonProperty("entity_id")
    private Long entityId;

    @JsonProperty("receive_date")
    private String receiveDate;

    @JsonProperty("description")
    private String description;

    @JsonProperty("barcode")
    private String barcode;

    @JsonProperty("document_id")
    private String documentId;

    @JsonProperty("form_type")
    private String formType;

    @JsonProperty("category")
    private String category;

    @JsonProperty("child")
    private List<FilingHistoryTransaction> child;

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<FilingHistoryTransaction> getChild() {
        return child;
    }

    public void setChild(List<FilingHistoryTransaction> child) {
        this.child = child;
    }
}
