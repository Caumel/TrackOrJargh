package com.trackorjargh.javarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trackorjargh.javaclass.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

}
