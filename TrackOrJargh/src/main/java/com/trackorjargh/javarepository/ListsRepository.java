package com.trackorjargh.javarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trackorjargh.javaclass.Lists;

public interface ListsRepository extends JpaRepository<Lists, Long>{

		Lists findById(Long id);
		Lists findByName(String name);
}
