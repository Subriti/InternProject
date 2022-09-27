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

import com.example.project.Model.Status;
import com.example.project.Service.StatusService;

@RestController
@RequestMapping(path = "api/status")
public class StatusController {

	private final StatusService statusService;

	@Autowired
	public StatusController(StatusService statusService) {
        this.statusService= statusService;
    }

	@GetMapping("/showStatus")
	 public List<Status> getStatus() {
        return statusService.getStatus();
	}

    @PostMapping("/addStatus")
    public void addNewStatus(@RequestBody Status status) {
    	statusService.addNewStatus(status);
	}

    @DeleteMapping(path= "/deleteStatus/{statusId}")
    public void deleteStatus(@PathVariable("statusId") int statusId) {
    	statusService.deleteStatus(statusId);
    }

    @PutMapping(path = "/updateStatus/{statusId}")
    public void updateStatus(@PathVariable("statusId") int statusId, String statusName) {
    	statusService.updateStatus(statusId,statusName);
    }
}
