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

@JsonPropertyOrder({"staff_id, staff_name, staff_phone, staff_address, staff_type, staff_status, user_id"})

@Entity
@Table(name = "staff")
public class Staff implements Serializable{

    /**
	 *
	 */
	private static final long serialVersionUID = -4132868616593747054L;

	@Id
	@SequenceGenerator(
            name = "staff_sequence",
            sequenceName = "staff_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "staff_sequence"
    )
	//@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "staff_id")
    private int staffId;

    @Column(name = "staff_name")
    private String staffName;

    @Column(name = "staff_phone")
    private String staffPhone;

    @Column(name = "staff_address")
    private String staffAddress;

    @OneToOne
    @JoinColumn(name = "staff_type", referencedColumnName = "type_id")
    private Type staffType;

    @OneToOne
    @JoinColumn(name = "staff_status", referencedColumnName = "status_id")
    private Status staffStatus;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User userId;


	public Staff() {
		super();
	}

	public Staff(String staffName, String staffPhone, String staffAddress, Type staffType, Status staffStatus,
			User userId) {
		super();
		this.staffName = staffName;
		this.staffPhone = staffPhone;
		this.staffAddress = staffAddress;
		this.staffType = staffType;
		this.staffStatus = staffStatus;
		this.userId = userId;
	}
	
	public Staff(String staffName) {
		super();
		this.staffName = staffName;
	}

	@JsonGetter("staff_id")
	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	@JsonGetter("staff_name")
	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	@JsonGetter("staff_phone")
	public String getStaffPhone() {
		return staffPhone;
	}

	public void setStaffPhone(String staffPhone) {
		this.staffPhone = staffPhone;
	}

	@JsonGetter("staff_address")
	public String getStaffAddress() {
		return staffAddress;
	}

	public void setStaffAddress(String staffAddress) {
		this.staffAddress = staffAddress;
	}

	@JsonGetter("staff_type")
	public Type getStaffType() {
		return staffType;
	}

	public void setStaffType(Type staffType) {
		this.staffType = staffType;
	}

	@JsonGetter("staff_status")
	public Status getStaffStatus() {
		return staffStatus;
	}

	public void setStaffStatus(Status staffStatus) {
		this.staffStatus = staffStatus;
	}

	@JsonGetter("user_id")
	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Staff [staff_id=" + staffId + ", staff_name=" + staffName + ", staff_phone=" + staffPhone
				+ ", staff_address=" + staffAddress + ", staff_type=" + staffType + ", staff_status=" + staffStatus
				+ ", user_id=" + userId + "]";
	}


}
