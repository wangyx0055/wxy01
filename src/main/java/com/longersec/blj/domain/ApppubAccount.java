package com.longersec.blj.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ApppubAccount {

	private Integer id;

	@NotNull
	@Pattern(regexp = "([A-Za-z]|[\\u4e00-\\u9fa5]|\\-|[0-9]|[;%&'@!#$%*+,=_?$]){1,32}")
	private String name;

	@Pattern(regexp = "([A-Za-z]|[\\u4e00-\\u9fa5]|\\-|[0-9]|[;%&'@!#$%*+,=_?$]){0,32}")
	private String username;

	@Pattern(regexp = "([A-Za-z]|[\\u4e00-\\u9fa5]|\\-|[0-9]|[;%&'@!#$%*+,=_?$]){0,32}")
	private String password;

	private Integer apppub_program_id;
	
	private Integer apppub_server_id;

	private Integer department;

	private String depart_name;

	private Integer favorite_id;

	private String url;

	private String desc;

	private Integer apppub;
	
	private String appserverip;
	
	private String appservername;
	
	private String appprogramname;
	
	private String searchAll;

	private String topName;

	public String getTopName() {
		return topName;
	}

	public void setTopName(String topName) {
		this.topName = topName;
	}

	public Integer getFavorite_id() {
		return favorite_id;
	}


	public void setFavorite_id(Integer favorite_id) {
		this.favorite_id = favorite_id;
	}


	public Integer getApppub_server_id() {
		return apppub_server_id;
	}

	public void setApppub_server_id(Integer apppub_server_id) {
		this.apppub_server_id = apppub_server_id;
	}

	public String getAppserverip() {
		return appserverip;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}
	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}

	public void setAppserverip(String appserverip) {
		this.appserverip = appserverip;
	}


	public String getAppservername() {
		return appservername;
	}


	public void setAppservername(String appservername) {
		this.appservername = appservername;
	}


	public String getAppprogramname() {
		return appprogramname;
	}


	public void setAppprogramname(String appprogramname) {
		this.appprogramname = appprogramname;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getApppub_program_id() {
		return apppub_program_id;
	}

	public void setApppub_program_id(Integer apppub_program_id) {
		this.apppub_program_id = apppub_program_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getApppub() {
		return apppub;
	}

	public void setApppub(Integer apppub) {
		this.apppub = apppub;
	}


	public String getSearchAll() {
		return searchAll;
	}

	public void setSearchAll(String searchAll) {
		this.searchAll = searchAll;
	}


	@Override
	public String toString() {
		return "ApppubAccount{" +
				"id=" + id +
				", name='" + name + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", apppub_program_id=" + apppub_program_id +
				", apppub_server_id=" + apppub_server_id +
				", department=" + department +
				", depart_name='" + depart_name + '\'' +
				", favorite_id=" + favorite_id +
				", url='" + url + '\'' +
				", desc='" + desc + '\'' +
				", apppub=" + apppub +
				", appserverip='" + appserverip + '\'' +
				", appservername='" + appservername + '\'' +
				", appprogramname='" + appprogramname + '\'' +
				", searchAll='" + searchAll + '\'' +
				", topName='" + topName + '\'' +
				'}';
	}
}
