package com.example.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.Model.Report;
import com.example.project.Repository.ReportRepository;

@Service
public class ReportService {

	private final ReportRepository reportRepository;

	@Autowired
	public ReportService(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}

	public List<Report> getReport() {
		return reportRepository.findAll();
	}

	public void addNewReport(Report report) {
		reportRepository.save(report);
	}

	public void deleteReport(int reportId) {
		boolean exists = reportRepository.existsById(reportId);
		if (!exists) {
			throw new IllegalStateException("report with id " + reportId + " does not exist");
		}
		reportRepository.deleteById(reportId);
	}

	@Transactional
	public void updateReport(int reportId, Report Newreport) {
		Report report = reportRepository.findById(reportId)
				.orElseThrow(() -> new IllegalStateException("report with id " + reportId + " does not exist"));

		if (Newreport.getNotes() != null) {
			report.setNotes(Newreport.getNotes());
		}

		if (Newreport.getReportedDate() != null) {
			report.setReportedDate(Newreport.getReportedDate());
		}

		if (Newreport.getCorpId()!= null) {
			report.setCorpId(Newreport.getCorpId());
		}

		if (Newreport.getProblemId() != null) {
			report.setProblemId(Newreport.getProblemId());
		}

		if (Newreport.getStaffId() != null) {
			report.setStaffId(Newreport.getStaffId());
		}

	}
	}
