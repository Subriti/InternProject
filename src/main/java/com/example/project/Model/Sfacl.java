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

@JsonPropertyOrder({"corp_id, corp_name, corp_phone, corp_address, area_id, status_id"})

@Entity
@Table(name = "sfacl")
public class Sfacl implements Serializable{

    /**
	 *
	 */
	private static final long serialVersionUID = 2181861867072599129L;

	@Id
	@SequenceGenerator(
            name = "sfacl_sequence",
            sequenceName = "sfacl_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sfacl_sequence"
    )
	//@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "corp_id")
    private int corpId;

    @Column(name = "corp_name")
    private String corpName;

    @Column(name = "corp_phone")
    private String corpPhone;

    @Column(name = "corp_address")
    private String corpAddress;

    @OneToOne
    @JoinColumn(name = "area_id", referencedColumnName = "area_id")
    private Area areaId;

    @OneToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private Status statusId;


	public Sfacl() {
		super();
	}

	public Sfacl(int corpId, String corpName, String corpPhone, String corpAddress, Area areaId, Status statusId) {
		super();
		this.corpId = corpId;
		this.corpName = corpName;
		this.corpPhone = corpPhone;
		this.corpAddress = corpAddress;
		this.areaId = areaId;
		this.statusId = statusId;
	}

	public Sfacl(String corpName, String corpPhone, String corpAddress, Area areaId, Status statusId) {
		super();
		this.corpName = corpName;
		this.corpPhone = corpPhone;
		this.corpAddress = corpAddress;
		this.areaId = areaId;
		this.statusId = statusId;
	}

	@JsonGetter("corp_id")
	public int getCorpId() {
		return corpId;
	}

	@JsonSetter("corp_id")
	public void setCorpId(int corpId) {
		this.corpId = corpId;
	}

	@JsonGetter("corp_name")
	public String getCorpName() {
		return corpName;
	}

	@JsonSetter("corp_name")
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}


	@JsonGetter("corp_phone")
	public String getCorpPhone() {
		return corpPhone;
	}

	@JsonSetter("corp_phone")
	public void setCorpPhone(String corpPhone) {
		this.corpPhone = corpPhone;
	}

	@JsonGetter("corp_address")
	public String getCorpAddress() {
		return corpAddress;
	}

	@JsonSetter("corp_address")
	public void setCorpAddress(String corpAddress) {
		this.corpAddress = corpAddress;
	}

	@JsonGetter("area_id")
	public Area getAreaId() {
		return areaId;
	}

	@JsonSetter("area_id")
	public void setAreaId(Area areaId) {
		this.areaId = areaId;
	}

	@JsonGetter("status_id")
	public Status getStatus() {
		return statusId;
	}

	@JsonSetter("status_id")
	public void setStatus(Status statusId) {
		this.statusId = statusId;
	}

	@Override
	public String toString() {
		return "Sfacl [corpId=" + corpId + ", corpName=" + corpName + ", corpPhone=" + corpPhone + ", corpAddress=" + corpAddress + ", areaId=" + areaId + ", statusId="
				+ statusId + "]";
	}

}
