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
	
	void deleteAllByuserId(int userId);

	Optional<Label> findByLabelId(int labelId);
	
}
