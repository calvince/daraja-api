CREATE TABLE b2c_c2b_entries
(
    internal_id                   VARCHAR(255) NOT NULL,
    transaction_type              VARCHAR(255) NULL,
    transaction_id                VARCHAR(255) NULL,
    msisdn                        VARCHAR(255) NULL,
    amount                        VARCHAR(255) NULL,
    conversation_id               VARCHAR(255) NULL,
    originator_conversation_id    VARCHAR(255) NULL,
    entry_date                    datetime     NULL,
    result_code                   VARCHAR(255) NULL,
    bill_ref_number               VARCHAR(255) NULL,
    raw_callback_payload_response JSON         NULL,
    CONSTRAINT pk_b2c_c2b_entries PRIMARY KEY (internal_id)
);

ALTER TABLE b2c_c2b_entries
    ADD CONSTRAINT uc_b2c_c2b_entries_conversation UNIQUE (conversation_id);

ALTER TABLE b2c_c2b_entries
    ADD CONSTRAINT uc_b2c_c2b_entries_originator_conversation UNIQUE (originator_conversation_id);

ALTER TABLE b2c_c2b_entries
    ADD CONSTRAINT uc_b2c_c2b_entries_transaction UNIQUE (transaction_id);
