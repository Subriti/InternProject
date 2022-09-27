package com.example.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.Model.Designation;
import com.example.project.Repository.DesignationRepository;

@Service
public class DesignationService {

	private final DesignationRepository designationRepository;

	@Autowired
	public DesignationService(DesignationRepository designationRepository) {
		this.designationRepository = designationRepository;
	}

	public List<Designation> getDesignations() {
		return designationRepository.findAll();
	}

	public void addNewDesignation(Designation designation) {
		designationRepository.save(designation);
	}

	public void deleteDesignation(int designationId) {
		boolean exists = designationRepository.existsById(designationId);
		if (!exists) {
			throw new IllegalStateException("designation with id " + designationId + " does not exist");
		}
		designationRepository.deleteById(designationId);
	}

	@Transactional
	public void updateDesignation(int designationId, String designationName) {
		Designation designation = designationRepository.findById(designationId)
				.orElseThrow(() -> new IllegalStateException("designation with id " + designationId + " does not exist"));

		if (designationName != null && designationName.length() > 0) {
			designation.setDesignationName(designationName);
		}
	}
}
