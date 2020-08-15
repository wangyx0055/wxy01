package com.longersec.blj.domain.DTO;

import java.util.Objects;

public class UserGroup {
    private Integer group_id;
    private String group_name;

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "group_id=" + group_id +
                ", group_name='" + group_name + '\'' +
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
        UserGroup userGroup = (UserGroup) o;
        return Objects.equals(group_id, userGroup.group_id) &&
                Objects.equals(group_name, userGroup.group_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group_id, group_name);
    }
}
