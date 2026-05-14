package uk.gov.ch.model.officer.bankrupt;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "SCOTTISH_BANKRUPT_OFFICER")
public class ScottishBankruptOfficerDataModel extends ScottishBankruptOfficerBase {

    @Override
	@JsonProperty("addressTown")
	public String getTown() {
		return super.getTown();
	}

    @Override
	@JsonProperty("addressCounty")
	public String getCounty() {
		return super.getCounty();
	}

    @Override
	@JsonProperty("addressPostcode")
	public String getPostcode() {
		return super.getPostcode();
	}
}
