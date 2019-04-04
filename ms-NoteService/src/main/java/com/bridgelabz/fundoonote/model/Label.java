package com.bridgelabz.fundoonote.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "Label")
public class Label implements Serializable {

	private static final long serialVersionUID = 1L;	

	@GeneratedValue
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name = "labelName")
	private String labelName;

	private int userId=0;
	

	public int getId() {
		return id;
	}
	public void setId(int userId) {
		this.id = userId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	@Override
	public String toString() {
		return "Label [id=" + id + ", labelName=" + labelName + ", userId=" + userId + "]";
	}

}