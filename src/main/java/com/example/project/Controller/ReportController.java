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

import com.example.project.Model.Report;
import com.example.project.Service.ReportService;

@RestController
@RequestMapping(path = "api/report")
public class ReportController {

	private final ReportService reportService;

	@Autowired
	public ReportController(ReportService reportService) {
        this.reportService= reportService;
    }

	@GetMapping("/showReport")
	 public List<Report> getReport() {
        return reportService.getReport();
	}

    @PostMapping("/addReport")
    public void addNewReport(@RequestBody Report report) {
    	reportService.addNewReport(report);
	}

    @DeleteMapping(path= "/deleteReport/{reportId}")
    public void deleteReport(@PathVariable("reportId") int reportId) {
    	reportService.deleteReport(reportId);
    }

    @PutMapping(path = "/updateReport/{reportId}")
    public void updateReport(@PathVariable int reportId, @RequestBody Report report) {
    	reportService.updateReport(reportId, report);
    }
}
