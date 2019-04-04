package com.bridgelabz.fundoonote.noteservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoonote.model.Image;
import com.bridgelabz.fundoonote.model.Note;

public interface NoteServiceInf {

	Note createNote(String token,Note note, HttpServletRequest request) ;

	Note editNote(String token,Note note,int noteId, HttpServletRequest request);

	List<Note> deleteNote(String token,int noteId,HttpServletRequest request);

	List<Note> retrieveNote(String token,HttpServletRequest request);
	
	Image saveImageFile(int noteId, MultipartFile file) ;
	
	boolean deleteImageFile(int noteId,int imageId);

}
