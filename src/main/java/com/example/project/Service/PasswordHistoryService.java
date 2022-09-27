package com.example.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.Model.PasswordHistory;
import com.example.project.Repository.PasswordHistoryRepository;

@Service
public class PasswordHistoryService {

	private final PasswordHistoryRepository historyRepository;

	@Autowired
	public PasswordHistoryService(PasswordHistoryRepository historyRepository) {
		this.historyRepository= historyRepository;
	}

    public List<PasswordHistory> getPasswordHistory() {
    	return historyRepository.findAll();
    }

	public void addNewPasswordHistory(PasswordHistory history) {
		historyRepository.save(history);
	}

	public void deletePasswordHistory(int historyId) {
		boolean exists= historyRepository.existsById(historyId);
		if (!exists) {
			throw new IllegalStateException("history with id "+ historyId + "does not exist");
		}
		historyRepository.deleteById(historyId);
	}

	@Transactional
	public void updatePasswordHistory(int historyId, PasswordHistory passHistory) {
		PasswordHistory history= historyRepository.findById(historyId).orElseThrow(()-> new IllegalStateException("history with id "+ historyId + " does not exist"));

		if (passHistory.getDate()!=null ) {
			history.setDate(passHistory.getDate());
		}

		if (passHistory.getPassword()!=null && passHistory.getPassword().length()>0) {
			history.setPassword(passHistory.getPassword());
		}

		if (passHistory.getReasonType()!=null ) {
			history.setReasonType(passHistory.getReasonType());
		}

		if (passHistory.getUserId()!=null) {
			history.setUserId(passHistory.getUserId());
		}

	}
}
