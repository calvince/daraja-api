package dev.vexla.mpesaDaraja.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "stk_push_entries")
public class StkPush_Entries {
    @Id
    @Column(name = "internal_id", nullable = false)
    private String internalId;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "amount")
    private String amount;

    @Column(name = "merchant_request_id", unique = true)
    private String merchantRequestID;

    @Column(name = "checkout_request_id", unique = true)
    private String checkoutRequestID;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "entry_date")
    private Date entryDate;

    @Column(name = "result_code")
    private String resultCode;

    @Column(name = "raw_callback_payload_response")
    @JdbcTypeCode(SqlTypes.JSON)
    private Object rawCallbackPayloadResponse;

}