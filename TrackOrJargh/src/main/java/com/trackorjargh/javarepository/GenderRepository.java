package com.trackorjargh.javarepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trackorjargh.javaclass.Film;
import com.trackorjargh.javaclass.Gender;

public interface GenderRepository extends JpaRepository<Gender, Long>{

	
	List<Gender> findByFilms(Film film);
	
	@Query(value = "SELECT * FROM GENDER WHERE ID NOT LIKE (SELECT GENDERS_ID FROM FILM_GENDERS WHERE FILMS_ID = ?1)", nativeQuery = true)
	List<Gender> findByNotInFilm(Long id);
	
}
