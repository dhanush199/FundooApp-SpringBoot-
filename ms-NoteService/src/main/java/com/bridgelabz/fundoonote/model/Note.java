package com.bridgelabz.fundoonote.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="Note")
public class Note implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch=FetchType.EAGER,targetEntity= Label.class,cascade = CascadeType.ALL)
	@JoinTable(name = "Note_Label", joinColumns = { @JoinColumn(name = "noteId") }, inverseJoinColumns = { @JoinColumn(name = "labelId") })
	private List<Label> labelList;

	@GeneratedValue
	@Id
	@Column(name="id")
	private int id;


	@Column(name="title")
	private String title;

	@Column(name="discription")
	private String discription;

	@Column(name="isPinned")
	private boolean isPinned;

	@Column(name="colore")
	private String colore;

	@Column(name="inTrash")
	private boolean inTrash=false;

	@Column(name="updateTime")
	@UpdateTimestamp
	private Timestamp updateTime;

	@Column(name="createdTime")
	@CreationTimestamp
	private Timestamp createdTime;



	@OneToMany(mappedBy = "noteId", cascade = CascadeType.ALL)
	private List<Collaborator> collaborators;
	public List<Collaborator> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(List<Collaborator> collaborator) {
		this.collaborators = collaborator;
	}


	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	private int userId;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private boolean isArchive=false;

	public boolean getArchive() {
		return isArchive;
	}

	public void setArchive(boolean isArchive) {
		this.isArchive = isArchive;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}


	public void setPinned(boolean isPinned) {
		this.isPinned = isPinned;
	}

	public boolean getPinned() {
		return isPinned;
	}

	public void setInTrash(boolean inTrash) {
		this.inTrash = inTrash;
	}

	public boolean getInTrash() {
		return inTrash;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public List<Label> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<Label> labelList) {
		this.labelList = labelList;
	}

	@Override
	public String toString() {
		return "Note [labelList=" + labelList + ", id=" + id + ", title=" + title + ", discription=" + discription
				+ ", isPinned=" + isPinned + ", colore=" + colore + ", inTrash=" + inTrash + ", updateTime="
				+ updateTime + ", createdTime=" + createdTime + ", userId=" + userId + ", isArchive=" + isArchive + "]";
	}


}

