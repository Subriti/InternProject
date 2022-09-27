package com.example.project.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonPropertyOrder({ "id, area_id, staff_id, from_date, to_date" })

@Entity
@Table(name = "area_staff")
public class AreaStaff implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5402324502765938532L;

	@Id
	@SequenceGenerator(
            name = "areaStaff_sequence",
            sequenceName = "areaStaff_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "areaStaff_sequence"
    )
	@Column(name = "id")
	private int id;

	@OneToOne
	@JoinColumn(name = "area_id", referencedColumnName = "area_id")
	private Area areaId;

	@OneToOne
	@JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
	private Staff staffId;

	@Column(name = "from_date")
	private Date fromDate;

	@Column(name = "to_date", nullable = true)
	private Date toDate;

	public AreaStaff() {
	}


	public AreaStaff(int id, Area areaId, Staff staffId, Date fromDate, Date toDate) {
		super();
		this.id = id;
		this.areaId = areaId;
		this.staffId = staffId;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public AreaStaff(Area areaId, Staff staffId, Date fromDate, Date toDate) {
		super();
		this.areaId = areaId;
		this.staffId = staffId;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}
	
	public AreaStaff(Area areaId) {
		this.areaId = areaId;
	}


	@JsonGetter("id")
	public int getId() {
		return id;
	}

	@JsonSetter("id")
	public void setId(int id) {
		this.id = id;
	}

	@JsonGetter("area_id")
	public Area getAreaId() {
		return areaId;
	}

	@JsonSetter("area_id")
	public void setAreaId(Area areaId) {
		this.areaId = areaId;
	}

	@JsonGetter("staff_id")
	public Staff getStaffId() {
		return staffId;
	}

	@JsonSetter("staff_id")
	public void setStaffId(Staff staffId) {
		this.staffId = staffId;
	}


	@JsonGetter("from_date")
	public Date getFromDate() {
		return fromDate;
	}


	@JsonSetter("from_date")
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@JsonGetter("to_date")
	public Date getToDate() {
		return toDate;
	}

	@JsonSetter("to_date")
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

}
