package com.longersec.blj.domain;

public class SystemUsage {

	private Long id;

	private String type;

	private String get_datetime;

	private Long total;

	private Long value;

	private Integer percent;



	public SystemUsage() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGet_datetime() {
		return get_datetime;
	}

	public void setGet_datetime(String get_datetime) {
		this.get_datetime = get_datetime;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public Integer getPercent() {
		return percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	@Override
	public String toString() {
		return "SystemUsage{ "+
			",id=" + id +
			",type=" + type +
			",get_datetime=" + get_datetime +
			",total=" + total +
			",value=" + value +
			",percent=" + percent +
			"";
	}
}
