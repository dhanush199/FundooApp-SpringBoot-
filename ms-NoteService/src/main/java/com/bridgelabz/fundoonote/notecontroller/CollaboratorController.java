package com.bridgelabz.fundoonote.notecontroller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bridgelabz.fundoonote.model.Collaborator;
import com.bridgelabz.fundoonote.noteservice.CollaboratorServiceInf;

@Controller
@RequestMapping("/user")
public class CollaboratorController {

	@Autowired 
	private CollaboratorServiceInf collaboratorService;
	
	@PutMapping("/add-collabarator/{token:.+}")
	public ResponseEntity<?> addCollabarator(@PathVariable("token") String token,@RequestBody Collaborator collaborator,HttpServletRequest request,HttpServletResponse response) {
		if(collaboratorService.addCollaborator(token,collaborator))
			return new ResponseEntity<String>("successfully Added",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Something went wrong",HttpStatus.CONFLICT);
	}
	
	@PutMapping("/remove-collabarator/{token:.+}")
	public ResponseEntity<?> removeCollabarator(@PathVariable("token") String token,@RequestBody Collaborator collaborator,HttpServletRequest request,HttpServletResponse response) {
		if(collaboratorService.removeCollaborator(token,collaborator))
			return new ResponseEntity<String>("successfully deleted",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Something went wrong",HttpStatus.CONFLICT);
	}
}
