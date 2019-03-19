package com.bridgelabz.fundoonote.noteservice;


import java.util.List;

import com.bridgelabz.fundoonote.model.Collaborator;
import com.bridgelabz.fundoonote.model.Note;

public interface CollaboratorServiceInf {

	boolean addCollaborator(String token,Collaborator collaborator);
	
	boolean removeCollaborator(String token,Collaborator colUser);
	
	List<Note> getCollaboratedNote(String token);
}
