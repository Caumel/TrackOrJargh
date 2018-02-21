package com.trackorjargh.javarepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trackorjargh.javaclass.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByName(String name);
	User findByEmail(String email);
<<<<<<< HEAD
=======
	
	
	
>>>>>>> 6f2da447c24153955a71c8c0e4e4dc06828d4e63
}
