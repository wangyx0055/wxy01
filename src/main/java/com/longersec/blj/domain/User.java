package com.longersec.blj.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

public class User implements java.io.Serializable{
	private static final long serialVersionUID = 3058782159500119274L;

	private Integer id;

	private Integer role_id;

	private String topName;

	@NotNull(message = "用户名不能为空")
	private String username;

	@Pattern(regexp = "[\\u4e00-\\u9fa5\\w-_@0-9]{1,128}",message = "姓名格式错误")
	@NotNull(message = "姓名不能为空")
	private String realname;

	
	private String ldap_dn;

	private String avatar;

	private String password;
	
	private String plain_password;

	private Integer status;

	private Integer fail_count;

	private Integer lock_time;

	private Integer last_chpass;


	private Integer groupid;
	
	@Email(message = "邮箱格式错误")
	private String email;
	
	@Pattern(regexp = "(((13[0-9])|(14[5,7])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])|(18[0-9])|(19[8,9]))\\d{8})?",message = "电话格式错误")
	private String mobile;
	
	@Pattern(regexp = "([1-9][0-9]{4,})?",message = "qq账号错误")
	private String qq;

	@Pattern(regexp = "([a-zA-Z\\d_]{5,})?",message = "微信账号错误")
	private String wechat;

	private Integer creator_id;

	private Integer create_time;

	private String description;

	private Integer local_auth;

	private Integer sms_auth;

	private Integer radius_auth;

	private Integer finger_auth;

	private Integer dynamic_auth;

	private Integer ldap_auth;

	private Integer ad_auth;

	private Integer wechat_auth;

	private Integer email_auth;

	private Integer auth_type;

	private String last_login_ip;

	private Integer last_change_password;

	private Integer department;

	private String depart_name;

	private String type;

	private Integer resolution;

	private String rdpdisk;

	private Integer session_title;
	
	private String google_auth_key;
	
	private String searchall;
	
	private String rolename;
	
	private String groupname;
	
	private Integer google_auth_token_used;
	
	private String sms_code;
	
	private String fingerdata;
	
	private Integer fingerdatachange;

	private String valid_datetime_start;

	private Integer valid_long;

	private String valid_datetime_end;

	public String getValid_datetime_start() {
		return valid_datetime_start;
	}

	public void setValid_datetime_start(String valid_datetime_start) {
		this.valid_datetime_start = valid_datetime_start;
	}

	public Integer getValid_long() {
		return valid_long;
	}

	public void setValid_long(Integer valid_long) {
		this.valid_long = valid_long;
	}

	public String getValid_datetime_end() {
		return valid_datetime_end;
	}

	public void setValid_datetime_end(String valid_datetime_end) {
		this.valid_datetime_end = valid_datetime_end;
	}

	public String getFingerdata() {
		return fingerdata;
	}

	public void setFingerdata(String fingerdata) {
		this.fingerdata = fingerdata;
	}

	public Integer getFingerdatachange() {
		return fingerdatachange;
	}

	public void setFingerdatachange(Integer fingerdatachange) {
		this.fingerdatachange = fingerdatachange;
	}

	public String getSms_code() {
		return sms_code;
	}

