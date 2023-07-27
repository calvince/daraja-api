CREATE TABLE b2c_c2b_entries
(
    internal_id                   VARCHAR(255) NOT NULL,
    transaction_type              VARCHAR(255) NULL,
    transaction_id                VARCHAR(255) NULL,
    msisdn                        VARCHAR(255) NULL,
    amount                        BIGINT       NULL,
    conversation_id               VARCHAR(255) NULL,
    originator_conversation_id    VARCHAR(255) NULL,
    entry_date                    date         NULL,
    result_code                   VARCHAR(255) NULL,
    raw_callback_payload_response VARCHAR(255) NULL,
    CONSTRAINT pk_b2c_c2b_entries PRIMARY KEY (internal_id)
);

ALTER TABLE b2c_c2b_entries
    ADD CONSTRAINT uc_b2c_c2b_entries_conversation UNIQUE (conversation_id);

ALTER TABLE b2c_c2b_entries
    ADD CONSTRAINT uc_b2c_c2b_entries_originatorconversation UNIQUE (originator_conversation_id);

ALTER TABLE b2c_c2b_entries
    ADD CONSTRAINT uc_b2c_c2b_entries_transaction UNIQUE (transaction_id);

CREATE TABLE stk_push_entries
(
    internal_id                   VARCHAR(255) NOT NULL,
    transaction_id                VARCHAR(255) NULL,
    msisdn                        VARCHAR(255) NULL,
    amount                        BIGINT       NULL,
    merchant_request_id           VARCHAR(255) NULL,
    checkout_request_id           VARCHAR(255) NULL,
    entry_date                    date         NULL,
    result_code                   VARCHAR(255) NULL,
    raw_callback_payload_response VARCHAR(255) NULL,
    CONSTRAINT pk_stk_push_entries PRIMARY KEY (internal_id)
);

ALTER TABLE stk_push_entries
    ADD CONSTRAINT uc_stk_push_entries_checkout_request UNIQUE (checkout_request_id);

ALTER TABLE stk_push_entries
    ADD CONSTRAINT uc_stk_push_entries_merchantrequest UNIQUE (merchant_request_id);