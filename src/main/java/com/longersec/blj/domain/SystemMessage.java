package com.longersec.blj.domain;

public class SystemMessage {

	private Integer id;

	private String title;

	private String content;

	private String add_datetime;

	private Integer level;

	public SystemMessage() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLogin_datetime() {
		return add_datetime;
	}

	public void setLogin_datetime(String add_datetime) {
		this.add_datetime = add_datetime;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getRead_check() {
		return read_check;
	}

	public void setRead_check(Integer read_check) {
		this.read_check = read_check;
	}

	private Integer read_check;



	@Override
	public String toString() {
		return "SystemMessage{ "+
			",id=" + id +
			",title=" + title +
			",content=" + content +
			",add_datetime=" + add_datetime +
			",level=" + level +
			",read_check=" + read_check +
			"";
	}
}
