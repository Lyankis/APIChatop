package com.openclassrooms.repository;


import com.openclassrooms.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	User findByEmail(String email);
}
