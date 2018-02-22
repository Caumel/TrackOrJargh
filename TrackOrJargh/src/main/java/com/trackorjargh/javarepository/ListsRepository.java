package com.trackorjargh.javarepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trackorjargh.javaclass.Lists;
import com.trackorjargh.javaclass.User;

public interface ListsRepository extends JpaRepository<Lists, Long>{

		Lists findById(Long id);
		Lists findByName(String name);
		List<Lists> findByUser(User user);
}
