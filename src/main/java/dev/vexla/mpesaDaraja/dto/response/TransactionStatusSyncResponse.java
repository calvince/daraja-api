package dev.vexla.mpesaDaraja.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionStatusSyncResponse{

	@JsonProperty("ConversationID")
	private String conversationID;

	@JsonProperty("ResponseCode")
	private String responseCode;

	@JsonProperty("OriginatorConversationID")
	private String originatorConversationID;

	@JsonProperty("ResponseDescription")
	private String responseDescription;

	public String getConversationID(){
		return conversationID;
	}

	public String getResponseCode(){
		return responseCode;
	}

	public String getOriginatorConversationID(){
		return originatorConversationID;
	}

	public String getResponseDescription(){
		return responseDescription;
	}
}