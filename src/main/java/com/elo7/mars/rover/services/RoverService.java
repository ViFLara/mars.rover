package com.elo7.mars.rover.services;

import com.elo7.mars.rover.entities.Rover;
import com.elo7.mars.rover.exceptions.RoverAlreadyRegisteredException;
import com.elo7.mars.rover.repositories.RoverRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoverService {

    private RoverRepository roverRepository;

    public Optional<Rover> findById(Long id) {
        Optional<Rover> foundRover = roverRepository.findById(id);
        return Optional.ofNullable(foundRover.orElse(null));
    }

    public List<Rover> findAll() {
        return roverRepository.findAll();
    }

    public Rover update(Rover rover) {
        Rover updatedRover = roverRepository.save(rover);
        return updatedRover;
    }

    public void deleteById(Long id) throws ChangeSetPersister.NotFoundException {
        verifyIfExists(id);
        roverRepository.deleteById(id);
    }

    private Rover verifyIfExists(Long id) throws ChangeSetPersister.NotFoundException {
        return roverRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    public Rover createRover(Rover roverEntity) throws RoverAlreadyRegisteredException {
        Rover savedRoverEntity = roverRepository.save(roverEntity);
        return savedRoverEntity;
    }

}
