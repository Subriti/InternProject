package com.example.project.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"area_id, area_name, area_code"})

@Entity
@Table(name = "area")
public class Area {
	@Id
	@SequenceGenerator(
            name = "area_sequence",
            sequenceName = "area_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "area_sequence"
    )
    //@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "area_id")
	private int areaId;

	@Column(name = "area_name")
	private String areaName;

	@Column(name = "area_code")
	private String areaCode;



	public Area() {
		super();
	}

	public Area(int areaId, String areaName, String areaCode) {
		super();
		this.areaId = areaId;
		this.areaName = areaName;
		this.areaCode = areaCode;
	}

	public Area(String areaName, String areaCode) {
		super();
		this.areaName = areaName;
		this.areaCode = areaCode;
	}

	public Area(String areaName) {
		super();
		this.areaName = areaName;
	}
	
	@JsonGetter("area_id")
	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	@JsonGetter("area_name")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@JsonGetter("area_code")
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}


	}
