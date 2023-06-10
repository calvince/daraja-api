package dev.vexla.mpesaDaraja.shared;

import okhttp3.MediaType;

public class Constants {
    public static final String BASIC_AUTH_STRING = "Basic";
    public static final String AUTHORIZATION_HEADER_STRING = "authorization";
    public static final String CACHE_CONTROL_HEADER = "cache-control";
    public static final String CACHE_CONTROL_HEADER_VALUE = "no-control";
    public static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
}
