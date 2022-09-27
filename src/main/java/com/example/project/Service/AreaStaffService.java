
package com.example.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.Model.Area;
import com.example.project.Model.AreaStaff;
import com.example.project.Model.Type;
import com.example.project.Repository.AreaStaffRepository;

@Service
public class AreaStaffService {

	private final AreaStaffRepository areastaffRepository;

	@Autowired
	public AreaStaffService(AreaStaffRepository areastaffRepository) {
		this.areastaffRepository = areastaffRepository;
	}

	public List<AreaStaff> getAreaStaff() {
		return areastaffRepository.findAll();
	}

	public List<Object> findStaffByArea(Area areaId, Type typeId) {
		if (typeId !=null) {
			return areastaffRepository.findStaffByArea(areaId, typeId);
		}
		return areastaffRepository.findStaffByArea(areaId);
	}

	public void addNewAreaStaff(AreaStaff areastaff) {
		areastaffRepository.save(areastaff);
	}

	public void deleteAreaStaff(int id) {
		boolean exists = areastaffRepository.existsById(id);
		if (!exists) {
			throw new IllegalStateException("AreaStaff Details with id " + id + " does not exist");
		}
		areastaffRepository.deleteById(id);
	}

	@Transactional
	public void updateAreaStaff(int id, AreaStaff newAreaStaff) {
		AreaStaff areastaff = areastaffRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("AreaStaff Details with id " + id + " does not exist"));

		if (newAreaStaff.getAreaId() != null) {
			areastaff.setAreaId(newAreaStaff.getAreaId());
		}

		if (newAreaStaff.getStaffId() != null) {
			areastaff.setStaffId(newAreaStaff.getStaffId());
		}

		if (newAreaStaff.getFromDate() != null) {
			areastaff.setFromDate(newAreaStaff.getFromDate());
		}

		if (newAreaStaff.getToDate() != null) {
			areastaff.setToDate(newAreaStaff.getToDate());
		}
	}
}
