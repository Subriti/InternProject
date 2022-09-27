package com.example.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.Model.Area;
import com.example.project.Repository.AreaRepository;

@Service
public class AreaService {

	private final AreaRepository areaRepository;

	@Autowired
	public AreaService(AreaRepository areaRepository) {
		this.areaRepository= areaRepository;
	}

    public List<Area> getArea() {
    	return areaRepository.findAll();
    }

	public void addNewArea(Area area) {
		areaRepository.save(area);
	}

	public void deleteArea(int areaId) {
		boolean exists= areaRepository.existsById(areaId);
		if (!exists) {
			throw new IllegalStateException("area with id "+ areaId + "does not exist");
		}
		areaRepository.deleteById(areaId);
	}

	@Transactional
	public void updateArea(int areaId, Area Newarea) {
		Area area= areaRepository.findById(areaId).orElseThrow(()-> new IllegalStateException("area with id "+ areaId + " does not exist"));

		if (Newarea.getAreaName()!=null && Newarea.getAreaName().length()>0) {
			area.setAreaName(Newarea.getAreaName());
		}

		if (Newarea.getAreaCode()!=null) {
			area.setAreaCode(Newarea.getAreaCode());
		}

	}
}
