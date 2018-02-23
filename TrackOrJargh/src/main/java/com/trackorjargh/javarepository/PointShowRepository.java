package com.trackorjargh.javarepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.trackorjargh.javaclass.PointShow;
import com.trackorjargh.javaclass.Show;
import com.trackorjargh.javaclass.User;

public interface PointShowRepository extends JpaRepository<PointShow, Long>{
	PointShow findByUserAndShow(User user, Show show);
	List<PointShow> findByShow(Show show);
	
    @Modifying
    @Transactional
	@Query(value = "DELETE FROM POINT_SHOW WHERE USER_ID = ?1", nativeQuery = true)
	void removePointsShowsByUserId(long id);
    
    @Modifying
    @Transactional
	@Query(value = "DELETE FROM POINT_SHOW WHERE SHOW_ID = ?1", nativeQuery = true)
	void removePointsShowsByShowId(long id);
}
