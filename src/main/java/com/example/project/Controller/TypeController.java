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

import com.example.project.Model.Type;
import com.example.project.Service.TypeService;

@RestController
@RequestMapping(path = "api/type")
public class TypeController {

	private final TypeService typeService;

	@Autowired
	public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

	@GetMapping("/showTypes")
	 public List<Type> getTypes() {
        return typeService.getTypes();
	}

	@PostMapping("/addTypes")
    public void addNewType(@RequestBody Type type) {
		typeService.addNewType(type);
	}

    @DeleteMapping(path= "/deleteTypes/{typeId}")
    public void deleteType(@PathVariable("typeId") int typeId) {
    	typeService.deleteType(typeId);
    }

    @PutMapping(path = "/updateTypes/{typeId}")
    public void updateType(@PathVariable("typeId") int typeId, @RequestParam(required = false) String typeName) {
    	typeService.updateType(typeId,typeName);
    }
}
