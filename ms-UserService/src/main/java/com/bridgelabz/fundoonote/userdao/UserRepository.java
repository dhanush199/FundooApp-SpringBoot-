package com.bridgelabz.fundoonote.userdao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonote.usermodel.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
