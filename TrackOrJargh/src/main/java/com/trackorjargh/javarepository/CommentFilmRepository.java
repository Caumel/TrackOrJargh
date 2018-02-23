package com.trackorjargh.javarepository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.trackorjargh.javaclass.CommentFilm;

public interface CommentFilmRepository extends JpaRepository<CommentFilm, Long>{
    @Modifying
    @Transactional
	@Query(value = "DELETE FROM COMMENT_FILM WHERE USER_ID = ?1", nativeQuery = true)
	void removeCommentsFilmsByUserId(long id);
    
    @Modifying
    @Transactional
	@Query(value = "DELETE FROM COMMENT_FILM WHERE FILM_ID = ?1", nativeQuery = true)
	void removeCommentsFilmssByFilmId(long id);
}
