package com.bridgelabz.fundoonote.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonote.model.Label;

@Repository
public interface LabelRepository  extends JpaRepository<Label,Integer> {

	List<Label> findAllByUserId(int userId);
	
	Label findByUserId(int userId);
	
	Label findByLabelName(String labelName);
	
	void deleteAllByuserId(int userId);
	
	void deleteBylabelName(String labelname);

	Optional<Label> findByLabelId(int labelId);
	
}
