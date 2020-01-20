package com.wxf.rabbitmq.mail.pojo;

import java.io.Serializable;
import java.util.Date;

public class ScheduleJob implements Serializable {
    private String jobId;

    private String beanName;

    private String methodName;

    private String params;

    private String cronExpression;

    private String status;

    private String remark;

    private Date createTime;

    private Integer invalidTime;

    private static final long serialVersionUID = 1L;

    public ScheduleJob(String jobId, String beanName, String methodName, String params, String cronExpression, String status, String remark, Date createTime, Integer invalidTime) {
        this.jobId = jobId;
        this.beanName = beanName;
        this.methodName = methodName;
        this.params = params;
        this.cronExpression = cronExpression;
        this.status = status;
        this.remark = remark;
        this.createTime = createTime;
        this.invalidTime = invalidTime;
    }

    public ScheduleJob() {
        super();
    }

    public String getJobId() {
        return jobId;
    }

    public ScheduleJob withJobId(String jobId) {
        this.setJobId(jobId);
        return this;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    public String getBeanName() {
        return beanName;
    }

    public ScheduleJob withBeanName(String beanName) {
        this.setBeanName(beanName);
        return this;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName == null ? null : beanName.trim();
    }

    public String getMethodName() {
        return methodName;
    }

    public ScheduleJob withMethodName(String methodName) {
        this.setMethodName(methodName);
        return this;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    public String getParams() {
        return params;
    }

    public ScheduleJob withParams(String params) {
        this.setParams(params);
        return this;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public ScheduleJob withCronExpression(String cronExpression) {
        this.setCronExpression(cronExpression);
        return this;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression == null ? null : cronExpression.trim();
    }

    public String getStatus() {
        return status;
    }

    public ScheduleJob withStatus(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public ScheduleJob withRemark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ScheduleJob withCreateTime(Date createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getInvalidTime() {
        return invalidTime;
    }

    public ScheduleJob withInvalidTime(Integer invalidTime) {
        this.setInvalidTime(invalidTime);
        return this;
    }

    public void setInvalidTime(Integer invalidTime) {
        this.invalidTime = invalidTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ScheduleJob other = (ScheduleJob) that;
        return (this.getJobId() == null ? other.getJobId() == null : this.getJobId().equals(other.getJobId()))
            && (this.getBeanName() == null ? other.getBeanName() == null : this.getBeanName().equals(other.getBeanName()))
            && (this.getMethodName() == null ? other.getMethodName() == null : this.getMethodName().equals(other.getMethodName()))
            && (this.getParams() == null ? other.getParams() == null : this.getParams().equals(other.getParams()))
            && (this.getCronExpression() == null ? other.getCronExpression() == null : this.getCronExpression().equals(other.getCronExpression()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getInvalidTime() == null ? other.getInvalidTime() == null : this.getInvalidTime().equals(other.getInvalidTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getJobId() == null) ? 0 : getJobId().hashCode());
        result = prime * result + ((getBeanName() == null) ? 0 : getBeanName().hashCode());
        result = prime * result + ((getMethodName() == null) ? 0 : getMethodName().hashCode());
        result = prime * result + ((getParams() == null) ? 0 : getParams().hashCode());
        result = prime * result + ((getCronExpression() == null) ? 0 : getCronExpression().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getInvalidTime() == null) ? 0 : getInvalidTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", jobId=").append(jobId);
        sb.append(", beanName=").append(beanName);
        sb.append(", methodName=").append(methodName);
        sb.append(", params=").append(params);
        sb.append(", cronExpression=").append(cronExpression);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", invalidTime=").append(invalidTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}