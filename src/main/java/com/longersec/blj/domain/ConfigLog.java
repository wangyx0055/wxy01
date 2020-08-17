package com.longersec.blj.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;

public class ConfigLog {
    private Integer id;

    @NotNull(message = "请输入链接地址")
    @NotEmpty(message = "请输入链接地址")
    private String log_manage;

    @NotNull(message = "请输入链接名称")
    @NotEmpty(message = "请输入链接名称")
    private String name;

    @NotNull(message = "请输入排序")
    private Integer sort;

    public ConfigLog() {
        super();
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getLog_manage() {
        return log_manage;
    }
    public void setLog_manage(String log_manage) {
        this.log_manage = log_manage;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "ConfigLog{" +
                "id=" + id +
                ", log_manage='" + log_manage + '\'' +
                ", name='" + name + '\'' +
                ", sort=" + sort +
                '}';
    }
}
