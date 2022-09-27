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

import com.example.project.CommonProblemSolutionModel;
import com.example.project.Model.Solution;
import com.example.project.Service.SolutionService;

@RestController
@RequestMapping(path = "api/solution")
public class SolutionController {

	private final SolutionService solutionService;

	@Autowired
	public SolutionController(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

	@GetMapping("/showSolutions")
	 public List<Solution> getSolutions() {
        return solutionService.getSolutions();
	}

	//working one
		@GetMapping(path = "/findSolution")
		public List<CommonProblemSolutionModel> findSolution(@RequestBody String jsonRequest) {
			return solutionService.findSolution(jsonRequest);
		}
		
    @PostMapping("/addSolution")
    public void addNewSolution(@RequestBody Solution solution) {
		solutionService.addNewSolution(solution);
	}

    @DeleteMapping(path= "/deleteSolution/{solutionId}")
    public void deleteSolution(@PathVariable("solutionId") int solutionId) {
    	solutionService.deleteSolution(solutionId);
    }

    @PutMapping(path = "/updateSolution/{solutionId}")
    public void updateSolution(@PathVariable int solutionId, @RequestBody Solution solution) {
    	solutionService.updateSolution(solutionId,solution);
    }
}
