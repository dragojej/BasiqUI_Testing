package com.basiq.ui.tests.resources;

public class PageUrl {

    public static final String CONSENT_URL = "http://consent.basiq.io/home?token=";

    public static String getConsentUrl(String token) {
        return String.format(CONSENT_URL+"%s", token);
    }

}
