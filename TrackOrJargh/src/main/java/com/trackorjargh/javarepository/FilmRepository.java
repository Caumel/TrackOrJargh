package com.trackorjargh.javarepository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trackorjargh.javaclass.Film;

public interface FilmRepository extends JpaRepository<Film, Long>{
	Film findByName(String name);
	Film findById(Long id);
	
	@Query(value="Select max(id) from Book", nativeQuery=true)
	Long findLastId();
}
