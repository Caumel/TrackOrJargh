package com.trackorjargh.javarepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trackorjargh.javaclass.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	
	@Query(value="Select max(id) from Book", nativeQuery=true)
	Long findLastId();
	
	
	//SELECT TOP 10 * FROM FILM ORDER BY ID DESC
	@Query(value="Select top ?1 * from Book order by id desc", nativeQuery=true)
	List<Book> findByLastAdded(int additions);
	
	
	Book findById(Long id);
	Book findByName(String name);
	
	
	
}

