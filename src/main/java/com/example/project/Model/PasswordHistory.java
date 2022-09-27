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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "history_id, user_id, date, password, reason_type" })

@Entity
@Table(name = "password_history")
public class PasswordHistory implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6894766197968370915L;

	@Id
	@SequenceGenerator(
            name = "history_sequence",
            sequenceName = "history_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "history_sequence"
    )
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "history_id")
	private int historyId;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User userId;

	@Column(name = "password")
	private String password;

	@Temporal(TemporalType.TIMESTAMP)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "date")
	private Date date;

	@OneToOne
	@JoinColumn(name = "reason_type", referencedColumnName = "type_id")
	private Type reasonType;

	public PasswordHistory() {
		super();
	}

	public PasswordHistory(User userId, String password, Date date, Type reasonType) {
		super();
		this.userId = userId;
		this.password = password;
		this.date = date;
		this.reasonType = reasonType;
	}

	@JsonGetter("history_id")
	public int getHistoryId() {
		return historyId;
	}

	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}

	@JsonGetter("user_id")
	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	@JsonGetter("password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonGetter("date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@JsonGetter("reason_type")
	public Type getReasonType() {
		return reasonType;
	}

	public void setReasonType(Type reasonType) {
		this.reasonType = reasonType;
	}

	@Override
	public String toString() {
		return "PasswordHistory [user_id=" + userId + ", password=" + password + ", date=" + date + ", reason_type="
				+ reasonType + "]";
	}
}
