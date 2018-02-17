package com.trackorjargh.javarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trackorjargh.javaclass.Show;

public interface ShowRepository extends JpaRepository<Show, Long>{
	
	@Query(value="Select max(id) from Book", nativeQuery=true)
	Long findLastId();
	
	Show findById(Long id);
	Show findByName(String name);

}
