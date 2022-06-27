package com.elo7.mars.rover.services;

import com.elo7.mars.rover.entities.Direction;
import com.elo7.mars.rover.entities.Position;
import com.elo7.mars.rover.exceptions.PositionAlreadyRegisteredException;
import com.elo7.mars.rover.repositories.PositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PositionService {

    PositionRepository positionRepository;

    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    public Optional<Position> findById(Long id) {
        Optional<Position> foundPosition = positionRepository.findById(id);
        return Optional.ofNullable(foundPosition.orElse(null));
    }

    public Position createPosition(Position positionEntity) throws PositionAlreadyRegisteredException {
        Position savedPositionEntity = positionRepository.save(positionEntity);
        return savedPositionEntity;
    }

    public Position update(Position position) {
        Position updatedPosition = positionRepository.save(position);
        return updatedPosition;
    }

    public void deleteById(Long id) throws ChangeSetPersister.NotFoundException {
        verifyIfExists(id);
        positionRepository.deleteById(id);
    }

    private Position verifyIfExists(Long id) throws ChangeSetPersister.NotFoundException {
        return positionRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    public void process(String commands, Position position) {
        for (int idx = 0; idx < commands.length(); idx++ ) {
            process(commands.charAt(idx), position);
        }
    }
    private void process(Character command, Position position) {
        if (command.equals('L')) turnLeft(position);
        else if (command.equals('R')) turnRight(position);
        else if (command.equals('M')) move(position);
        else throw new IllegalArgumentException("Digit the correct letter");
    }
    private void move(Position position) {
        Integer x = position.getX();
        Integer y = position.getY();
        if (position.getFacing() == Direction.N) {
            position.setY(y++);
        } else if (position.getFacing() == Direction.E) {
            position.setX(x++);
        } else if (position.getFacing()== Direction.S) {
            position.setY(y--);
        } else if (position.getFacing() == Direction.W) {
            position.setX(x--);
        }
    }
    private void turnLeft(Position position) {
        Direction facing = position.getFacing();
        if (facing == Direction.N) position.setFacing(Direction.W);
        else if (facing == Direction.W) position.setFacing(Direction.S);
        else if (facing == Direction.S) position.setFacing(Direction.E);
        else position.setFacing(Direction.N);
    }
    private void turnRight(Position position) {
        Direction facing = position.getFacing();
        if (facing == Direction.N) position.setFacing(Direction.E);
        else if (facing == Direction.E) position.setFacing(Direction.S);
        else if (facing == Direction.S) position.setFacing(Direction.W);
        else position.setFacing(Direction.N);
    }

    public void printPosition(Position position) {
        Integer x = position.getX();
        Integer y = position.getY();

        System.out.println(x  + " "  + y  + " " +  position.getFacing());
    }
}
