package com.basiq.ui.tests.models;

public class Token {

    private String scope;
    private String userid;

    public Token(String scope, String userid) {
        this.scope = scope;
        this.userid = userid;
    }

    public Token(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
