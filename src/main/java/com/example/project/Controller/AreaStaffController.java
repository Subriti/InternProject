
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

import com.example.project.Model.Area;
import com.example.project.Model.AreaStaff;
import com.example.project.Model.Type;
import com.example.project.Service.AreaStaffService;

@RestController

@RequestMapping(path = "api/areastaff")
public class AreaStaffController {

	private final AreaStaffService areastaffService;

	@Autowired
	public AreaStaffController(AreaStaffService areastaffService) {
		this.areastaffService = areastaffService;
	}

	@GetMapping("/showAreaStaff")
	public List<AreaStaff> getAreaStaffs() {
		return areastaffService.getAreaStaff();
	}
	
	@GetMapping(path = "/findStaffByArea/{areaId}")
	public List<Object> findStaffByArea(@PathVariable Area areaId, @RequestParam(required = false)Type typeId ) {
		return areastaffService.findStaffByArea(areaId, typeId);
	}
	
	@PostMapping("/addAreaStaff")
	public void addNewAreaStaff(@RequestBody AreaStaff areastaff) {
		areastaffService.addNewAreaStaff(areastaff);
	}

	@DeleteMapping(path = "/deleteAreaStaff/{id}")
	public void deleteAreaStaff(@PathVariable int id) {
		areastaffService.deleteAreaStaff(id);
	}

	@PutMapping(path = "/updateAreaStaff/{id}")
	public void updateAreaStaff(@PathVariable int id, @RequestBody AreaStaff areaStaff) {
		areastaffService.updateAreaStaff(id, areaStaff);
	}
}
