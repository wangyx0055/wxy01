package com.longersec.blj.domain.DTO;


import java.util.ArrayList;
import java.util.List;

public class DepartDTO {
    private Integer id;
    private String text;
    private Integer parent_id;
    private List<DepartDTO> nodes = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    @Override
    public String toString() {
        return "DepartDTO{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", parent_id=" + parent_id +
                ", nodes=" + nodes +
                '}';
    }

    public List<DepartDTO> getNodes() {
        return nodes;
    }

    public void setNodes(List<DepartDTO> nodes) {
        this.nodes = nodes;
    }

    public DepartDTO(Integer id, String text, Integer parent_id) {
        this.id = id;
        this.text = text;
        this.parent_id = parent_id;
    }
}
