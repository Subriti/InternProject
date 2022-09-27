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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Model.Designation;
import com.example.project.Model.Sfacl;
import com.example.project.Model.SfaclStaff;
import com.example.project.Service.SfaclStaffService;

@RestController
@RequestMapping(path = "api/corpStaff")
public class SfaclStaffController {

	private final SfaclStaffService corpStaffService;

	@Autowired
	public SfaclStaffController(SfaclStaffService corpStaffService) {
        this.corpStaffService= corpStaffService;
    }

	@GetMapping("/showSfaclStaff")
	 public List<SfaclStaff> getSfaclStaff() {
        return corpStaffService.getSfaclStaff();
	}

	@GetMapping(path = "/findSfaclStaff/{corpId}")
	public List<SfaclStaff> findSfaclStaff(@PathVariable Sfacl corpId, @RequestParam(required = false) Designation designationId) {
		return corpStaffService.findSfaclStaff(corpId, designationId);
	}

	@GetMapping(path = "/findSfaclStaffByCorp/{corpId}")
	public List<SfaclStaff> findSfaclStaffByCorp(@PathVariable Sfacl corpId) {
		return corpStaffService.findStaffBySfacl(corpId);
	}

	@GetMapping(path = "/findSfaclStaffByDesignation/{designationId}")
	public List<SfaclStaff> findSfaclStaffByDesignation(@PathVariable Designation designationId) {
		return corpStaffService.findStaffByDesignation(designationId);
	}

    @PostMapping("/addSfaclStaff")
    public void addNewSfaclStaff(@RequestBody SfaclStaff corpStaff) {
    	corpStaffService.addNewSfaclStaff(corpStaff);
	}

    @DeleteMapping(path= "/deleteSfaclStaff/{corpStaffId}")
    public void deleteSfaclStaff(@PathVariable("corpStaffId") int corpStaffId) {
    	corpStaffService.deleteSfaclStaff(corpStaffId);
    }

    @PutMapping(path = "/updateSfaclStaff/{corpStaffId}")
    public void updateSfaclStaff(@PathVariable int corpStaffId, @RequestBody SfaclStaff sfaclStaff) {
    	corpStaffService.updateSfaclStaff(corpStaffId,sfaclStaff);
    }
}
