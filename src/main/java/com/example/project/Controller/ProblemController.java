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

import com.example.project.ObjectModels;
import com.example.project.Model.Problem;
import com.example.project.Service.ProblemService;

@RestController
@RequestMapping(path = "api/problem")
public class ProblemController {

	private final ProblemService problemService;

	@Autowired
	public ProblemController(ProblemService problemService) {
		this.problemService = problemService;
	}

	@GetMapping("/showProblems")
	public List<Problem> getProblems() {
		return problemService.getProblems();
	}
	
	//working one
	@GetMapping(path = "/findProblem")
	public List<ObjectModels> findProblem(@RequestBody String jsonRequest) {
		return problemService.findProblem(jsonRequest);
	}

	@PostMapping("/addProblem")
	public void addNewProblem(@RequestBody Problem problem) {
		problemService.addNewProblem(problem);
	}

	@DeleteMapping(path = "/deleteProblem/{problemId}")
	public void deleteProblem(@PathVariable("problemId") int problemId) {
		problemService.deleteProblem(problemId);
	}

	@PutMapping(path = "/updateProblem/{problemId}")
	public void updateProblem(@PathVariable int problemId, @RequestBody Problem problem) {
		problemService.updateProblem(problemId, problem);
	}
}