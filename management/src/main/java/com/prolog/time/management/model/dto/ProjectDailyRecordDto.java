package com.prolog.time.management.model.dto;

import java.util.Date;

public class ProjectDailyRecordDto {
    private String project_no;
    private String project_name;
    private String project_manager;
    private String project_user;
    private Date nocommit_date;
    private String view_type;
    private String id;
    private String project_user_dept;

    public String getProject_no() {
        return project_no;
    }

    public void setProject_no(String project_no) {
        this.project_no = project_no;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_manager() {
        return project_manager;
    }

    public void setProject_manager(String project_manager) {
        this.project_manager = project_manager;
    }

    public String getProject_user() {
        return project_user;
    }

    public void setProject_user(String project_user) {
        this.project_user = project_user;
    }

    public Date getNocommit_date() {
        return nocommit_date;
    }

    public void setNocommit_date(Date nocommit_date) {
        this.nocommit_date = nocommit_date;
    }

    public String getView_type() {
        return view_type;
    }

    public void setView_type(String view_type) {
        this.view_type = view_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject_user_dept() {
        return project_user_dept;
    }

    public void setProject_user_dept(String project_user_dept) {
        this.project_user_dept = project_user_dept;
    }
}
