package com.wxf.rabbitmq.mail.pojo;

import java.io.Serializable;
import java.util.Date;

public class ScheduleJobLog implements Serializable {
    private String logId;

    private String jobId;

    private String beanName;

    private String methodName;

    private String params;

    private String status;

    private String error;

    private Integer times;

    private String ipAddress;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public ScheduleJobLog(String logId, String jobId, String beanName, String methodName, String params, String status, String error, Integer times, String ipAddress, Date createTime) {
        this.logId = logId;
        this.jobId = jobId;
        this.beanName = beanName;
        this.methodName = methodName;
        this.params = params;
        this.status = status;
        this.error = error;
        this.times = times;
        this.ipAddress = ipAddress;
        this.createTime = createTime;
    }

    public ScheduleJobLog() {
        super();
    }

    public String getLogId() {
        return logId;
    }

    public ScheduleJobLog withLogId(String logId) {
        this.setLogId(logId);
        return this;
    }

    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    public String getJobId() {
        return jobId;
    }

    public ScheduleJobLog withJobId(String jobId) {
        this.setJobId(jobId);
        return this;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    public String getBeanName() {
        return beanName;
    }

    public ScheduleJobLog withBeanName(String beanName) {
        this.setBeanName(beanName);
        return this;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName == null ? null : beanName.trim();
    }

    public String getMethodName() {
        return methodName;
    }

    public ScheduleJobLog withMethodName(String methodName) {
        this.setMethodName(methodName);
        return this;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    public String getParams() {
        return params;
    }

    public ScheduleJobLog withParams(String params) {
        this.setParams(params);
        return this;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public String getStatus() {
        return status;
    }

    public ScheduleJobLog withStatus(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getError() {
        return error;
    }

    public ScheduleJobLog withError(String error) {
        this.setError(error);
        return this;
    }

    public void setError(String error) {
        this.error = error == null ? null : error.trim();
    }

    public Integer getTimes() {
        return times;
    }

    public ScheduleJobLog withTimes(Integer times) {
        this.setTimes(times);
        return this;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public ScheduleJobLog withIpAddress(String ipAddress) {
        this.setIpAddress(ipAddress);
        return this;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ScheduleJobLog withCreateTime(Date createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        ScheduleJobLog other = (ScheduleJobLog) that;
        return (this.getLogId() == null ? other.getLogId() == null : this.getLogId().equals(other.getLogId()))
            && (this.getJobId() == null ? other.getJobId() == null : this.getJobId().equals(other.getJobId()))
            && (this.getBeanName() == null ? other.getBeanName() == null : this.getBeanName().equals(other.getBeanName()))
            && (this.getMethodName() == null ? other.getMethodName() == null : this.getMethodName().equals(other.getMethodName()))
            && (this.getParams() == null ? other.getParams() == null : this.getParams().equals(other.getParams()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getError() == null ? other.getError() == null : this.getError().equals(other.getError()))
            && (this.getTimes() == null ? other.getTimes() == null : this.getTimes().equals(other.getTimes()))
            && (this.getIpAddress() == null ? other.getIpAddress() == null : this.getIpAddress().equals(other.getIpAddress()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLogId() == null) ? 0 : getLogId().hashCode());
        result = prime * result + ((getJobId() == null) ? 0 : getJobId().hashCode());
        result = prime * result + ((getBeanName() == null) ? 0 : getBeanName().hashCode());
        result = prime * result + ((getMethodName() == null) ? 0 : getMethodName().hashCode());
        result = prime * result + ((getParams() == null) ? 0 : getParams().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getError() == null) ? 0 : getError().hashCode());
        result = prime * result + ((getTimes() == null) ? 0 : getTimes().hashCode());
        result = prime * result + ((getIpAddress() == null) ? 0 : getIpAddress().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logId=").append(logId);
        sb.append(", jobId=").append(jobId);
        sb.append(", beanName=").append(beanName);
        sb.append(", methodName=").append(methodName);
        sb.append(", params=").append(params);
        sb.append(", status=").append(status);
        sb.append(", error=").append(error);
        sb.append(", times=").append(times);
        sb.append(", ipAddress=").append(ipAddress);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}