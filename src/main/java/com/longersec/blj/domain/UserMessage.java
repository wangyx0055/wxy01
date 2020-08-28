package com.longersec.blj.domain;

public class UserMessage {

	private Integer id;

	private Integer from_user_id;

	private Integer to_user_id;

	private String module;

	private Integer module_id;

	private String create_time;



	public UserMessage() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFrom_user_id() {
		return from_user_id;
	}

	public void setFrom_user_id(Integer from_user_id) {
		this.from_user_id = from_user_id;
	}

	public Integer getTo_user_id() {
		return to_user_id;
	}

	public void setTo_user_id(Integer to_user_id) {
		this.to_user_id = to_user_id;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Integer getModule_id() {
		return module_id;
	}

	public void setModule_id(Integer module_id) {
		this.module_id = module_id;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return "UserMessage{ "+
			",id=" + id +
			",from_user_id=" + from_user_id +
			",to_user_id=" + to_user_id +
			",module=" + module +
			",module_id=" + module_id +
			",create_time=" + create_time +
			"";
	}
}
