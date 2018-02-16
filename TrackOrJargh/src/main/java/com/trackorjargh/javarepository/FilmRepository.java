package com.trackorjargh.javarepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trackorjargh.javaclass.Film;

public interface FilmRepository extends JpaRepository<Film, Long>{
	
	Film findByName(String name);
	Film findById(Long id);

}
