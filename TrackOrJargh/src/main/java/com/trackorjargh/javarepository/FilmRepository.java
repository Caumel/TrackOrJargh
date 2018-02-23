package com.trackorjargh.javarepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trackorjargh.javaclass.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {
	@Query(value = "Select max(id) from Film", nativeQuery = true)
	Long findLastId();

	// SELECT TOP 10 * FROM FILM ORDER BY ID DESC
	@Query(value = "Select top ?1 * from Film order by id desc", nativeQuery = true)
	List<Film> findByLastAdded(int additions);
	
    @Query(value = "SELECT FILM.* FROM POINT_FILM INNER JOIN FILM ON POINT_FILM.FILM_ID=FILM.ID GROUP BY POINT_FILM.FILM_ID ORDER BY AVG(POINT_FILM.POINTS) \n-- #pageable\n", 
    		countQuery = "SELECT COUNT(*) FROM POINT_FILM GROUP BY FILM_ID",
    		nativeQuery = true)
    Page<Film> findBestPointFilm(Pageable pageable);
	
	Page<Film> findByNameContainingIgnoreCase(String name, Pageable pageable);
	Film findByName(String name);
	Film findById(Long id);
}
