package com.longersec.blj.domain;

public class CrontabScriptConfigUser {
    private Integer config_id;
    private Integer user_id;

    @Override
    public String toString() {
        return "CrontabScriptConfigUser{" +
                "config_id='" + config_id + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public Integer getConfig_id() {
        return config_id;
    }

    public void setConfig_id(Integer config_id) {
        this.config_id = config_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
