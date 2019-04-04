package com.bridgelabz.fundoonote.noteservice;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundoonote.model.Collaborator;
import com.bridgelabz.fundoonote.noteutil.TokenGeneratorInf;
import com.bridgelabz.fundoonote.repository.CollaboratorRepo;

@Service
public class CollaboratorServiceImpl implements CollaboratorServiceInf{

	@Autowired
	private TokenGeneratorInf tokenGenerator;

	@Autowired
	private CollaboratorRepo collaboratorRepo;
	
	public boolean addCollaborator(String token,Collaborator collaborator) {
		collaborator.setOwnerId(collaborator.getOwnerId());
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
		try {
		collaboratorRepo.deleteById(colaborater.getId());
		}catch (Exception e) {
			return false;
		}
		return true;
	}	
}
