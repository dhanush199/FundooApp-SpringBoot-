package com.bridgelabz.fundoonote.noteservice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonote.model.Collaborator;
import com.bridgelabz.fundoonote.model.Note;
import com.bridgelabz.fundoonote.repository.CollaboratorRepo;
import com.bridgelabz.fundoonote.repository.NoteRepository;
import com.bridgelabz.fundoonote.noteutil.TokenGeneratorInf;

@Service
public class NoteServiceImpl implements NoteServiceInf {

	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private CollaboratorRepo collaboratorRepo;

	@Autowired
	private TokenGeneratorInf tokenGenerator;

	@Override
	public Note createNote(String token, Note note, HttpServletRequest request) {
		int userId=tokenGenerator.authenticateToken(token);
		note.setUserId(userId);
		Note savedNote=noteRepository.save(note);
		return savedNote;
	}

	@Override
	public Note editNote(String token, Note note,int noteId,HttpServletRequest request) {
		Note existingNote=noteRepository.getOne(noteId);
		if(existingNote!=null) {
			note.setId(existingNote.getId());
			Note updatedNote=noteRepository.save(note);
			return updatedNote;

		}
		return null;
	}
	@Override
	public List<Note> deleteNote(String token,int noteId,HttpServletRequest request) {
		int userId=tokenGenerator.authenticateToken(token);
		List<Note> aliveNote=noteRepository.findNoteByUserId(userId);
		if(aliveNote!=null) {
			noteRepository.deleteById(noteId);
			return aliveNote;
		}
		else
			return null;
	}

	@Override
	public List<Note> retrieveNote(String token,HttpServletRequest request) {
		int userId=tokenGenerator.authenticateToken(token);
		List<Note> notes=new ArrayList<Note>();
		List<Collaborator> collaborators=collaboratorRepo.findAllByOwnerId(userId);
		for(Collaborator collaborator:collaborators)
		{
			notes.add(noteRepository.findById(collaborator.getNoteId()).get());
		}
		List<Note> newNotes = noteRepository.findAllNoteByUserId(userId);
		notes.addAll(newNotes);
		return notes;
	}

}
