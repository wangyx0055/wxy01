package com.longersec.blj.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ConfigSms {

	private Integer id;

	/*private Integer method;
*/
	/*@NotEmpty(message = "请输入aip_Id")*/
	private String access_key_id;

	/*@NotEmpty(message = "请输入api_Secret")*/
	private String access_key_secret;

	private String sign_name;

	private Integer type;

	private String template_code;
	/*@NotEmpty(message = "请输入URL地址")*/

	private String domain;

	private String product;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAccess_key_id() {
		return access_key_id;
	}

	public void setAccess_key_id(String access_key_id) {
		this.access_key_id = access_key_id;
	}

	public String getAccess_key_secret() {
		return access_key_secret;
	}

	public void setAccess_key_secret(String access_key_secret) {
		this.access_key_secret = access_key_secret;
	}

	public String getSign_name() {
		return sign_name;
	}

	public void setSign_name(String sign_name) {
		this.sign_name = sign_name;
	}

	public String getTemplate_code() {
		return template_code;
	}

	public void setTemplate_code(String template_code) {
		this.template_code = template_code;
	}
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

@Override
public String toString() {
	return "ConfigSms{" +
			"id=" + id +
			", type='" + type + '\'' +
			", access_key_id='" + access_key_id + '\'' +
			", access_key_secret='" + access_key_secret + '\'' +
			", sign_name='" + sign_name + '\'' +
			", template_code='" + template_code + '\'' +
			", domain='" + domain + '\'' +
			", product='" + product + '\'' +
			'}';
}
}
