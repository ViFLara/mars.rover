package com.elo7.mars.rover.services;

import com.elo7.mars.rover.entities.Direction;
import com.elo7.mars.rover.entities.Position;
import com.elo7.mars.rover.entities.Rover;
import com.elo7.mars.rover.repositories.PositionRepository;
import com.elo7.mars.rover.repositories.RoverRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DBService {

    private RoverRepository roverRepository;
    private PositionRepository positionRepository;

    public void instantiateTestDatabase() throws ParseException {


        Rover r1 = Rover.builder().id(null).name("Sputnik").build();

        Rover r2 = Rover.builder().id(null).name("Apolo").build();

        Position p1 = Position.builder().rover(r1).x(3).y(3).limitX(5).limitY(5).facing(Direction.E).build();

        Position p2 = Position.builder().rover(r2).x(1).y(2).limitX(5).limitY(5).facing(Direction.N).build();

        r1.getPosition(p1);

        r2.getPosition(p2);

        roverRepository.saveAll(Arrays.asList(r1, r2));

        positionRepository.saveAll(Arrays.asList(p1, p2));

    }
}

