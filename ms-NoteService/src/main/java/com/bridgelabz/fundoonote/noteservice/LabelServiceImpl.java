package com.bridgelabz.fundoonote.noteservice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonote.model.Label;
import com.bridgelabz.fundoonote.model.Note;
import com.bridgelabz.fundoonote.noteutil.TokenGeneratorInf;
import com.bridgelabz.fundoonote.repository.LabelRepository;

@Service
public class LabelServiceImpl implements LabelServiceInf{

	@Autowired
	private LabelRepository labelRepository;

	@Autowired
	private TokenGeneratorInf tokenGenerator;

	@Autowired
	private NoteServiceInf noteService;

	public Label createLabel(String token,Label label, HttpServletRequest request){
		int userId=tokenGenerator.authenticateToken(token);
		if(userId>0) {
			label.setUserId(userId);
			Label sevedLabel=labelRepository.save(label);
			return sevedLabel;
		}
		return null;
	}

	public Label editLabel(String token,Label label, HttpServletRequest request)
	{
		int userId=tokenGenerator.authenticateToken(token);
		Label aliveLabel=labelRepository.findByUserId(userId);
		if(aliveLabel!=null) {
			aliveLabel.setLabelName(label.getLabelName());
			return labelRepository.save(aliveLabel);
		}
		return aliveLabel;
	}

	public List<Label> retrieveLabel(String token, HttpServletRequest request) {
		int userId=tokenGenerator.authenticateToken(token);
		List<Label> labels = labelRepository.findAllByUserId(userId);
		if (!labels.isEmpty()) 
			return labels;
		else
			return null;
	}
	@Transactional
	public boolean deleteLabel(String token, HttpServletRequest request) {
		int userId=tokenGenerator.authenticateToken(token);
		Label aliveLabels=labelRepository.findByUserId(userId);
		if(aliveLabels!=null) {
			labelRepository.deleteAllByuserId(userId);
			return true;
		}
		else
			return false;
	}
	public boolean mapNoteToLabel(String token, int noteId, int labelId) {
		int userId = tokenGenerator.authenticateToken(token);
		Note note = noteService.getNoteByID(noteId);
		Optional<Label> OptionalLabel = labelRepository.findByLabelId(labelId);
		Label label=OptionalLabel.get();
		List<Label> labels = labelRepository.findAllByUserId(userId);
		labels.add(label);
		if (!labels.isEmpty()) {
			note.setLabelList(labels);
			noteService.saveNote(note);
			//labelRepository.save(label);
			return true;
		}
		return false;
	}

	public boolean removeNoteLabel(String token, int noteId, int labelId) {
		int id = tokenGenerator.authenticateToken(token);
		Note residingNote = noteService.getNoteByUserID(id);
		List<Label> labels = residingNote.getLabelList();
		if (!labels.isEmpty()) {
			labels = labels.stream().filter(label -> label.getId() != labelId)
					.collect(Collectors.toList());
			residingNote.setLabelList(labels);
			labelRepository.deleteAll(labels);
			return true;
		}
		return false;
	}




}
