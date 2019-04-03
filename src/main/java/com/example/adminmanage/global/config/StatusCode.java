package com.example.adminmanage.global.config;

public class StatusCode {
    public static final String LOGIN_SUCCESS         = "10000";
    public static final String LOGIN_NEGATIVE_STATUS = "10001";
    public static final String LOGIN_WRONG_USERNAME  = "10002";
    public static final String LOGIN_WRONG_PASSWORD  = "10003";
    public static final String LOGIN_TIMEOUT         = "10004";

    public static final String COMMIT_SUCCESS        = "20000";
    public static final String COMMIT_FAIL           = "20001";
    public static final String COMMIT_TIMEOUT        = "20002";
    public static final String COMMIT_WRONG_ARGS     = "20003";
}
