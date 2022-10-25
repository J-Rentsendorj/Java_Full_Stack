package com.jinn.bookclub.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jinn.bookclub.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
		List<User> findAll();
		
//		List<User> findAllOrderByEmailDesc();
	//	
		Optional<User> findByEmail(String email);
}
