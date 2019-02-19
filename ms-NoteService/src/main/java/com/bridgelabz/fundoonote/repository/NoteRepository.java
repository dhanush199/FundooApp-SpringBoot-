package com.bridgelabz.fundoonote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonote.model.Note;

@Repository
public interface NoteRepository  extends JpaRepository<Note,Integer>{

	Note findNoteByUserId(int id);
	
	List<Note> findAllNoteByUserId(int id);
	
	Note findNoteById(int noteId);

}

