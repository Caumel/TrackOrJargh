package com.trackorjargh.javarepository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.trackorjargh.javaclass.CommentShow;

public interface CommentShowRepository extends JpaRepository<CommentShow, Long>{
    @Modifying
    @Transactional
	@Query(value = "DELETE FROM COMMENT_SHOW WHERE USER_ID = ?1", nativeQuery = true)
	void removeCommentsShowsByUserId(long id);
    
    @Modifying
    @Transactional
	@Query(value = "DELETE FROM COMMENT_SHOW WHERE SHOW_ID = ?1", nativeQuery = true)
	void removeCommentsShowsByShowId(long id);
}
