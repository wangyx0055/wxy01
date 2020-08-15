package com.longersec.blj.domain;

public class GroupDeviceAccount {
    private Integer group_id;

    private Integer device_account_id;

    @Override
    public String toString() {
        return "GroupDeviceAccount{" +
                "group_id=" + group_id +
                ", device_account_id=" + device_account_id +
                '}';
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public Integer getDevice_account_id() {
        return device_account_id;
    }

    public void setDevice_account_id(Integer device_account_id) {
        this.device_account_id = device_account_id;
    }
}