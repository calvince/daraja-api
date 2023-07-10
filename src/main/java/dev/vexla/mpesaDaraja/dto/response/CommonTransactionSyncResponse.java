package dev.vexla.mpesaDaraja.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommonTransactionSyncResponse {

    @JsonProperty("ConversationID")
    private String conversationID;

    @JsonProperty("ResponseCode")
    private String responseCode;

    @JsonProperty("OriginatorConversationID")
    private String originatorConversationID;

    @JsonProperty("ResponseDescription")
    private String responseDescription;
}
