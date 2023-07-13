package dev.vexla.mpesaDaraja.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StkPushRequest{

	@JsonProperty("TransactionType")
	private String transactionType;

	@JsonProperty("Amount")
	private String amount;

	@JsonProperty("CallBackURL")
	private String callBackURL;

	@JsonProperty("PhoneNumber")
	private String phoneNumber;

	@JsonProperty("PartyA")
	private String partyA;

	@JsonProperty("PartyB")
	private String partyB;

	@JsonProperty("AccountReference")
	private String accountReference;

	@JsonProperty("TransactionDesc")
	private String transactionDesc;

	@JsonProperty("BusinessShortCode")
	private String businessShortCode;

	@JsonProperty("Timestamp")
	private String timestamp;

	@JsonProperty("Password")
	private String password;
}