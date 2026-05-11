package uk.gov.ch.model.officer.bankrupt;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "SCOTTISH_BANKRUPT_OFFICER")
public class ScottishBankruptOfficerDataModel extends ScottishBankruptOfficerBase {

	@JsonProperty("addressTown")
	public String getTown() {
		return super.getTown();
	}

	@JsonProperty("addressCounty")
	public String getCounty() {
		return super.getCounty();
	}

	@JsonProperty("addressPostcode")
	public String getPostcode() {
		return super.getPostcode();
	}
}
