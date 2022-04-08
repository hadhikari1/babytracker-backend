package com.wcci.babytracker.Repositories;

import com.wcci.babytracker.pojos.Baby;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BabyRepository extends JpaRepository<Baby, Long> {
   List<Baby> findAllByUserId(Long id);

}
