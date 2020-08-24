package com.longersec.blj.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * @author wxy
 */
public class Department {

	private Integer id;

	private Integer dept_id;

	@Pattern(regexp = "([A-Za-z]|[\\u4e00-\\u9fa5]|\\.|\\-|\\@|\\_|[0-9]){0,32}",message = "部门名称格式不正确")
	@NotNull(message = "部门名称不能为空")
	@NotEmpty(message = "部门名称不能为空")
	private String name;


	private Integer count;

	private Integer device_count;

	@Size(min=0, max=128,message = "长度不能超过128位")
	private String description;

	private Integer create_time;

	private Integer create_id;

	private Integer parent_id;

	private String searchAll;

	private String parent_name;

	public Integer getDept_id() {
		return dept_id;
	}

	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
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

	public Integer getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Integer create_time) {
		this.create_time = create_time;
	}

	public Integer getCreate_id() {
		return create_id;
	}

	public void setCreate_id(Integer create_id) {
		this.create_id = create_id;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public String getSearchAll() {
		return searchAll;
	}

	public void setSearchAll(String searchAll) {
		this.searchAll = searchAll;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}

	@Override
	public String toString() {
		return "Department{" +
				"id=" + id +
				", name='" + name + '\'' +
				", count=" + count +
				", device_count=" + device_count +
				", description='" + description + '\'' +
				", create_time=" + create_time +
				", create_id=" + create_id +
				", parent_id=" + parent_id +
				", searchAll='" + searchAll + '\'' +
				", parent_name='" + parent_name + '\'' +
				", dept_id='" + dept_id + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Department)) return false;
		Department that = (Department) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(name, that.name) &&
				Objects.equals(count, that.count) &&
				Objects.equals(device_count, that.device_count) &&
				Objects.equals(description, that.description) &&
				Objects.equals(create_time, that.create_time) &&
				Objects.equals(create_id, that.create_id) &&
				Objects.equals(parent_id, that.parent_id) &&
				Objects.equals(searchAll, that.searchAll) &&
				Objects.equals(parent_name, that.parent_name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, count, device_count, description, create_time, create_id, parent_id, searchAll, parent_name);
	}
}
