package com.elo7.mars.rover.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Entity
@Builder
@Table(name = "position")
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer x;
    private Integer y;
    private Integer limitX;
    private Integer limitY;
    Direction facing;

    @OneToOne(mappedBy = "position")
    private Rover rover;

    public void setLimit(Integer limitX, Integer limitY) {
        this.limitX = limitX;
        this.limitY = limitY;
    }
    public void setPosition(Integer x, Integer y, Direction facing) {
        this.x = x;
        this.y = y;
        this.facing = facing;
    }

}

