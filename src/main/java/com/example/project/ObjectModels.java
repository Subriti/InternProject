package com.example.project;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonPropertyOrder({ "problem_id, problem_desc, issue_date, staff_name, corp_name, area_name" })

public class ObjectModels {
	@JsonProperty("problem_id")
	private int problemId;
	@JsonProperty("problem_desc")
	private String problemDesc;
	@JsonProperty("issue_date")
	private Date issueDate;
	@JsonProperty("staff_name")
	private String staffName;
	@JsonProperty("area_name")
	private String areaName;
	@JsonProperty("corp_name")
	private String corpName;
	@JsonProperty("status_name")
	private String statusName;

	public ObjectModels(String areaName, String staffName, String corpName, int problemId, String problemDesc,
			Date issueDate, String statusName) {
		super();
		this.areaName = areaName;
		this.staffName = staffName;
		this.corpName = corpName;
		this.problemId = problemId;
		this.problemDesc = problemDesc;
		this.issueDate = issueDate;
		this.statusName = statusName;
	}

	public ObjectModels() {

	}

	@JsonGetter("problem_id")
	public int getProblemId() {
		return problemId;
	}

	@JsonSetter("problem_id")
	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}

	@JsonGetter("problem_desc")
	public String getProblemDesc() {
		return problemDesc;
	}

	@JsonSetter("problem_desc")
	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}

	@JsonGetter("area_name")
	public String getAreaName() {
		return areaName;
	}

	@JsonSetter("area_name")
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@JsonGetter("staff_name")
	public String getStaffName() {
		return staffName;
	}

	@JsonSetter("staff_name")
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	@JsonGetter("corp_name")
	public String getCorpName() {
		return corpName;
	}

	@JsonSetter("corp_name")
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	@JsonGetter("issue_date")
	public Date getIssueDate() {
		return issueDate;
	}

	@JsonSetter("issue_date")
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	@JsonGetter("status_name")
	public String getStatusName() {
		return statusName;
	}

	@JsonSetter("status_name")
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "{area_name : " + areaName + ", staff_name : " + staffName + ", corp_name : " + corpName
				+ ", problem_id : " + problemId + ", problem_desc : " + problemDesc + ", issue_date : " + issueDate
				+ "}";
	}

}
