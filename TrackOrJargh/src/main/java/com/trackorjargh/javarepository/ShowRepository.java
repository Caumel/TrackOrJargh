package com.trackorjargh.javarepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trackorjargh.javaclass.Book;
import com.trackorjargh.javaclass.Show;

public interface ShowRepository extends JpaRepository<Show, Long>{
	
	@Query(value="Select max(id) from Show", nativeQuery=true)
	Long findLastId();

	// SELECT TOP 10 * FROM FILM ORDER BY ID DESC
	@Query(value = "Select top ?1 * from Show order by id desc", nativeQuery = true)
	List<Show> findByLastAdded(int additions);

    @Query(value = "SELECT SHOW.* FROM POINT_SHOW INNER JOIN SHOW ON POINT_SHOW.SHOW_ID=SHOW.ID GROUP BY POINT_SHOW.SHOW_ID ORDER BY AVG(POINT_SHOW.POINTS) \n-- #pageable\n", 
    		countQuery = "SELECT COUNT(*) FROM POINT_SHOW GROUP BY SHOW_ID",
    		nativeQuery = true)
    Page<Show> findBestPointShow(Pageable pageable);
    
    @Query(value = "SELECT SHOW.* FROM SHOW_GENDERS INNER JOIN SHOW ON SHOW_GENDERS.SHOWS_ID=SHOW.ID WHERE SHOW_GENDERS.GENDERS_ID IN (SELECT ID FROM GENDER WHERE LOWER(NAME) LIKE LOWER(?1)) \n-- #pageable\n",
    		countQuery = "SELECT COUNT(SHOW.*) FROM SHOW_GENDERS INNER JOIN SHOW ON SHOW_GENDERS.SHOWS_ID=SHOW.ID WHERE SHOW_GENDERS.GENDERS_ID IN (SELECT ID FROM GENDER WHERE LOWER(NAME) LIKE LOWER(?1))",
    		nativeQuery = true)
    Page<Book> findBooksByGender(String gender, Pageable pageable);
    
	Page<Show> findByNameContainingIgnoreCase(String name, Pageable pageable);	
	Show findById(Long id);
	Show findByName(String name);

}
