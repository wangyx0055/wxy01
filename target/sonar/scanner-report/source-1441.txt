package com.longersec.blj.domain;

public class GConnection {

	private Integer connection_id;

	private String connection_name;

	private Integer parent_id;

	private String protocol;

	private Integer proxy_port;

	private String proxy_hostname;

	private String proxy_encryption_method;

	private Integer max_connections;

	private Integer max_connections_per_user;

	private Integer connection_weight;

	private Integer failover_only;



	public GConnection() {
		super();
	}

	public Integer getConnection_id() {
		return connection_id;
	}

	public void setConnection_id(Integer connection_id) {
		this.connection_id = connection_id;
	}

	public String getConnection_name() {
		return connection_name;
	}

	public void setConnection_name(String connection_name) {
		this.connection_name = connection_name;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Integer getProxy_port() {
		return proxy_port;
	}

	public void setProxy_port(Integer proxy_port) {
		this.proxy_port = proxy_port;
	}

	public String getProxy_hostname() {
		return proxy_hostname;
	}

	public void setProxy_hostname(String proxy_hostname) {
		this.proxy_hostname = proxy_hostname;
	}

	public String getProxy_encryption_method() {
		return proxy_encryption_method;
	}

	public void setProxy_encryption_method(String proxy_encryption_method) {
		this.proxy_encryption_method = proxy_encryption_method;
	}

	public Integer getMax_connections() {
		return max_connections;
	}

	public void setMax_connections(Integer max_connections) {
		this.max_connections = max_connections;
	}

	public Integer getMax_connections_per_user() {
		return max_connections_per_user;
	}

	public void setMax_connections_per_user(Integer max_connections_per_user) {
		this.max_connections_per_user = max_connections_per_user;
	}

	public Integer getConnection_weight() {
		return connection_weight;
	}

	public void setConnection_weight(Integer connection_weight) {
		this.connection_weight = connection_weight;
	}

	public Integer getFailover_only() {
		return failover_only;
	}

	public void setFailover_only(Integer failover_only) {
		this.failover_only = failover_only;
	}

	@Override
	public String toString() {
		return "GConnection{ "+
			",connection_id=" + connection_id +
			",connection_name=" + connection_name +
			",parent_id=" + parent_id +
			",protocol=" + protocol +
			",proxy_port=" + proxy_port +
			",proxy_hostname=" + proxy_hostname +
			",proxy_encryption_method=" + proxy_encryption_method +
			",max_connections=" + max_connections +
			",max_connections_per_user=" + max_connections_per_user +
			",connection_weight=" + connection_weight +
			",failover_only=" + failover_only +
			"";
	}
}
