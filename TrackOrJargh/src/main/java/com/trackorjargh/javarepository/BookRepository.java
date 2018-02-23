package com.trackorjargh.javarepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trackorjargh.javaclass.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	
	@Query(value="Select max(id) from Book", nativeQuery=true)
	Long findLastId();
	
	
	//SELECT TOP 10 * FROM FILM ORDER BY ID DESC
	@Query(value="Select top ?1 * from Book order by id desc", nativeQuery=true)
	List<Book> findByLastAdded(int additions);
	Page<Book> findByNameContainingIgnoreCase(String name, Pageable pageable);
	
    @Query(value = "SELECT BOOK.* FROM POINT_BOOK INNER JOIN BOOK ON POINT_BOOK.BOOK_ID=BOOK.ID GROUP BY POINT_BOOK.BOOK_ID ORDER BY AVG(POINT_BOOK.POINTS) \n-- #pageable\n", 
    		countQuery = "SELECT COUNT(*) FROM POINT_BOOK GROUP BY BOOK_ID",
    		nativeQuery = true)
    Page<Book> findBestPointBook(Pageable pageable);
    
	Book findById(Long id);
	Book findByName(String name);
}

