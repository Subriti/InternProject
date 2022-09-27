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

import com.example.project.Model.Sfacl;
import com.example.project.Service.SfaclService;

@RestController
@RequestMapping(path = "api/corp")
public class SfaclController {

	private final SfaclService corpService;

	@Autowired
	public SfaclController(SfaclService corpService) {
        this.corpService= corpService;
    }

	@GetMapping("/showSfacl")
	 public List<Sfacl> getSfacl() {
        return corpService.getSfacl();
	}

    @PostMapping("/addSfacl")
    public void addNewSfacl(@RequestBody Sfacl corp) {
    	corpService.addNewSfacl(corp);
	}

    @DeleteMapping(path= "/deleteSfacl/{corpId}")
    public void deleteSfacl(@PathVariable("corpId") int corpId) {
    	corpService.deleteSfacl(corpId);
    }

    @PutMapping(path = "/updateSfacl/{corpId}")
    public void updateSfacl(@PathVariable int corpId, @RequestBody Sfacl corp) {
    	corpService.updateSfacl(corpId,corp);
    }
}
