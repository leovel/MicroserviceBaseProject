package com.leovel.officerservice.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leovel.officerservice.data.entities.Officer;

public interface OfficerRepository extends JpaRepository<Officer, Long> {

}
