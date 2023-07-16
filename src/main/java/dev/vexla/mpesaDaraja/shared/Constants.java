package dev.vexla.mpesaDaraja.shared;

import okhttp3.MediaType;

public class Constants {
    public static final String BASIC_AUTH_STRING = "Basic";
    public static final String AUTHORIZATION_HEADER_STRING = "Authorization";
    public static final String BEARER_AUTH_STRING = "Bearer";
    public static final String CACHE_CONTROL_HEADER = "cache-control";
    public static final String CACHE_CONTROL_HEADER_VALUE = "no-control";
    public static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    public static final String TRANSACTION_STATUS_QUERY_COMMAND = "TransactionStatusQuery";
    public static final String ACCOUNT_BALANCE_COMMAND = "AccountBalance";
    public static final String TRANSACTION_STATUS_VALUE = "Transaction Status";

    public static final String MSISDN_IDENTIFIER = "1";
    public static final String TILL_NUMBER_IDENTIFIER = "2";
    public static final String SHORT_CODE_IDENTIFIER = "4";
    public static final String CUSTOMER_PAYBILL_ONLINE = "CustomerPayBillOnline";
}
