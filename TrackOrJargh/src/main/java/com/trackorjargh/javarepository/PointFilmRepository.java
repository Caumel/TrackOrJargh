package com.trackorjargh.javarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trackorjargh.javaclass.PointFilm;
import com.trackorjargh.javaclass.User;

public interface PointFilmRepository extends JpaRepository<PointFilm, Long>{
	PointFilm findByUser(User user);
}
