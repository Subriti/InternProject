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

@JsonPropertyOrder({"solution_id, problem_id, solution_desc, solved_by, solved_date, is_solved"})

@Entity
@Table(name = "solution")
public class Solution implements Serializable {

    /**
	 *
	 */
	private static final long serialVersionUID = -2329815452893990426L;

	@Id
	@SequenceGenerator(
            name = "solution_sequence",
            sequenceName = "solution_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "solution_sequence"
    )
	//@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "solution_id")
    private int solutionId;

    @OneToOne
    @JoinColumn(name = "problem_id", referencedColumnName = "problem_id")
    private Problem problemId;

    @Column(name = "solution_desc")
    private String solutionDesc;
    
    @OneToOne
    @JoinColumn(name = "solved_by", referencedColumnName = "staff_id")
    private Staff solvedBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "solved_date")
    private Date solvedDate;
    
   @Column(name = "is_solved")
    private Boolean isSolved;

    public Solution() {
    	super();
	}

	public Solution(Problem problemId, String solutionDesc, Staff solvedBy, Date solvedDate, Boolean isSolved) {
		super();
		this.problemId = problemId;
		this.solutionDesc = solutionDesc;
		this.solvedBy = solvedBy;
		this.solvedDate = solvedDate;
		this.isSolved = isSolved;
	}


	@JsonGetter("solution_id")
	public int getSolutionId() {
		return solutionId;
	}

	@JsonSetter("solution_id")
	public void setSolutionId(int solutionId) {
		this.solutionId = solutionId;
	}

	@JsonGetter("problem_id")
	public Problem getProblemId() {
		return problemId;
	}

	@JsonSetter("problem_id")
	public void setProblemId(Problem problemId) {
		this.problemId = problemId;
	}

	@JsonGetter("solution_desc")
	public String getSolutionDesc() {
		return solutionDesc;
	}

	@JsonSetter("solution_desc")
	public void setSolutionDesc(String solutionDesc) {
		this.solutionDesc = solutionDesc;
	}

	@JsonGetter("solved_date")
	public Date getSolvedDate() {
		return solvedDate;
	}

	@JsonSetter("solved_date")
	public void setSolvedDate(Date solvedDate) {
		this.solvedDate = solvedDate;
	}
	
	@JsonGetter("solved_by")
	public Staff getSolvedBy() {
		return solvedBy;
	}

	@JsonSetter("solved_by")
	public void setSolvedBy(Staff solvedBy) {
		this.solvedBy = solvedBy;
	}

	@JsonGetter("is_solved")
	public Boolean getIsSolved() {
		return isSolved;
	}

	@JsonSetter("is_solved")
	public void setIsSolved(Boolean isSolved) {
		this.isSolved = isSolved;
	}

}
