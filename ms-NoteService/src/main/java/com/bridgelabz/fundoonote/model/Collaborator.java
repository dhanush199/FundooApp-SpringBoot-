package com.bridgelabz.fundoonote.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Collaborator")
public class Collaborator implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@GeneratedValue
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name = "collEmailId")
	private String collEmailId;

	@Column(name = "noteId")
	private int noteId;
	
	@Column(name = "ownerId")
	private int ownerId;

	public String getCollEmailId() {
		return collEmailId;
	}
	public void setCollEmailId(String collEmailId) {
		this.collEmailId = collEmailId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	public int getCollNoteId() {
		return noteId;
	}
	public void setCollNoteId(int collNoteId) {
		noteId = collNoteId;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
	@Override
	public String toString() {
		return "Collaborator [id=" + id + ", collEmailId=" + collEmailId + ", noteId=" + noteId + ", ownerId=" + ownerId
				+ "]";
	}
	
	

}
