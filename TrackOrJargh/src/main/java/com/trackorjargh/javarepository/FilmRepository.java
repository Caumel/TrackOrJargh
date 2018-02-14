package com.trackorjargh.javarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trackorjargh.javaclass.Film;

public interface FilmRepository extends JpaRepository<Film, Long>{

}
