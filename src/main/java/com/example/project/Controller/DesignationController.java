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
import com.example.project.Service.DesignationService;

@RestController
@RequestMapping(path = "api/designation")
public class DesignationController {

	private final DesignationService designationService;

	@Autowired
	public DesignationController(DesignationService designationService) {
        this.designationService = designationService;
    }

	@GetMapping("/showDesignations")
	 public List<Designation> getDesignations() {
        return designationService.getDesignations();
	}

	@PostMapping("/addDesignations")
    public void addNewDesignation(@RequestBody Designation designation) {
		designationService.addNewDesignation(designation);
	}

    @DeleteMapping(path= "/deleteDesignations/{designationId}")
    public void deleteDesignation(@PathVariable("designationId") int designationId) {
    	designationService.deleteDesignation(designationId);
    }

    @PutMapping(path = "/updateDesignations/{designationId}")
    public void updateDesignation(@PathVariable int designationId, @RequestParam(required = false) String designationName) {
    	designationService.updateDesignation(designationId,designationName);
    }
}
