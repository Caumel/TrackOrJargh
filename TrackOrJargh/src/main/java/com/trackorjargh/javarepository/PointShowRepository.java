package com.trackorjargh.javarepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trackorjargh.javaclass.PointShow;
import com.trackorjargh.javaclass.Show;
import com.trackorjargh.javaclass.User;

public interface PointShowRepository extends JpaRepository<PointShow, Long>{
	PointShow findByUserAndShow(User user, Show show);
	List<PointShow> findByShow(Show show);
}
