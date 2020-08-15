package com.longersec.blj.domain.DTO;

import java.util.Objects;

/**
 * @author Administrator
 */
public class PolicyCmdGroup {
    private Integer cmdgroup_id;
    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCmdgroup_id() {
		return cmdgroup_id;
	}

	public void setCmdgroup_id(Integer cmdgroup_id) {
		this.cmdgroup_id = cmdgroup_id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PolicyCmdGroup cmdGroup = (PolicyCmdGroup) o;
		return Objects.equals(cmdgroup_id, cmdGroup.cmdgroup_id) &&
				Objects.equals(name, cmdGroup.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cmdgroup_id, name);
	}
}
