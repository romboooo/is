package com.example.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class Coordinates {
    private int x;

    @NotNull(message = "Y coordinate cannot be null")
    private Long y;

    public Coordinates() {}

    public Coordinates(int x, Long y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public Long getY() { return y; }
    public void setY(Long y) { this.y = y; }
}