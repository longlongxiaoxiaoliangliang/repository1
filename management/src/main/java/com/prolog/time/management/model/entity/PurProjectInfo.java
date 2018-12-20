package com.prolog.time.management.model.entity;

import java.io.Serializable;
import java.util.Date;

public class PurProjectInfo implements Serializable {
    private static final long serialVersionUID = 5444017239100620999L;
    private String project_id;
    private String pur_contract_no;
    private String contract_name;
    private Date sign_date;
    private String sale_contract_no;
    private String sale_contract_name;
    private String first_party;
    private String second_party;
    private double contract_money;
    private String pay_condition;
    private Date ins_dt;
    private String ins_usr_id;
    private String ins_usr_name;

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getPur_contract_no() {
        return pur_contract_no;
    }

    public void setPur_contract_no(String pur_contract_no) {
        this.pur_contract_no = pur_contract_no;
    }

    public String getContract_name() {
        return contract_name;
    }

    public void setContract_name(String contract_name) {
        this.contract_name = contract_name;
    }

    public Date getSign_date() {
        return sign_date;
    }

    public void setSign_date(Date sign_date) {
        this.sign_date = sign_date;
    }

    public String getSale_contract_no() {
        return sale_contract_no;
    }

    public void setSale_contract_no(String sale_contract_no) {
        this.sale_contract_no = sale_contract_no;
    }

    public String getSale_contract_name() {
        return sale_contract_name;
    }

    public void setSale_contract_name(String sale_contract_name) {
        this.sale_contract_name = sale_contract_name;
    }

    public String getFirst_party() {
        return first_party;
    }

    public void setFirst_party(String first_party) {
        this.first_party = first_party;
    }

    public String getSecond_party() {
        return second_party;
    }

    public void setSecond_party(String second_party) {
        this.second_party = second_party;
    }

    public double getContract_money() {
        return contract_money;
    }

    public void setContract_money(double contract_money) {
        this.contract_money = contract_money;
    }

    public String getPay_condition() {
        return pay_condition;
    }

    public void setPay_condition(String pay_condition) {
        this.pay_condition = pay_condition;
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

    public String getIns_usr_name() {
        return ins_usr_name;
    }

    public void setIns_usr_name(String ins_usr_name) {
        this.ins_usr_name = ins_usr_name;
    }
}
