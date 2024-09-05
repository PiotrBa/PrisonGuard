package com.piotrba.prisoners.repo;

import com.piotrba.prisoners.entity.Prisoner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrisonersRepository extends JpaRepository<Prisoner, Long> {
}
