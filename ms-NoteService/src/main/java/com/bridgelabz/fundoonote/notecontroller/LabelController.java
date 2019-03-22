package com.bridgelabz.fundoonote.notecontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bridgelabz.fundoonote.model.Label;
import com.bridgelabz.fundoonote.noteservice.LabelServiceInf;

@Controller
@RequestMapping("/user")
public class LabelController {
	@Autowired
	private LabelServiceInf labelService;

	@PostMapping("/createlabel")
	public ResponseEntity<?> createLabel(@RequestHeader ("token")String token,@RequestBody Label label, HttpServletRequest request,HttpServletResponse response) {		
		if (labelService.createLabel(token,label, request)!=null)
			return new ResponseEntity<String>("label Succesfully Created",HttpStatus.OK);
		else
			return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
	}

	@PutMapping("/editlabel")
	public ResponseEntity<?> editLabel(@RequestHeader ("token")String token,@RequestBody Label label, HttpServletRequest request,HttpServletResponse response) {		
		if (labelService.editLabel(token,label, request)!=null)
			return new ResponseEntity<String>("label Succesfully updated",HttpStatus.OK);
		else
			return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
	}

	@GetMapping("/retrievelabel")
	public ResponseEntity<?> retrieveLabel(@RequestHeader ("token")String token, HttpServletRequest request,HttpServletResponse response) {		

		List<Label> labels=labelService.retrieveLabel(token, request);
		if (labels!=null)
			return new ResponseEntity<List<Label>>(labels,HttpStatus.OK);
		else
			return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
	}

	@DeleteMapping("/deletelabel/{token:.+}")
	public ResponseEntity<?> deletelabel(@PathVariable ("token")String token,@RequestParam String labelName,HttpServletRequest request) {		
		if(labelService.deleteLabel(token,labelName, request))
			return new ResponseEntity<>("successfully deleted",HttpStatus.OK);
		else
			return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
	}

	@PutMapping("/map-note-label/{noteId:.+}/{labelId:.+}")
	public ResponseEntity<?> mapNoteLabel(@RequestHeader ("token")String token,@PathVariable ("noteId")int noteId,@PathVariable ("labelId")int labelId,HttpServletRequest request) {		

		if(labelService.mapNoteToLabel(token, noteId, labelId))
			return new ResponseEntity<String>("Mapped successfully",HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}

	@DeleteMapping("/removenote&label/{noteId:.+}/{labelId:.+}")
	public ResponseEntity<?> removeNoteLabel(@RequestHeader("token")String token,@PathVariable ("noteId")int noteId,@PathVariable ("labelId")int labelId,HttpServletRequest request) {		

		if(labelService.removeNoteLabel(token, noteId, labelId))
			return new ResponseEntity<>("Labels from particular note has been removed ",HttpStatus.OK);
		else
			return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
	}
}
