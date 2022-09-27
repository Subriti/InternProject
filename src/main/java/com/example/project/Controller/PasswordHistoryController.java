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

import com.example.project.Model.PasswordHistory;
import com.example.project.Service.PasswordHistoryService;

@RestController
@RequestMapping(path = "api/history")
public class PasswordHistoryController {

	private final PasswordHistoryService historyService;

	@Autowired
	public PasswordHistoryController(PasswordHistoryService historyService) {
        this.historyService= historyService;
    }

	@GetMapping("/showPasswordHistory")
	 public List<PasswordHistory> getPasswordHistory() {
        return historyService.getPasswordHistory();
	}

    @PostMapping("/addPasswordHistory")
    public void addNewPasswordHistory(@RequestBody PasswordHistory history) {
    	historyService.addNewPasswordHistory(history);
	}

    @DeleteMapping(path= "/deletePasswordHistory/{historyId}")
    public void deletePasswordHistory(@PathVariable("historyId") int historyId) {
    	historyService.deletePasswordHistory(historyId);
    }

    @PutMapping(path = "/updatePasswordHistory/{historyId}")
    public void updatePasswordHistory(@PathVariable int historyId,@RequestBody PasswordHistory passwordHistory) {
    	historyService.updatePasswordHistory(historyId,passwordHistory);
    }
}
