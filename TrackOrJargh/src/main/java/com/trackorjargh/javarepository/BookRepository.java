package com.trackorjargh.javarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trackorjargh.javaclass.Book;
import com.trackorjargh.javaclass.Film;


public interface BookRepository extends JpaRepository<Book, Long>{
	
	@Query(value="Select max(id) from Book", nativeQuery=true)
	Long findLastId();
	
	Book findById(Long id);
	Book findByName(String name);
}
