package com.bridgelabz.fundoonote.noteservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoonote.model.Collaborator;
import com.bridgelabz.fundoonote.model.Image;
import com.bridgelabz.fundoonote.model.Note;
import com.bridgelabz.fundoonote.repository.CollaboratorRepo;
import com.bridgelabz.fundoonote.repository.ImageRepository;
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

	@Autowired
	private ImageRepository imageRepository;

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
	public Image saveImageFile(int noteId, MultipartFile file) {
		System.out.println("We are here");
		Image image =new Image();
		try {
			byte[] bytes = file.getBytes();
			image.setImage(bytes);
			image.setNoteId(noteId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		image= imageRepository.save(image);
		System.out.println(image);
		return image;
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
		for(Note note:newNotes) {
			List<Image> imageList=imageRepository.findAllByNoteId(note.getId());
			note.setImageList(imageList);
		}
		notes.addAll(newNotes);
		return notes;
	}
	
	public boolean deleteImageFile(int noteId,int imageId){
		if(noteRepository.findById(noteId).isPresent()) {
			imageRepository.deleteById(imageId);
			return true;
		}
		return false;
	}
}
