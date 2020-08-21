package com.longersec.blj.domain;

public class SshScript {
    private Integer id;

    private String name;

    private String file_path;

    private String desc;

    private String upload_time;


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

    public java.lang.String getFile_path() {
        return file_path;
    }

    public void setFile_path(java.lang.String file_path) {
        this.file_path = file_path;
    }

    public java.lang.String getDesc() {
        return desc;
    }

    public void setDesc(java.lang.String desc) {
        this.desc = desc;
    }

    public String getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(String upload_time) {
        this.upload_time = upload_time;
    }
}