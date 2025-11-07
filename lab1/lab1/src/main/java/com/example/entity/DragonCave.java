package com.example.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Positive;

import jakarta.persistence.Column;

@Embeddable
public class DragonCave {
    @Positive(message = "Number of treasures must be greater than 0")
    @Column(name = "treasures_count")
    private int numberOfTreasures;

    public DragonCave() {}

    public DragonCave(int numberOfTreasures) {
        this.numberOfTreasures = numberOfTreasures;
    }

    public int getNumberOfTreasures() { return numberOfTreasures; }
    public void setNumberOfTreasures(int numberOfTreasures) { this.numberOfTreasures = numberOfTreasures; }
}