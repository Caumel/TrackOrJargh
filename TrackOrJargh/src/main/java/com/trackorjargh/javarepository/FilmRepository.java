package com.trackorjargh.javarepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trackorjargh.javaclass.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {
	@Query(value = "Select max(id) from Film", nativeQuery = true)
	Long findLastId();

	// SELECT TOP 10 * FROM FILM ORDER BY ID DESC
	@Query(value = "Select top ?1 * from Film order by id desc", nativeQuery = true)
	List<Film> findByLastAdded(int additions);
	
	
	Film findByName(String name);

	Film findById(Long id);

	
}
