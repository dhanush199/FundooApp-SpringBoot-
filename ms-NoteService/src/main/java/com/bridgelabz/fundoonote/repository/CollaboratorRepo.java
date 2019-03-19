package com.bridgelabz.fundoonote.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonote.model.Collaborator;

@Repository
public interface CollaboratorRepo extends JpaRepository<Collaborator,Integer>{
	
	List<Collaborator> findByNoteId(int noteId);
	
	Optional<Collaborator> findByOwnerId(int userId);

}
