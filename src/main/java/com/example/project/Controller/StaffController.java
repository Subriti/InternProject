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

import com.example.project.Model.Staff;
import com.example.project.Model.Status;
import com.example.project.Service.StaffService;

@RestController
@RequestMapping(path = "api/staff")
public class StaffController {

	private final StaffService staffService;

	@Autowired
	public StaffController(StaffService staffService) {
		this.staffService = staffService;
	}

	@GetMapping("/showStaff")
	public List<Staff> getStaff() {
		return staffService.getStaff();
	}

	@GetMapping(path = "/findStaff/{staffStatus}")
	public List<Staff> findStaff(@PathVariable Status staffStatus) {
		return staffService.findStaff(staffStatus);
	}

	@PostMapping("/addStaff")
	public void addNewStaff(@RequestBody Staff staff) {
		staffService.addNewStaff(staff);
	}

	@DeleteMapping(path = "/deleteStaff/{staffId}")
	public void deleteStaff(@PathVariable("staffId") int staffId) {
		staffService.deleteStaff(staffId);
	}

	@PutMapping(path = "/updateStaff/{staffId}")
	public void updateStaff(@PathVariable int staffId, @RequestBody Staff staff) {
		staffService.updateStaff(staffId, staff);
	}
}
