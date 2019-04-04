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
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoonote.model.Note;
import com.bridgelabz.fundoonote.noteservice.NoteServiceInf;

@Controller
@RequestMapping("/user")
public class NoteController {

	@Autowired
	private NoteServiceInf noteService;

	@PostMapping("/createnote")
	public ResponseEntity<?> createNote(@RequestHeader ("token")String token,@RequestBody Note note, HttpServletRequest request,HttpServletResponse response) {		
		if (noteService.createNote(token,note, request)!=null)
			return new ResponseEntity<String>("Note Succesfully Created",HttpStatus.OK);
		else
			return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
	}
	@PutMapping("/editnote")
	public ResponseEntity<?> editNote(@RequestParam ("token")String token,@RequestParam("noteId") int noteId,@RequestBody Note note, HttpServletRequest request,HttpServletResponse response) {
		
		if (noteService.editNote(token,note,noteId,request)!=null)
			return new ResponseEntity<String>("Note Succesfully updated",HttpStatus.OK);
		else
			return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
	}
	
	@PostMapping("/deletenote")
	public ResponseEntity<?> deleteNote(@RequestBody int noteId,@RequestHeader ("token")String token,HttpServletRequest request,HttpServletResponse response) {
		if (noteService.deleteNote(token,noteId, request)!=null)
			return new ResponseEntity<String>("Note Succesfully deleted",HttpStatus.OK);
		else
			return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
	}
	
	@GetMapping("/retrievenote")
	public ResponseEntity<?> retrieveNote(@RequestHeader ("token") String token,HttpServletRequest request,HttpServletResponse response) {
		List<Note> notes=noteService.retrieveNote(token, request);
		if (notes!=null) {
			System.out.println(notes);
			return new ResponseEntity<List<Note>>(notes,HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
	}
	
	@DeleteMapping("/delete/{noteId:.+}")
	public ResponseEntity<?> delete(@RequestHeader ("token") String token,@PathVariable int noteId,HttpServletRequest request,HttpServletResponse response) {
	List<Note> notes=noteService.deleteNote(token,noteId,request);
		if (notes!=null)
			return new ResponseEntity<String>("Successfully deleted",HttpStatus.OK);
		else
			return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
	}
	
	@PutMapping("/uploadFile/{noteId:.+}")
	public ResponseEntity<?> uploadFile(@PathVariable ("noteId")int noteId,@RequestParam ("file")MultipartFile uploadData
	 ) {	      
		if(noteService.saveImageFile(noteId,uploadData)!=null)
			return new ResponseEntity<String>("Successfully uploaded", HttpStatus.OK);

		else
			return new ResponseEntity<String>("Something went wrong",HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/uploadFile/{noteId:.+}")
	public ResponseEntity<?> deleteFile(@PathVariable ("noteId")int noteId,@RequestHeader("imageId")int imageId
	 ) {	      
		if(noteService.deleteImageFile(noteId,imageId))
			return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);

		else
			return new ResponseEntity<String>("Something went wrong",HttpStatus.NOT_FOUND);
	}

	
}
