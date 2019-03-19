package com.bridgelabz.fundoonote.userdao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bridgelabz.fundoonote.usermodel.User;


@Repository
public interface UserRepository  extends JpaRepository<User,Integer>{

	Optional<User> findUserById(int id);
	User findUserByEmailId(String emailId);
	
    @Query("SELECT emailId FROM User p WHERE p.emailId!=null")

    public List<String> find();
	
}
