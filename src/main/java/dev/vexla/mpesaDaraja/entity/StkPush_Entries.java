package dev.vexla.mpesaDaraja.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "stk_push_entries")
public class StkPush_Entries {
    @Id
    @Column(name = "internal_id", nullable = false)
    private String internalId;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "merchant_request_id", unique = true)
    private String merchantRequestId;

    @Column(name = "checkout_request_id", unique = true)
    private String checkoutRequestId;

    @Temporal(TemporalType.DATE)
    @Column(name = "entry_date")
    private Date entryDate;

    @Column(name = "result_code")
    private String resultCode;

    @Column(name = "raw_callback_payload_response")
    private String rawCallbackPayloadResponse;

}