package com.example.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.Model.Sfacl;
import com.example.project.Repository.SfaclRepository;

@Service
public class SfaclService {

	private final SfaclRepository corpRepository;

	@Autowired
	public SfaclService(SfaclRepository corpRepository) {
		this.corpRepository= corpRepository;
	}

    public List<Sfacl> getSfacl() {
    	return corpRepository.findAll();
    }

	public void addNewSfacl(Sfacl corp) {
		corpRepository.save(corp);
	}

	public void deleteSfacl(int corpId) {
		boolean exists= corpRepository.existsById(corpId);
		if (!exists) {
			throw new IllegalStateException("corp with id "+ corpId + "does not exist");
		}
		corpRepository.deleteById(corpId);
	}

	@Transactional
	public void updateSfacl(int corpId, Sfacl Newcorp) {
		Sfacl corp= corpRepository.findById(corpId).orElseThrow(()-> new IllegalStateException("corp with id "+ corpId + " does not exist"));

		if (Newcorp.getCorpName()!=null && Newcorp.getCorpName().length()>0) {
			corp.setCorpName(Newcorp.getCorpName());
		}

		if (Newcorp.getCorpAddress()!=null && Newcorp.getCorpAddress().length()>0) {
			corp.setCorpAddress(Newcorp.getCorpAddress());
		}

		if (Newcorp.getCorpPhone()!=null) {
			corp.setCorpPhone(Newcorp.getCorpPhone());
		}

		if (Newcorp.getAreaId()!=null) {
			corp.setAreaId(Newcorp.getAreaId());
		}

		if (Newcorp.getStatus()!=null) {
			corp.setStatus(Newcorp.getStatus());
		}
	}
}
