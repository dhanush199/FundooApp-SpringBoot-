package com.bridgelabz.fundoonote.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonote.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image,Integer> { 

	List<Image> findAllByNoteId(int noteId);
	
}
