package com.trackorjargh.javarepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trackorjargh.javaclass.Film;
import com.trackorjargh.javaclass.PointFilm;
import com.trackorjargh.javaclass.User;

public interface PointFilmRepository extends JpaRepository<PointFilm, Long>{
	PointFilm findByUserAndFilm(User user, Film film);
	List<PointFilm> findByFilm(Film film);
}
