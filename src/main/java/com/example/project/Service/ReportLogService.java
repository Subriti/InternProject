package com.example.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.Model.ReportLog;
import com.example.project.Repository.ReportLogRepository;

@Service
public class ReportLogService {

	private final ReportLogRepository reportLogRepository;

	@Autowired
	public ReportLogService(ReportLogRepository reportLogRepository) {
		this.reportLogRepository = reportLogRepository;
	}

	public List<ReportLog> getReportLogs() {
		return reportLogRepository.findAll();
	}

	public void addNewReportLog(ReportLog reportLog) {
		reportLogRepository.save(reportLog);
	}

	public void deleteReportLog(int reportLogId) {
		boolean exists = reportLogRepository.existsById(reportLogId);
		if (!exists) {
			throw new IllegalStateException("reportLog with id " + reportLogId + " does not exist");
		}
		reportLogRepository.deleteById(reportLogId);
	}

	@Transactional
	public void updateReportLog(int reportLogId, ReportLog NewreportLog) {
		ReportLog reportLog = reportLogRepository.findById(reportLogId)
				.orElseThrow(() -> new IllegalStateException("reportLog with id " + reportLogId + " does not exist"));

		if (NewreportLog.getAssignedStatus() != null) {
			reportLog.setAssignedStatus(NewreportLog.getAssignedStatus());
		}

		if (NewreportLog.getAssignedTo()!= null) {
			reportLog.setAssignedTo(NewreportLog.getAssignedTo());
		}

		if (NewreportLog.getEscalatedTo() != null) {
			reportLog.setEscalatedTo(NewreportLog.getEscalatedTo());
		}

		if (NewreportLog.getReportId()!= null) {
			reportLog.setReportId(NewreportLog.getReportId());
		}

	}
}
