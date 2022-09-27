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

@JsonPropertyOrder({ "report_id, reported_date, corp_id, problem_id, staff_id, notes" })

@Entity
@Table(name = "report")
public class Report implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3412733841802158319L;

	@Id
	@SequenceGenerator(
            name = "report_sequence",
            sequenceName = "report_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "report_sequence"
    )
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "report_id")
	private int reportId;

	@OneToOne
	@JoinColumn(name = "corp_id", referencedColumnName = "corp_id")
	private Sfacl corpId;

	@OneToOne
	@JoinColumn(name = "problem_id", referencedColumnName = "problem_id")
	private Problem problemId;

	@OneToOne
	@JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
	private Staff staffId;

	@Temporal(TemporalType.DATE)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "reported_date")
	private Date reportedDate;

	@Column(name = "notes")
	private String notes;

	public Report() {
		super();
	}

	public Report(Sfacl corpId, Problem problemId, Staff staffId, Date reportedDate, String notes) {
		super();
		this.corpId = corpId;
		this.problemId = problemId;
		this.staffId = staffId;
		this.reportedDate = reportedDate;
		this.notes = notes;
	}

	@JsonGetter("report_id")
	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	@JsonGetter("corp_id")
	public Sfacl getCorpId() {
		return corpId;
	}

	public void setCorpId(Sfacl corpId) {
		this.corpId = corpId;
	}

	@JsonGetter("problem_id")
	public Problem getProblemId() {
		return problemId;
	}

	public void setProblemId(Problem problemId) {
		this.problemId = problemId;
	}

	@JsonGetter("staff_id")
	public Staff getStaffId() {
		return staffId;
	}

	public void setStaffId(Staff staffId) {
		this.staffId = staffId;
	}

	@JsonGetter("reported_date")
	public Date getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}

	@JsonGetter("notes")
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Report [reportId=" + reportId + ", corpId=" + corpId + ", problemId=" + problemId + ", staffId="
				+ staffId + ", reportedDate=" + reportedDate + ", notes=" + notes + "]";
	}

}
