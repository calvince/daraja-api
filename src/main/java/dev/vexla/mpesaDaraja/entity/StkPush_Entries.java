package dev.vexla.mpesaDaraja.entity;

import dev.vexla.mpesaDaraja.dto.ReferenceItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "stk_push_entries")
public class StkPush_Entries {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "internal_id", nullable = false)
    private String internalId;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "amount")
    private String amount;

    @Column(name = "merchant_request_id", unique = true)
    private String merchantRequestId;

    @Column(name = "checkout_request_id", unique = true)
    private String checkoutRequestId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "entry_date")
    private Date entryDate;

    @Column(name = "result_code")
    private String resultCode;

    @Column(name = "raw_callback_payload_response")
    private Object rawCallbackPayloadResponse;

}