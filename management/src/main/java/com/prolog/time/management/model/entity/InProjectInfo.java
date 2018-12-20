package com.prolog.time.management.model.entity;

import java.io.Serializable;
import java.util.Date;

public class InProjectInfo implements Serializable {
    private static final long serialVersionUID = 4444117239100620999L;
    private String project_id;
    private String arc_no;
    private String contract_no;
    private String region_manager;
    private String sale_manager;
    private String region_name;
    private String contract_name;
    private String company_code;
    private Date sign_date;
    private String province_name;
    private String province_no;
    private String chinese_project;
    private String project_code;
    private int cnt;
    private String contract_type;
    private String project_type;
    private String cooperation_type;
    private Date ins_dt;
    private String ins_usr_id;
    private String project_states;
    private String project_types;
    private String ins_usr_name;

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getArc_no() {
        return arc_no;
    }

    public void setArc_no(String arc_no) {
        this.arc_no = arc_no;
    }

    public String getContract_no() {
        return contract_no;
    }

    public void setContract_no(String contract_no) {
        this.contract_no = contract_no;
    }

    public String getRegion_manager() {
        return region_manager;
    }

    public void setRegion_manager(String region_manager) {
        this.region_manager = region_manager;
    }

    public String getSale_manager() {
        return sale_manager;
    }

    public void setSale_manager(String sale_manager) {
        this.sale_manager = sale_manager;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getContract_name() {
        return contract_name;
    }

    public void setContract_name(String contract_name) {
        this.contract_name = contract_name;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public Date getSign_date() {
        return sign_date;
    }

    public void setSign_date(Date sign_date) {
        this.sign_date = sign_date;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getProvince_no() {
        return province_no;
    }

    public void setProvince_no(String province_no) {
        this.province_no = province_no;
    }

    public String getChinese_project() {
        return chinese_project;
    }

    public void setChinese_project(String chinese_project) {
        this.chinese_project = chinese_project;
    }

    public String getProject_code() {
        return project_code;
    }

    public void setProject_code(String project_code) {
        this.project_code = project_code;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public String getContract_type() {
        return contract_type;
    }

    public void setContract_type(String contract_type) {
        this.contract_type = contract_type;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getCooperation_type() {
        return cooperation_type;
    }

    public void setCooperation_type(String cooperation_type) {
        this.cooperation_type = cooperation_type;
    }

    public Date getIns_dt() {
        return ins_dt;
    }

    public void setIns_dt(Date ins_dt) {
        this.ins_dt = ins_dt;
    }

    public String getIns_usr_id() {
        return ins_usr_id;
    }

    public void setIns_usr_id(String ins_usr_id) {
        this.ins_usr_id = ins_usr_id;
    }

    public String getProject_states() {
        return project_states;
    }

    public void setProject_states(String project_states) {
        this.project_states = project_states;
    }

    public String getProject_types() {
        return project_types;
    }

    public void setProject_types(String project_types) {
        this.project_types = project_types;
    }

    public String getIns_usr_name() {
        return ins_usr_name;
    }

    public void setIns_usr_name(String ins_usr_name) {
        this.ins_usr_name = ins_usr_name;
    }
}
