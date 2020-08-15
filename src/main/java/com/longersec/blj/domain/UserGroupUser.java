package com.longersec.blj.domain;

public class UserGroupUser {
    private Integer group_id;

    private Integer user_id;

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "UserGroupUser{" +
                "group_id=" + group_id +
                ", user_id=" + user_id +
                '}';
    }
}