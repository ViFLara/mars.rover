package com.elo7.mars.rover.resources;

import com.elo7.mars.rover.entities.Position;
import com.elo7.mars.rover.entities.Rover;
import com.elo7.mars.rover.exceptions.PositionAlreadyRegisteredException;
import com.elo7.mars.rover.services.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/positions")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PositionResource {

        private PositionService service;

        @GetMapping
        public ResponseEntity<List<Position>> findAll() {
            List<Position> list = service.findAll();
            return ResponseEntity.ok().body(list);
        }

        @GetMapping(value = "/{id}")
        public ResponseEntity<Optional<Position>> findById(@PathVariable Long id) {
            Optional<Position> obj = service.findById(id);
            return ResponseEntity.ok().body(obj);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Position createPosition(@RequestBody Position position) throws PositionAlreadyRegisteredException {
            return service.createPosition(position);
        }

        @RequestMapping(value="/{id}", method=RequestMethod.PUT)
        public ResponseEntity<Void> update(@RequestBody Position position, @PathVariable Long id) {
            position.setId(id);
            position = service.update(position);
            return ResponseEntity.noContent().build();
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
            service.deleteById(id);
        }

    }

