package com.trackorjargh.javarepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trackorjargh.javaclass.PointShow;
import com.trackorjargh.javaclass.User;

public interface PointShowRepository extends JpaRepository<PointShow, Long>{
	PointShow findByUser(User user);
}
