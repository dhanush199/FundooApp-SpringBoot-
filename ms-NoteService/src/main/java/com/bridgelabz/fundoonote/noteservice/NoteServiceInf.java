package com.bridgelabz.fundoonote.noteservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bridgelabz.fundoonote.model.Note;

public interface NoteServiceInf {
	
	Note createNote(String token,Note note, HttpServletRequest request) ;
	
	Note editNote(String token,Note note,int noteId, HttpServletRequest request);
	
	List<Note> deleteNote(String token,int noteId,HttpServletRequest request);
	
	List<Note> retrieveNote(String token,HttpServletRequest request);

	
}
