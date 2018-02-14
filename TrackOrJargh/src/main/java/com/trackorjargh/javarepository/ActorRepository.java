package com.trackorjargh.javarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trackorjargh.javaclass.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long>{

}