	public void setSms_code(String sms_code) {
		this.sms_code = sms_code;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getSearchall() {
		return searchall;
	}

	public void setSearchall(String searchall) {
		this.searchall = searchall;
	}

	public String getGoogle_auth_key() {
		return google_auth_key;
	}

	public void setGoogle_auth_key(String google_auth_key) {
		this.google_auth_key = google_auth_key;
	}

	public User() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	
	public Integer getLocal_auth() {
		return local_auth;
	}

	public void setLocal_auth(Integer local_auth) {
		this.local_auth = local_auth;
	}

	public Integer getSms_auth() {
		return sms_auth;
	}

	public void setSms_auth(Integer sms_auth) {
		this.sms_auth = sms_auth;
	}

	public Integer getRadius_auth() {
		return radius_auth;
	}

	public void setRadius_auth(Integer radius_auth) {
		this.radius_auth = radius_auth;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getLdap_dn() {
		return ldap_dn;
	}

	public void setLdap_dn(String ldap_dn) {
		this.ldap_dn = ldap_dn;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPlain_password() {
		return plain_password;
	}

	public void setPlain_password(String plain_password) {
		this.plain_password = plain_password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getFail_count() {
		return fail_count;
	}

	public void setFail_count(Integer fail_count) {
		this.fail_count = fail_count;
	}

	public Integer getLock_time() {
		return lock_time;
	}

	public void setLock_time(Integer lock_time) {
		this.lock_time = lock_time;
	}

	public Integer getLast_chpass() {
		return last_chpass;
	}

	public void setLast_chpass(Integer last_chpass) {
		this.last_chpass = last_chpass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public Integer getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(Integer creator_id) {
		this.creator_id = creator_id;
	}

	public Integer getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Integer create_time) {
		this.create_time = create_time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDynamic_auth() {
		return dynamic_auth;
	}

	public void setDynamic_auth(Integer dynamic_auth) {
		this.dynamic_auth = dynamic_auth;
	}

	public Integer getLdap_auth() {
		return ldap_auth;
	}

	public void setLdap_auth(Integer ldap_auth) {
		this.ldap_auth = ldap_auth;
	}

	public Integer getAd_auth() {
		return ad_auth;
	}

	public void setAd_auth(Integer ad_auth) {
		this.ad_auth = ad_auth;
	}

	public Integer getWechat_auth() {
		return wechat_auth;
	}

	public void setWechat_auth(Integer wechat_auth) {
		this.wechat_auth = wechat_auth;
	}

	public Integer getEmail_auth() {
		return email_auth;
	}

	public void setEmail_auth(Integer email_auth) {
		this.email_auth = email_auth;
	}

	public Integer getAuth_type() {
		return auth_type;
	}

	public void setAuth_type(Integer auth_type) {
		this.auth_type = auth_type;
	}

	public String getLast_login_ip() {
		return last_login_ip;
	}

	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}

	public Integer getLast_change_password() {
		return last_change_password;
	}

	public void setLast_change_password(Integer last_change_password) {
		this.last_change_password = last_change_password;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getResolution() {
		return resolution;
	}

	public void setResolution(Integer resolution) {
		this.resolution = resolution;
	}

	public String getRdpdisk() {
		return rdpdisk;
	}

	public void setRdpdisk(String rdpdisk) {
		this.rdpdisk = rdpdisk;
	}

	public Integer getSession_title() {
		return session_title;
	}

	public void setSession_title(Integer session_title) {
		this.session_title = session_title;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public Integer getGoogle_auth_token_used() {
		return google_auth_token_used;
	}

	public void setGoogle_auth_token_used(Integer google_auth_token_used) {
		this.google_auth_token_used = google_auth_token_used;
	}

	public Integer getFinger_auth() {
		return finger_auth;
	}

	public void setFinger_auth(Integer finger_auth) {
		this.finger_auth = finger_auth;
	}

	public String getTopName() {
		return topName;
	}

	public void setTopName(String topName) {
		this.topName = topName;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", role_id=" + role_id +
				", topName='" + topName + '\'' +
				", username='" + username + '\'' +
				", realname='" + realname + '\'' +
				", ldap_dn='" + ldap_dn + '\'' +
				", avatar='" + avatar + '\'' +
				", password='" + password + '\'' +
				", plain_password='" + plain_password + '\'' +
				", status=" + status +
				", fail_count=" + fail_count +
				", lock_time=" + lock_time +
				", last_chpass=" + last_chpass +
				", groupid=" + groupid +
				", email='" + email + '\'' +
				", mobile='" + mobile + '\'' +
				", qq='" + qq + '\'' +
				", wechat='" + wechat + '\'' +
				", creator_id=" + creator_id +
				", create_time=" + create_time +
				", description='" + description + '\'' +
				", local_auth=" + local_auth +
				", sms_auth=" + sms_auth +
				", radius_auth=" + radius_auth +
				", finger_auth=" + finger_auth +
				", dynamic_auth=" + dynamic_auth +
				", ldap_auth=" + ldap_auth +
				", ad_auth=" + ad_auth +
				", wechat_auth=" + wechat_auth +
				", email_auth=" + email_auth +
				", auth_type=" + auth_type +
				", last_login_ip='" + last_login_ip + '\'' +
				", last_change_password=" + last_change_password +
				", department=" + department +
				", depart_name='" + depart_name + '\'' +
				", type='" + type + '\'' +
				", resolution=" + resolution +
				", rdpdisk='" + rdpdisk + '\'' +
				", session_title=" + session_title +
				", google_auth_key='" + google_auth_key + '\'' +
				", searchall='" + searchall + '\'' +
				", rolename='" + rolename + '\'' +
				", groupname='" + groupname + '\'' +
				", google_auth_token_used=" + google_auth_token_used +
				", sms_code='" + sms_code + '\'' +
				", fingerdata='" + fingerdata + '\'' +
				", fingerdatachange=" + fingerdatachange +
				", valid_datetime_start='" + valid_datetime_start + '\'' +
				", valid_long=" + valid_long +
				", valid_datetime_end='" + valid_datetime_end + '\'' +
				'}';
	}
}
