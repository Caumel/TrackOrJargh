package com.trackorjargh.javarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trackorjargh.javaclass.CommentBook;

public interface CommentBookRepository extends JpaRepository<CommentBook, Long>{

}
