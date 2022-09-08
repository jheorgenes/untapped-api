package com.pj.untapped.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pj.untapped.domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{

}
