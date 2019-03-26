package com.bridgelabz.fundoonote.noteservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonote.model.Label;
import com.bridgelabz.fundoonote.model.Note;
import com.bridgelabz.fundoonote.noteutil.TokenGeneratorInf;
import com.bridgelabz.fundoonote.repository.LabelRepository;
import com.bridgelabz.fundoonote.repository.NoteRepository;

@Service
public class LabelServiceImpl implements LabelServiceInf{

	@Autowired
	private LabelRepository labelRepository;

	@Autowired
	private TokenGeneratorInf tokenGenerator;

	@Autowired
	private NoteRepository noteRepository;

	public Label createLabel(String token,Label label, HttpServletRequest request){
		int userId=tokenGenerator.authenticateToken(token);
		System.out.println(userId);
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
		List<Label> aliveLabel=labelRepository.findAllByUserId(userId);
		if(aliveLabel!=null) {
			Label existingLabel = aliveLabel.stream()
					.filter(userLabel -> label.getId()==(userLabel.getId()))
					.findAny()
					.orElse(null);
			existingLabel.setLabelName(label.getLabelName());
			assert(existingLabel!=null);
			return labelRepository.save(existingLabel);
		}
		else
			return null;
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
	public boolean deleteLabel(String token,String labelName, HttpServletRequest request) {
		int userId=tokenGenerator.authenticateToken(token);
		List<Label> aliveLabels=labelRepository.findAllByUserId(userId);
		if(aliveLabels!=null) {
			labelRepository.deleteBylabelName(labelName);
			return true;
		}
		else
			return false;
	}

	public boolean mapNoteToLabel(String token, int noteId, int labelId) {
		System.out.println("labelId"+labelId+" noteId "+noteId);
		Note note = noteRepository.findById(noteId).get();
		Label label = labelRepository.findById(labelId).get();
		if(!note.getLabelList().contains(label)) {
			List<Label> labelAddList=note.getLabelList();
			labelAddList.add(label);
			note.setLabelList(labelAddList);
			noteRepository.save(note);
			return true;
		}				
		return false;
	}

	@Transactional
	public boolean removeNoteLabel(String token, int noteId, int labelId) {
		Note residingNote = noteRepository.findById(noteId).get();
		List<Label> labels = residingNote.getLabelList();
		Label label = labelRepository.findById(labelId).get();

		if(labels.remove(label)) {
			residingNote.setLabelList(labels);
			//			System.out.println((labels));
			//			if (!labels.isEmpty()) {
			//				labels = labels.stream().filter(label -> label.getId() == labelId)
			//						.collect(Collectors.toList());
			//				residingNote.setLabelList(labels);
			//				System.out.println("this is my label list "+labels);
			noteRepository.save(residingNote);
			return true;
			//}
		}
		return false;

	}
}

