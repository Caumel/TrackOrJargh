package com.trackorjargh.javarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trackorjargh.javaclass.PointBook;
import com.trackorjargh.javaclass.User;

public interface PointBookRepository extends JpaRepository<PointBook, Long>{
	PointBook findByUser(User user);
}
