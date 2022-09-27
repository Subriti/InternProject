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

import com.example.project.Model.Area;
import com.example.project.Service.AreaService;

@RestController
@RequestMapping(path = "api/area")
public class AreaController {

	private final AreaService areaService;

	@Autowired
	public AreaController(AreaService areaService) {
		this.areaService = areaService;
	}

	@GetMapping("/showArea")
	public List<Area> getArea() {
		return areaService.getArea();
	}

	@PostMapping("/addArea")
	public void addNewArea(@RequestBody Area area) {
		areaService.addNewArea(area);
	}

	@DeleteMapping(path = "/deleteArea/{areaId}")
	public void deleteArea(@PathVariable("areaId") int areaId) {
		areaService.deleteArea(areaId);
	}

	@PutMapping(path = "/updateArea/{areaId}")
	public void updateArea(@PathVariable int areaId, @RequestBody Area area) {
		areaService.updateArea(areaId, area);
	}
}
