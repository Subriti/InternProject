package com.example.project.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonPropertyOrder({ "problem_id, problem_desc, issue_date, problem_type" })

@Entity
@Table(name = "problem")
public class Problem implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7520761311590447988L;

	@Id
	@SequenceGenerator(
            name = "problem_sequence",
            sequenceName = "problem_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "problem_sequence"
    )
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "problem_id")
	private int problemId;

	@Column(name = "problem_desc")
	private String problemDesc;

	@Temporal(TemporalType.DATE)
	@Column(name = "issue_date")
	private Date issueDate;

	 @OneToOne
	 @JoinColumn(name = "problem_type", referencedColumnName = "type_id")
	private Type problemType;
	
	public Problem() {
		super();
	}

	public Problem(int problemId, String problemDesc, Date issueDate, Type problemType) {
		super();
		this.problemId = problemId;
		this.problemDesc = problemDesc;
		this.issueDate = issueDate;
		this.problemType= problemType;
	}

	public Problem(String problemDesc, Date issueDate, Type problemType) {
		super();
		this.problemDesc = problemDesc;
		this.issueDate = issueDate;
		this.problemType= problemType;
	}

	@JsonGetter("problem_id")
	public int getProblemId() {
		return problemId;
	}

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

	@JsonGetter("issue_date")
	public Date getIssueDate() {
		return issueDate;
	}

	@JsonSetter("issue_date")
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	@JsonGetter("problem_type")
	public Type getProblemType() {
		return problemType;
	}

	@JsonSetter("problem_type")
	public void setProblemType(Type problemType) {
		this.problemType = problemType;
	}

}
