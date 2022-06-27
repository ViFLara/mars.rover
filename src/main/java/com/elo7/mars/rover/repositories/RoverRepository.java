package com.elo7.mars.rover.repositories;

import com.elo7.mars.rover.entities.Rover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoverRepository extends JpaRepository<Rover, Long> {
    Optional<Rover> findByName(String name);

}
