package com.longersec.blj.domain.DTO;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DepartDTO {
    private Integer id;
    private String text;
    private Integer parent_id;
    private Integer count;
    private Integer device_count;
    private String description;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getDevice_count() {
        return device_count;
    }

    public void setDevice_count(Integer device_count) {
        this.device_count = device_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DepartDTO(Integer id, String text, Integer parent_id, Integer count, Integer device_count, String description) {
        this.id = id;
        this.text = text;
        this.parent_id = parent_id;
        this.count = count;
        this.device_count = device_count;
        this.description = description;
    }

    @Override
    public String toString() {
        return "DepartDTO{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", parent_id=" + parent_id +
                ", count=" + count +
                ", device_count=" + device_count +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DepartDTO)) return false;
        DepartDTO departDTO = (DepartDTO) o;
        return Objects.equals(id, departDTO.id) &&
                Objects.equals(text, departDTO.text) &&
                Objects.equals(parent_id, departDTO.parent_id) &&
                Objects.equals(count, departDTO.count) &&
                Objects.equals(device_count, departDTO.device_count) &&
                Objects.equals(description, departDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, parent_id, count, device_count, description);
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
