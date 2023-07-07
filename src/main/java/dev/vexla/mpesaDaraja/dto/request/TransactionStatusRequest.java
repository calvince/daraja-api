package dev.vexla.mpesaDaraja.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionStatusRequest{

	@JsonProperty("QueueTimeOutURL")
	private String queueTimeOutURL;

	@JsonProperty("Initiator")
	private String initiator;

	@JsonProperty("Remarks")
	private String remarks;

	@JsonProperty("Occasion")
	private String occasion;

	@JsonProperty("SecurityCredential")
	private String securityCredential;

	@JsonProperty("PartyA")
	private String partyA;

	@JsonProperty("IdentifierType")
	private String identifierType;

	@JsonProperty("Command ID")
	private String commandID;

	@JsonProperty("Transaction ID")
	private String transactionID;

	@JsonProperty("ResultURL")
	private String resultURL;
}