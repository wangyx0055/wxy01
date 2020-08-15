package com.longersec.blj.domain.DTO;

import java.util.Objects;

public class DeviceGroup {
    private Integer dgroup_id;
    private String dgroup_name;

    public Integer getDgroup_id() {
        return dgroup_id;
    }

    public void setDgroup_id(Integer dgroup_id) {
        this.dgroup_id = dgroup_id;
    }

    public String getDgroup_name() {
        return dgroup_name;
    }

    public void setDgroup_name(String dgroup_name) {
        this.dgroup_name = dgroup_name;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "dgroup_id=" + dgroup_id +
                ", dgroup_name='" + dgroup_name + '\'' +
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
        DeviceGroup that = (DeviceGroup) o;
        return Objects.equals(dgroup_id, that.dgroup_id) &&
                Objects.equals(dgroup_name, that.dgroup_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dgroup_id, dgroup_name);
    }
}
