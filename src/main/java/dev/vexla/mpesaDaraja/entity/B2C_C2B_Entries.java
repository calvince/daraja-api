package dev.vexla.mpesaDaraja.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
    private String amount;

    @Column(name = "conversation_id", unique = true)
    private String conversationId;

    @Column(name = "originator_conversation_id", unique = true)
    private String originatorConversationId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "entry_date")
    private Date entryDate;

    @Column(name = "result_code")
    private String resultCode;

    @Column(name = "bill_ref_number")
    private String billRefNumber;

    @Column(name = "raw_callback_payload_response")
    @JdbcTypeCode(SqlTypes.JSON)
    private Object rawCallbackPayloadResponse;

}