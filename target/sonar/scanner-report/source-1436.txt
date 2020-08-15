package com.longersec.blj.domain.DTO;

import java.util.List;

public class RoleMenuDTO {
    private Integer id;

    private String name;

    private List<Integer> menu_id;

    @Override
    public String toString() {
        return "RoleMenuDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", menu_id=" + menu_id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(List<Integer> menu_id) {
        this.menu_id = menu_id;
    }
}
