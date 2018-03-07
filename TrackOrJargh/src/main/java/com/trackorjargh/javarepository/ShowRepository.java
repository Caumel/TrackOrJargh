package com.trackorjargh.javarepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trackorjargh.javaclass.Shows;

public interface ShowRepository extends JpaRepository<Shows, Long>{
	
	@Query(value="Select max(id) from Show", nativeQuery=true)
	Long findLastId();

	// SELECT TOP 10 * FROM FILM ORDER BY ID DESC
	@Query(value = "Select top ?1 * from Show order by id desc", nativeQuery = true)
	List<Shows> findByLastAdded(int additions);

    @Query(value = "SELECT SHOW.* FROM POINT_SHOW INNER JOIN SHOW ON POINT_SHOW.SHOW_ID=SHOW.ID GROUP BY POINT_SHOW.SHOW_ID ORDER BY AVG(POINT_SHOW.POINTS) DESC \n-- #pageable\n", 
    		countQuery = "SELECT COUNT(*) FROM POINT_SHOW GROUP BY SHOW_ID",
    		nativeQuery = true)
    Page<Shows> findBestPointShow(Pageable pageable);
    
    @Query(value = "SELECT SHOW.* FROM SHOW_GENDERS INNER JOIN SHOW ON SHOW_GENDERS.SHOWS_ID=SHOW.ID WHERE SHOW_GENDERS.GENDERS_ID IN (SELECT ID FROM GENDER WHERE LOWER(NAME) LIKE LOWER(?1)) \n-- #pageable\n",
    		countQuery = "SELECT COUNT(SHOW.*) FROM SHOW_GENDERS INNER JOIN SHOW ON SHOW_GENDERS.SHOWS_ID=SHOW.ID WHERE SHOW_GENDERS.GENDERS_ID IN (SELECT ID FROM GENDER WHERE LOWER(NAME) LIKE LOWER(?1))",
    		nativeQuery = true)
    Page<Shows> findShowsByGender(String gender, Pageable pageable);
    
    @Query(value = "SELECT SHOW.* FROM SHOW_GENDERS INNER JOIN SHOW ON SHOW_GENDERS.SHOWS_ID=SHOW.ID WHERE SHOW_GENDERS.GENDERS_ID IN (SELECT GENDERS_ID FROM FILM_GENDERS WHERE FILMS_ID = ?1) AND SHOW.ID != ?1 \n-- #pageable\n",
    		countQuery = "SELECT COUNT(SHOW.*) FROM SHOW_GENDERS INNER JOIN SHOW ON SHOW_GENDERS.SHOWS_ID=SHOW.ID WHERE SHOW_GENDERS.GENDERS_ID IN (SELECT GENDERS_ID FROM FILM_GENDERS WHERE FILMS_ID = ?1) AND SHOW.ID != ?1",
    		nativeQuery = true)
    Page<Shows> findShowsRelationsById(long id, Pageable pageable);
    
	Page<Shows> findByNameContainingIgnoreCase(String name, Pageable pageable);	
	Shows findById(Long id);
	Shows findByNameIgnoreCase(String name);

}
