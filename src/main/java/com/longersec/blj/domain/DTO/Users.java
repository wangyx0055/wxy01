package com.longersec.blj.domain.DTO;

import java.util.Objects;

public class Users {
    private Integer user_id;
    private String username;
    private String realname;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Override
    public String toString() {
        return "Users{" +
                "user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Users users = (Users) o;
        return Objects.equals(user_id, users.user_id) &&
                Objects.equals(username, users.username) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, username);
    }
}
