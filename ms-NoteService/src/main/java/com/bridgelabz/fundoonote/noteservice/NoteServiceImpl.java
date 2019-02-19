package com.bridgelabz.fundoonote.noteservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonote.model.Note;
import com.bridgelabz.fundoonote.repository.NoteRepository;
import com.bridgelabz.fundoonote.noteutil.TokenGeneratorInf;

@Service
public class NoteServiceImpl implements NoteServiceInf {

	@Autowired
	private NoteRepository noteRepository;

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
	public Note editNote(String token, Note note,int noteId ,HttpServletRequest request) {
		int userId=tokenGenerator.authenticateToken(token);
		Note existingNote=noteRepository.getOne(noteId);
		if(existingNote!=null) {
			existingNote.setUserId(userId);
			existingNote.setDiscription(note.getDiscription());
			existingNote.setTitle(note.getTitle());
			Note updatedNote=noteRepository.save(existingNote);
			return updatedNote;

		}
		return null;
	}
	@Override
	public Note deleteNote(String token,int noteId,HttpServletRequest request) {
		int userId=tokenGenerator.authenticateToken(token);
		Note aliveNote=noteRepository.findNoteByUserId(userId);
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
		List<Note> Notes = noteRepository.findAllNoteByUserId(userId);
		if (!Notes.isEmpty()) {
			return Notes;
		}
		return null;
	}

	public Note getNoteByID(int userId){
		return noteRepository.findNoteById(userId);
	}
	
	public Note saveNote(Note note) {
		return noteRepository.save(note);
		
	}
	
	public Note getNoteByUserID(int userId){
		return noteRepository.findNoteByUserId(userId);
	}


}
