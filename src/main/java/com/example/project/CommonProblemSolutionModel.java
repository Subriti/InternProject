package com.example.project;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonPropertyOrder({ "problem_id, problem_desc, issue_date, unsolved_days, list_solution, corp_name, area_name" })

public class CommonProblemSolutionModel {
	@JsonProperty("problem_id")
	private int problemId;
	
	@JsonProperty("problem_desc")
	private String problemDesc;
	
	@JsonProperty("issue_date")
	private Date issueDate; 
	
	@JsonProperty("unsolved_days")
	private int unsolvedDays;
	
	@JsonProperty("solution_desc")
    private String solutionDesc;
    
	@JsonProperty("staff_name")
	private String staffName;

	@JsonProperty("solved_date")
    private Date solvedDate;
	
	@JsonProperty("list_solution")
	private List<CommonProblemSolutionModel> listSolution;
	
	@JsonProperty("area_name")
	private String areaName;
	@JsonProperty("corp_name")
	private String corpName;


	public CommonProblemSolutionModel(int problemId, String problemDesc, Date issueDate, int unsolvedDays,
			String solutionDesc, String staffName, Date solvedDate, List<CommonProblemSolutionModel> listSolution,
			String areaName, String corpName) {
		super();
		this.problemId = problemId;
		this.problemDesc = problemDesc;
		this.issueDate = issueDate;
		this.unsolvedDays = unsolvedDays;
		this.solutionDesc = solutionDesc;
		this.staffName = staffName;
		this.solvedDate = solvedDate;
		this.listSolution = listSolution;
		this.areaName = areaName;
		this.corpName = corpName;
	}

	public CommonProblemSolutionModel() {

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

	@JsonGetter("unsolved_days")
	public int getUnsolvedDays() {
		return unsolvedDays;
	}

	@JsonSetter("unsolved_days")
	public void setUnsolvedDays(int unsolvedDays) {
		this.unsolvedDays = unsolvedDays;
	}
	
	@JsonGetter("solution_desc")
	public String getSolutionDesc() {
		return solutionDesc;
	}

	@JsonSetter("solution_desc")
	public void setSolutionDesc(String solutionDesc) {
		this.solutionDesc = solutionDesc;
	}

	@JsonGetter("staff_name")
	public String getStaffName() {
		return staffName;
	}

	@JsonSetter("staff_name")
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	@JsonGetter("solved_date")
	public Date getSolvedDate() {
		return solvedDate;
	}

	@JsonSetter("solved_date")
	public void setSolvedDate(Date solvedDate) {
		this.solvedDate = solvedDate;
	}


	@JsonGetter("list_solution")
	public List<CommonProblemSolutionModel> getListSolution() {
		return listSolution;
	}

	@JsonSetter("list_solution")
	public void setListSolution(List<CommonProblemSolutionModel> listSolution) {
		this.listSolution = listSolution;
	}
}
