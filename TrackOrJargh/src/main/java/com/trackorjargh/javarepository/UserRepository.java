package com.trackorjargh.javarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trackorjargh.javaclass.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByName(String nickname);

}
