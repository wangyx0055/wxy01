package com.longersec.blj.domain.DTO;

import java.util.Objects;

public class App {
    private Integer apppub_account_id;
    private String name;
    private String apppubserver;


    public Integer getApppub_account_id() {
		return apppub_account_id;
	}

	public void setApppub_account_id(Integer apppub_account_id) {
		this.apppub_account_id = apppub_account_id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApppubserver() {
		return apppubserver;
	}

	public void setApppubserver(String apppubserver) {
		this.apppubserver = apppubserver;
	}

	@Override
    public String toString() {
        return "App{" +
                "apppub_account_id=" + apppub_account_id +
                ", name='" + name + '\'' +
                ", apppubserver='" + apppubserver + '\'' +
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
        App app = (App) o;
        return Objects.equals(apppub_account_id, app.apppub_account_id) &&
                Objects.equals(name, app.name) &&
                Objects.equals(apppubserver, app.apppubserver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apppub_account_id, name, apppubserver);
    }
}