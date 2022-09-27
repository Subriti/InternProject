package com.example.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Model.ReportLog;
import com.example.project.Service.ReportLogService;

@RestController
@RequestMapping(path = "api/reportLog")
public class ReportLogController {

	private final ReportLogService reportLogService;

	@Autowired
	public ReportLogController(ReportLogService reportLogService) {
		this.reportLogService = reportLogService;
	}

	@GetMapping("/showReportLogs")
	public List<ReportLog> getReportLogs() {
		return reportLogService.getReportLogs();
	}

	@PostMapping("/addReportLog")
	public void addNewReportLog(@RequestBody ReportLog reportLog) {
		reportLogService.addNewReportLog(reportLog);
	}

	@DeleteMapping(path = "/deleteReportLog/{reportLogId}")
	public void deleteReportLog(@PathVariable("reportLogId") int reportLogId) {
		reportLogService.deleteReportLog(reportLogId);
	}

	@PutMapping(path = "/updateReportLogs/{reportLogId}")
	public void updateReportLog(@PathVariable int reportLogId, @RequestBody ReportLog reportLog) {
		reportLogService.updateReportLog(reportLogId, reportLog);
	}
}
