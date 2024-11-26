package com.piotrba.visitors.repo;

import com.piotrba.visitors.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorsRepository extends JpaRepository<Visitor, Long> {
}
