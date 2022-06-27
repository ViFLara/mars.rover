package com.elo7.mars.rover.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

    @Data
    @Builder
    @Entity
    @Table(name = "rovers")
    @NoArgsConstructor
    @AllArgsConstructor
    public class Rover {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "position_id", referencedColumnName = "id")
        private Position position;

        public Rover(String name, Position position) {
            this.name = name;
            this.position = position;
            this.position.setRover(this);
        }

}
