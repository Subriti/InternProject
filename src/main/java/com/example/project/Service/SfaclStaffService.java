package com.example.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.Model.Designation;
import com.example.project.Model.Sfacl;
import com.example.project.Model.SfaclStaff;
import com.example.project.Repository.SfaclStaffRepository;

@Service
public class SfaclStaffService {

	private final SfaclStaffRepository corpStaffRepository;

	@Autowired
	public SfaclStaffService(SfaclStaffRepository corpStaffRepository) {
		this.corpStaffRepository= corpStaffRepository;
	}

    public List<SfaclStaff> getSfaclStaff() {
    	return corpStaffRepository.findAll();
    }

    public List<SfaclStaff> findStaffByDesignation(Designation designationId) {
		return corpStaffRepository.findStaffByDesignation(designationId);
	}

    public List<SfaclStaff> findStaffBySfacl(Sfacl corpId) {
		return corpStaffRepository.findStaffByCorp(corpId);
	}


    public List<SfaclStaff> findSfaclStaff(Sfacl corpId, Designation designationId) {
		return corpStaffRepository.findSfaclStaff(corpId, designationId);
	}

	public void addNewSfaclStaff(SfaclStaff corpStaff) {
		corpStaffRepository.save(corpStaff);
	}

	public void deleteSfaclStaff(int corpStaffId) {
		boolean exists= corpStaffRepository.existsById(corpStaffId);
		if (!exists) {
			throw new IllegalStateException("corpStaff with id "+ corpStaffId + "does not exist");
		}
		corpStaffRepository.deleteById(corpStaffId);
	}

	@Transactional
	public void updateSfaclStaff(int corpStaffId, SfaclStaff NewsfaclStaff) {
		SfaclStaff corpStaff= corpStaffRepository.findById(corpStaffId).orElseThrow(()-> new IllegalStateException("corpStaff with id "+ corpStaffId + " does not exist"));

		if (NewsfaclStaff.getCorpStaffName()!=null && NewsfaclStaff.getCorpStaffName().length()>0) {
			corpStaff.setCorpStaffName(NewsfaclStaff.getCorpStaffName());
		}

		if (NewsfaclStaff.getCorpStaffAddress()!=null && NewsfaclStaff.getCorpStaffAddress().length()>0) {
			corpStaff.setCorpStaffAddress(NewsfaclStaff.getCorpStaffAddress());
		}

		if (NewsfaclStaff.getCorpStaffPhone()!=null) {
			corpStaff.setCorpStaffPhone(NewsfaclStaff.getCorpStaffPhone());
		}

		if (NewsfaclStaff.getCorpId()!=null) {
			corpStaff.setCorpId(NewsfaclStaff.getCorpId());
		}

		if (NewsfaclStaff.getDesignationId()!=null) {
			corpStaff.setDesignationId(NewsfaclStaff.getDesignationId());
		}



	}
}
