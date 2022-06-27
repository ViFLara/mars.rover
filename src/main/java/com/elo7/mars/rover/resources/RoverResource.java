package com.elo7.mars.rover.resources;

import com.elo7.mars.rover.entities.Rover;
import com.elo7.mars.rover.exceptions.RoverAlreadyRegisteredException;
import com.elo7.mars.rover.services.RoverService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rovers")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoverResource {

    private RoverService service;

    @GetMapping
    public ResponseEntity<List<Rover>> findAll() {
        List<Rover> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Rover>> findById(@PathVariable Long id) {
        Optional<Rover> obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Rover createRover(@RequestBody Rover rover) throws RoverAlreadyRegisteredException {
        return service.createRover(rover);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Rover rover, @PathVariable Long id) {
        rover.setId(id);
        rover = service.update(rover);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        service.deleteById(id);
    }

}
