package com.example.project.Service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.Model.Status;
import com.example.project.Repository.StatusRepository;

@Service
public class StatusService {

	private final StatusRepository statusRepository;

	@Autowired
	public StatusService(StatusRepository statusRepository) {
		this.statusRepository = statusRepository;
	}

	public List<Status> getStatus() {
		return statusRepository.findAll();
	}

	public void addNewStatus(Status status) {
		statusRepository.save(status);
	}

	public void deleteStatus(int statusId) {
		boolean exists = statusRepository.existsById(statusId);
		if (!exists) {
			throw new IllegalStateException("status with id " + statusId + " does not exist");
		}
		statusRepository.deleteById(statusId);
	}

	@Transactional
	public void updateStatus(int statusId, String statusName) {
		Status status = statusRepository.findById(statusId)
				.orElseThrow(() -> new IllegalStateException("status with id " + statusId + " does not exist"));

		if (statusName != null && statusName.length() > 0 && !Objects.equals(status.getStatusName(), statusName)) {
			status.setStatusName(statusName);
		}
	}
}
