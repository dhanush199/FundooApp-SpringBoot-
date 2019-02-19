package com.bridgelabz.fundoonote.noteservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bridgelabz.fundoonote.model.Label;

public interface LabelServiceInf {

	Label createLabel(String token,Label label, HttpServletRequest request);

	Label editLabel(String token,Label label, HttpServletRequest request);

	List<Label> retrieveLabel(String token,HttpServletRequest request);

	boolean deleteLabel(String token, HttpServletRequest request );

	boolean mapNoteToLabel(String token, int noteId, int labelId);
	
	boolean removeNoteLabel(String token, int noteId, int labelId);
}
