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

@JsonPropertyOrder({"corp_staff_id, corp_staff_name, corp_staff_phone, contact_person, corp_staff_address, corp_id, designation_id"})

@Entity
@Table(name = "sfacl_staff")

public class SfaclStaff implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5458585195409395733L;

	@Id
	@SequenceGenerator(
            name = "sfacl_staff_sequence",
            sequenceName = "sfacl_staff_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sfacl_staff_sequence"
    )
	//@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "corp_staff_id")
    private int corpStaffId;

    @Column(name = "corp_staff_name")
    private String corpStaffName;

    @Column(name = "corp_staff_phone")
    private String corpStaffPhone;

    @Column(name = "contact_person")
    private Boolean contactPerson;
    
    
    @Column(name = "corp_staff_address")
    private String corpStaffAddress;

    @OneToOne
    @JoinColumn(name = "corp_id", referencedColumnName = "corp_id")
    private Sfacl corpId;

    @OneToOne
    @JoinColumn(name = "designation_id", referencedColumnName = "designation_id")
    private Designation designationId;

	public SfaclStaff(int corpStaffId, String corpStaffName, String corpStaffPhone, Boolean contactPerson, String corpStaffAddress,
			Sfacl corpId, Designation designationId) {
		super();
		this.corpStaffId = corpStaffId;
		this.corpStaffName = corpStaffName;
		this.contactPerson= contactPerson;
		this.corpStaffPhone = corpStaffPhone;
		this.corpStaffAddress = corpStaffAddress;
		this.corpId = corpId;
		this.designationId = designationId;
	}

	public SfaclStaff(String corpStaffName, String corpStaffPhone, Boolean contactPerson, String corpStaffAddress, Sfacl corpId, Designation designationId) {
		super();
		this.corpStaffName = corpStaffName;
		this.corpStaffPhone = corpStaffPhone;
		this.contactPerson=contactPerson;
		this.corpStaffAddress = corpStaffAddress;
		this.corpId = corpId;
		this.designationId = designationId;
	}

	public SfaclStaff() {
		super();
	}

	@JsonGetter("corp_staff_id")
	public int getCorpStaffId() {
		return corpStaffId;
	}

	@JsonSetter("corp_staff_id")
	public void setCorpStaffId(int corpStaffId) {
		this.corpStaffId = corpStaffId;
	}

	@JsonGetter("corp_staff_name")
	public String getCorpStaffName() {
		return corpStaffName;
	}

	@JsonSetter("corp_staff_name")
	public void setCorpStaffName(String corpStaffName) {
		this.corpStaffName = corpStaffName;
	}

	@JsonGetter("corp_staff_phone")
	public String getCorpStaffPhone() {
		return corpStaffPhone;
	}

	@JsonSetter("corp_staff_phone")
	public void setCorpStaffPhone(String corpStaffPhone) {
		this.corpStaffPhone = corpStaffPhone;
	}
	
	@JsonGetter("contact_person")
	public Boolean getContactPerson() {
		return contactPerson;
	}

	@JsonSetter("contact_person")
	public void setContactPerson(Boolean contactPerson) {
		this.contactPerson = contactPerson;
	}

	@JsonGetter("corp_staff_address")
	public String getCorpStaffAddress() {
		return corpStaffAddress;
	}

	@JsonSetter("corp_staff_address")
	public void setCorpStaffAddress(String corpStaffAddress) {
		this.corpStaffAddress = corpStaffAddress;
	}

	@JsonGetter("corp_id")
	public Sfacl getCorpId() {
		return corpId;
	}

	@JsonSetter("corp_id")
	public void setCorpId(Sfacl corpId) {
		this.corpId = corpId;
	}

	@JsonGetter("designation_id")
	public Designation getDesignationId() {
		return designationId;
	}

	@JsonSetter("designation_id")
	public void setDesignationId(Designation designationId) {
		this.designationId = designationId;
	}

	@Override
	public String toString() {
		return "SfaclStaff [corpStaffId=" + corpStaffId + ", corpStaffName=" + corpStaffName + ", corpStaffPhone="
				+ corpStaffPhone + ", contactPerson=" + contactPerson + ", corpStaffAddress=" + corpStaffAddress
				+ ", corpId=" + corpId + ", designationId=" + designationId + "]";
	}


}
