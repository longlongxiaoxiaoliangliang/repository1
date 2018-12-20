package com.prolog.time.management.model.entity;

import java.io.Serializable;
import java.util.Date;

public class Workinghours implements Serializable {
    private static final long serialVersionUID = 6444017239100620999L;
    private String workingId;
    private String projectId;
    private String projectName;
    private String rojectType;
    private String remark1;
    private Date begDate;
    private Date endDate;
    private String workSite;
    private String auditStatus;
    private String auditDate;
    private String auditUser;
    private String remark2;
    private String workingContext;
    private Date creatDate;
    private Date updateDate;
    private String creatBy;
    private String updateBy;
    private String insusrname;
    private String auditusername;

    public String getWorkingId() {
        return workingId;
    }

    public void setWorkingId(String workingId) {
        this.workingId = workingId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRojectType() {
        return rojectType;
    }

    public void setRojectType(String rojectType) {
        this.rojectType = rojectType;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public Date getBegDate() {
        return begDate;
    }

    public void setBegDate(Date begDate) {
        this.begDate = begDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getWorkSite() {
        return workSite;
    }

    public void setWorkSite(String workSite) {
        this.workSite = workSite;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreatBy() {
        return creatBy;
    }

    public void setCreatBy(String creatBy) {
        this.creatBy = creatBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getWorkingContext() {
        return workingContext;
    }

    public void setWorkingContext(String workingContext) {
        this.workingContext = workingContext;
    }

    public String getInsusrname() {
        return insusrname;
    }

    public void setInsusrname(String insusrname) {
        this.insusrname = insusrname;
    }

    public String getAuditusername() {
        return auditusername;
    }

    public void setAuditusername(String auditusername) {
        this.auditusername = auditusername;
    }
}
