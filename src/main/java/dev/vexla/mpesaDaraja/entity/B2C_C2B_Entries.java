package dev.vexla.mpesaDaraja.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Entity
@Table(name = "b2c_c2b_entries")
@Setter
@Getter
public class B2C_C2B_Entries {
    @Id
    @Column(name = "internal_id", nullable = false)
    private String internalId;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "transaction_id", unique = true)
    private String transactionId;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "conversation_id", unique = true)
    private String conversationId;

    @Column(name = "originator_conversation_id", unique = true)
    private String originatorConversationId;

    @Temporal(TemporalType.DATE)
    @Column(name = "entry_date")
    private Date entryDate;

    @Column(name = "result_code")
    private String resultCode;

    @Column(name = "raw_callback_payload_response")
    private String rawCallbackPayloadResponse;

}