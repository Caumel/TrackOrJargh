package com.trackorjargh.javarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trackorjargh.javaclass.CommentFilm;

public interface CommentFilmRepository extends JpaRepository<CommentFilm, Long>{

}
