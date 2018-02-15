package com.trackorjargh.javarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trackorjargh.javaclass.Episode;

public interface EpisodeRepository extends JpaRepository<Episode, Long>{

}
