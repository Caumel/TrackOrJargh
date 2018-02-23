package com.trackorjargh.javarepository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.trackorjargh.javaclass.CommentBook;

public interface CommentBookRepository extends JpaRepository<CommentBook, Long>{
    @Modifying
    @Transactional
	@Query(value = "DELETE FROM COMMENT_BOOK WHERE USER_ID = ?1", nativeQuery = true)
	void removeCommentsBooksByUserId(long id);
    
    @Modifying
    @Transactional
	@Query(value = "DELETE FROM COMMENT_BOOK WHERE BOOK_ID = ?1", nativeQuery = true)
	void removeCommentsBooksByBookId(long id);
}
