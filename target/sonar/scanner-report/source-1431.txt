package com.longersec.blj.domain.DTO;

import java.util.Objects;

/**
 * @author Administrator
 */
public class Cmd {
	private Integer cmd_id;
	private String name;

	public Integer getCmd_id() {
		return cmd_id;
	}

	public void setCmd_id(Integer cmd_id) {
		this.cmd_id = cmd_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Cmd{" +
				"cmd_id=" + cmd_id +
				", name='" + name + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Cmd cmd = (Cmd) o;
		return Objects.equals(cmd_id, cmd.cmd_id) &&
				Objects.equals(name, cmd.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cmd_id, name);
	}
}
