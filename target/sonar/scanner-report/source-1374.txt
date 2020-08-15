package com.longersec.blj.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ApppubProgram {

	private Integer id;

	@NotNull
	@Pattern(regexp = "([A-Za-z]|[\\u4e00-\\u9fa5]|\\-|[0-9]|[\\s;%&'@!#:/\\\\\\\\$*`~+.<>,()+=_?]){1,32}")
	private String name;

	private Integer apppub_server_id;

	@Pattern(regexp = "([A-Za-z]|[\\u4e00-\\u9fa5]|\\-|[0-9]|[\\s;%&'@!#:/\\\\\\\\$*`~+.<>,()+=_?]){1,128}")
	private String path;

	@Pattern(regexp = "(^$)|(^((?:(?:25[0-5]|2[0-4]\\\\d|[01]?\\\\d?\\\\d)\\\\.){3}(?:25[0-5]|2[0-4]\\\\d|[01]?\\\\d?\\\\d))|([a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\\.?)$)")
	private String parameter;

	private String icon;

	@Pattern(regexp = "(^$)|([A-Za-z]|[\\u4e00-\\u9fa5]|\\-|[0-9]|[;%&'@!#$%*.+,=_?$]){0,64}")
	private String desc;



	public ApppubProgram() {
		super();
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

	public Integer getApppub_server_id() {
		return apppub_server_id;
	}

	public void setApppub_server_id(Integer apppub_server_id) {
		this.apppub_server_id = apppub_server_id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "ApppubProgram{ "+
			",id=" + id +
			",name=" + name +
			",apppub_server_id=" + apppub_server_id +
			",path=" + path +
			",parameter=" + parameter +
			",icon=" + icon +
			",desc=" + desc +
			"";
	}
}
