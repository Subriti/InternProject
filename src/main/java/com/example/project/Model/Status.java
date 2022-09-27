package com.example.project.Model;

import java.io.Serializable;

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

@JsonPropertyOrder({"status_id, status_name, status_type"})

@Entity
@Table(name = "status")
public class Status implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -3456522709697343323L;
	@Id
	 @SequenceGenerator(
	            name = "status_sequence",
	            sequenceName = "status_sequence",
	            allocationSize = 1
	    )
	    @GeneratedValue(
	            strategy = GenerationType.SEQUENCE,
	            generator = "status_sequence"
	    )
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "status_id")
	private int statusId;
	@Column(name = "status_name")
	private String statusName;
	@OneToOne
    @JoinColumn(name = "status_type", referencedColumnName = "type_id")
	private Type statusType;

	@JsonGetter("status_id")
	public int getStatusId() {
		return statusId;
	}

	@JsonSetter("status_id")
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	@JsonGetter("status_name")
	public String getStatusName() {
		return statusName;
	}

	@JsonSetter("status_name")
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@JsonGetter("status_type")
	public Type getStatusType() {
		return statusType;
	}

	@JsonSetter("status_type")
	public void setStatusType(Type statusType) {
		this.statusType = statusType;
	}

	public Status() {

	}

	public Status(int statusId, String statusName, Type statusType) {
		super();
		this.statusId = statusId;
		this.statusName = statusName;
		this.statusType = statusType;
	}

	public Status(String statusName, Type statusType) {
		super();
		this.statusName = statusName;
		this.statusType = statusType;
	}
	
}
