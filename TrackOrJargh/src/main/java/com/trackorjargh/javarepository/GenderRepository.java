package com.trackorjargh.javarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trackorjargh.javaclass.Gender;

public interface GenderRepository extends JpaRepository<Gender, Long>{

}
