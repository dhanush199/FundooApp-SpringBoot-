package com.bridgelabz.fundoonote.noteservice;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundoonote.model.Collaborator;
import com.bridgelabz.fundoonote.model.Note;
import com.bridgelabz.fundoonote.noteutil.TokenGeneratorInf;
import com.bridgelabz.fundoonote.repository.CollaboratorRepo;
import com.bridgelabz.fundoonote.repository.NoteRepository;

@Service
public class CollaboratorServiceImpl implements CollaboratorServiceInf{

	@Autowired
	private TokenGeneratorInf tokenGenerator;

	@Autowired
	private CollaboratorRepo collaboratorRepo;
	
	@Autowired
	private NoteRepository noteRepository;

	public boolean addCollaborator(String token,Collaborator collaborator) {
		int userId=tokenGenerator.authenticateToken(token);
		collaborator.setOwnerId(userId);
		try {
			collaboratorRepo.save(collaborator);
		}catch(Exception e) {
			return false;
		}
		return true;
	}


	@Transactional
	public boolean removeCollaborator(String token, Collaborator colaborater) {
		tokenGenerator.authenticateToken(token);
		System.out.println(colaborater);
		try {
		collaboratorRepo.deleteById(colaborater.getId());
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public List<Note> getCollaboratedNote(String token) {
		Collaborator collaboratedUser=collaboratorRepo.findByOwnerId(tokenGenerator.authenticateToken(token)).get();
		return noteRepository.findAllById(collaboratedUser.getNoteId());
	}
}
