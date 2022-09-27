package com.example.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.Model.Staff;
import com.example.project.Model.Status;
import com.example.project.Repository.StaffRepository;

@Service
public class StaffService {

	private final StaffRepository staffRepository;

	@Autowired
	public StaffService(StaffRepository staffRepository) {
		this.staffRepository = staffRepository;
	}

	public List<Staff> getStaff() {
		return staffRepository.findAll();
	}

	public List<Staff> findStaff(Status staffStatus) {
		return staffRepository.findStaffByStatus(staffStatus);
	}

	public void addNewStaff(Staff staff) {
		staffRepository.save(staff);
	}

	public void deleteStaff(int staffId) {
		boolean exists = staffRepository.existsById(staffId);
		if (!exists) {
			throw new IllegalStateException("staff with id " + staffId + " does not exist");
		}
		staffRepository.deleteById(staffId);
	}

	@Transactional
	public void updateStaff(int staffId, Staff Newstaff) {
		Staff staff = staffRepository.findById(staffId)
				.orElseThrow(() -> new IllegalStateException("staff with id " + staffId + " does not exist"));

		if (Newstaff.getStaffName() != null && Newstaff.getStaffName().length() > 0) {
			staff.setStaffName(Newstaff.getStaffName());
		}
		if (Newstaff.getStaffAddress()!= null && Newstaff.getStaffAddress().length() > 0) {
			staff.setStaffAddress(Newstaff.getStaffAddress());
		}
		if (Newstaff.getStaffPhone()!=null) {
			staff.setStaffPhone(Newstaff.getStaffPhone());
		}
		if (Newstaff.getStaffStatus() != null) {
			staff.setStaffStatus(Newstaff.getStaffStatus());
		}
		if (Newstaff.getStaffType()!= null) {
			staff.setStaffType(Newstaff.getStaffType());
		}
		if (Newstaff.getUserId() != null) {
			staff.setUserId(Newstaff.getUserId());
		}
	}
}
