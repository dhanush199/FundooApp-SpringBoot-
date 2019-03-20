package com.bridgelabz.fundoonote.noteservice;


import com.bridgelabz.fundoonote.model.Collaborator;

public interface CollaboratorServiceInf {

	boolean addCollaborator(String token,Collaborator collaborator);
	
	boolean removeCollaborator(String token,Collaborator colUser);
	
}
