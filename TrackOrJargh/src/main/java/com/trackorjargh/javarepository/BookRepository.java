package com.trackorjargh.javarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trackorjargh.javaclass.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
