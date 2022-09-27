package com.example.project.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonPropertyOrder({"designation_id, designation_name"})



@Entity
@Table(name = "designation")
public class Designation implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 2966322424748743047L;
	@Id
	@SequenceGenerator(
            name = "designation_sequence",
            sequenceName = "designation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "designation_sequence"
    )
	//@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "designation_id")
    private int designationId;

    @Column(name = "designation_name")
    private String designationName;

    public Designation() {

    }

	public Designation(int designationId, String designationName) {
		super();
		this.designationId = designationId;
		this.designationName = designationName;
	}

	public Designation(String designationName) {
		super();
		this.designationName = designationName;
	}

	@JsonGetter("designation_id")
	public int getDesignationId() {
		return designationId;
	}

	@JsonSetter("designation_id")
	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}

	@JsonGetter("designation_name")
	public String getDesignationName() {
		return designationName;
	}

	@JsonSetter("designation_name")
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	@Override
	public String toString() {
		return "Designation [designationId=" + designationId + ", designationName=" + designationName + "]";
	}
}
