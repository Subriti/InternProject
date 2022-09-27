package com.example.project.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"report_log_id, report_id, assigned_to, assigned_status, escalated_to"})

@Entity
@Table(name = "report_log")
public class ReportLog implements Serializable {

    /**
	 *
	 */
	private static final long serialVersionUID = 644089908573131517L;

	@Id
	@SequenceGenerator(
            name = "reportLog_sequence",
            sequenceName = "reportLog_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reportLog_sequence"
    )
	//@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reportLog_id")
    private int reportLogId;

    @OneToOne
    @JoinColumn(name = "report_id", referencedColumnName = "report_id")
    private Report reportId;

    @OneToOne
    @JoinColumn(name = "assigned_to", referencedColumnName = "staff_id")
    private Staff assignedTo;

    @OneToOne
    @JoinColumn(name = "assigned_status", referencedColumnName = "status_id")
    private Status assignedStatus;

    @OneToOne
    @JoinColumn(name = "escalated_to", nullable = true, referencedColumnName = "staff_id")
    private Staff escalatedTo;

	public ReportLog() {
		super();
	}


	public ReportLog(Report reportId, Staff assignedTo, Status assignedStatus, Staff escalatedTo) {
		super();
		this.reportId = reportId;
		this.assignedTo = assignedTo;
		this.assignedStatus = assignedStatus;
		this.escalatedTo = escalatedTo;
	}

	@JsonGetter("report_log_id")
	public int getReportLogId() {
		return reportLogId;
	}

	public void setReportLogId(int reportLogId) {
		this.reportLogId = reportLogId;
	}

	@JsonGetter("report_id")
	public Report getReportId() {
		return reportId;
	}

	public void setReportId(Report reportId) {
		this.reportId = reportId;
	}

	@JsonGetter("assigned_to")
	public Staff getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Staff assignedTo) {
		this.assignedTo = assignedTo;
	}

	@JsonGetter("assigned_status")
	public Status getAssignedStatus() {
		return assignedStatus;
	}

	public void setAssignedStatus(Status assignedStatus) {
		this.assignedStatus = assignedStatus;
	}

	@JsonGetter("escalated_to")
	public Staff getEscalatedTo() {
		return escalatedTo;
	}

	public void setEscalatedTo(Staff escalatedTo) {
		this.escalatedTo = escalatedTo;
	}

	@Override
	public String toString() {
		return "ReportLog [reportLogId=" + reportLogId + ", reportId=" + reportId + ", assignedTo=" + assignedTo
				+ ", assignedStatus=" + assignedStatus + ", escalatedTo=" + escalatedTo + "]";
	}


}
