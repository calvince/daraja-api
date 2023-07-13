package dev.vexla.mpesaDaraja.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InternalStkPushRequest{

	@JsonProperty("Amount")
	private String amount;

	@JsonProperty("PhoneNumber")
	private String phoneNumber;
}