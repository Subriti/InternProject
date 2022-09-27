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

@JsonPropertyOrder({ "user_id, user_name, password, password_expiry_months, user_status" })

@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8139372911386924019L;

	@Id
	 @SequenceGenerator(
	            name = "user_sequence",
	            sequenceName = "user_sequence",
	            allocationSize = 1
	    )
	    @GeneratedValue(
	            strategy = GenerationType.SEQUENCE,
	            generator = "user_sequence"
	    )
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;
	
	@Column(name = "password_expiry_months")
	private String passwordExpiryMonths;

	@OneToOne
	@JoinColumn(name = "user_status", referencedColumnName = "status_id")
	private Status userStatus;

	/*
	 * Custom Serialization: without having to implement serializable in status
	 *
	 * private void writeObject(ObjectOutputStream oos) throws IOException {
	 * oos.defaultWriteObject(); oos.writeObject(userStatus.getStatusId()); }
	 *
	 * private void readObject(ObjectInputStream ois) throws ClassNotFoundException,
	 * IOException { ois.defaultReadObject(); Integer userStatusId = (Integer)
	 * ois.readObject(); Status status = new Status();
	 * status.setStatusId(userStatusId); this.setUserStatus(status); }
	 *
	 *
	 *
	 *
	 * Deserialization (don't know if it works)
	 *
	 * try {
	 * String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
	 * 		ObjectMapper objectMapper = new ObjectMapper();
	 * 		userDetails = objectMapper. readValue(json, Student.class);
	 *
	 * Car car =
  objectMapper.readValue(new URL("file:src/test/resources/json_car.json"), Car.class);
	 *  }
	 * catch (JsonProcessingException e) { return null; }
	 */

	public User() {
		super();
	}

	/*
	 * public User(int userId, String userName, String password, Status userStatus)
	 * { this.userId = userId; this.userName = userName; this.password = password;
	 * this.userStatus = userStatus; }
	 * 
	 * public User(String userName, String password, Status userStatus) {
	 * this.userName = userName; this.password = password; this.userStatus =
	 * userStatus; }
	 */

	public User(int userId, String userName, String password, String passwordExpiryMonths, Status userStatus) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.passwordExpiryMonths = passwordExpiryMonths;
		this.userStatus = userStatus;
	}


	public User(String userName, String password, String passwordExpiryMonths, Status userStatus) {
		super();
		this.userName = userName;
		this.password = password;
		this.passwordExpiryMonths = passwordExpiryMonths;
		this.userStatus = userStatus;
	}

	@JsonGetter("user_id")
	public int getUserId() {
		return userId;
	}

	@JsonSetter("user_id")
	public void setUserId(int userId) {
		this.userId = userId;
	}

	@JsonGetter("user_name")
	public String getUserName() {
		return userName;
	}

	@JsonSetter("user_name")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonGetter("password")
	public String getPassword() {
		return password;
	}

	@JsonSetter("password")
	public void setPassword(String password) {
		this.password = password;
	}

	@JsonGetter("password_expiry_months")
	public String getPasswordExpiryMonths() {
		return passwordExpiryMonths;
	}

	@JsonSetter("password_expiry_months")
	public void setPasswordExpiryMonths(String passwordExpiryStrings) {
		this.passwordExpiryMonths = passwordExpiryStrings;
	}

	@JsonGetter("user_status")
	public Status getUserStatus() {
		return userStatus;
	}

	@JsonSetter("user_status")
	public void setUserStatus(Status userStatus) {
		this.userStatus = userStatus;
	}
}
