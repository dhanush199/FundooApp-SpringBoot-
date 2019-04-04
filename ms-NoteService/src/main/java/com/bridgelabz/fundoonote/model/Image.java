package com.bridgelabz.fundoonote.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="NoteImage")
public class Image implements Serializable{
	
	private static final long serialVersionUID = 1L;	

	@GeneratedValue
	@Id
	@Column(name="id")
	private int id;
	
	@Lob
	private byte[] image;
	
	@Column(name="noteId")
	private int noteId;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", image=" + Arrays.toString(image) + ", noteId=" + noteId + "]";
	}
	
}
