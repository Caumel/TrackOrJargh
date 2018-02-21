package com.trackorjargh.javarepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trackorjargh.javaclass.Book;
import com.trackorjargh.javaclass.PointBook;
import com.trackorjargh.javaclass.User;

public interface PointBookRepository extends JpaRepository<PointBook, Long>{
	PointBook findByUserAndBook(User user, Book book);
	List<PointBook> findByBook(Book book);
}
