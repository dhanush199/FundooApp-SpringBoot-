package com.bridgelabz.fundoonote.usermodel;


import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.redis.core.RedisHash;

@Entity
@Table(name = "User")
//@RedisHash("User")
public class User implements Serializable {

	private static final long serialVersionUID = 185652L;

	@SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_USER", allocationSize = 1)
	   
	@Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "emailId", unique = true)
	private String emailId;
	
	public String getCoEmailId() {
		return coEmailId;
	}

	public void setCoEmailId(String coEmailId) {
		this.coEmailId = coEmailId;
	}

	@Column(name = "coEmailId")
	private String coEmailId;

	@Column(name = "password", length = 60)
	private String password;

	@Column(name = "mobileNumber")
	private long mobileNumber;
	
	@Lob
	private byte[] image;
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] bytes) {
		this.image = bytes;
	}

	@Column(name = "activation_status")
	private boolean activationStatus;

	public boolean isActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(boolean activationStatus) {
		this.activationStatus = activationStatus;
	}
	public boolean getActivationStatus() {
		return activationStatus;
	}

	public int getId() {
		return id;
	}

	public User setId(int id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", emailId=" + emailId + ", coEmailId=" + coEmailId + ", password="
				+ password + ", mobileNumber=" + mobileNumber + ", image=" + Arrays.toString(image)
				+ ", activationStatus=" + activationStatus + "]";
	}

}
