package com.snakeladders.model.game;

import java.util.Random;

public class Dice {
    private final int faces;
    private final Random random;
    
    public Dice() {
        this.faces = 6;
        this.random = new Random();
    }
    
    public Dice(int faces) {
        this.faces = faces;
        this.random = new Random();
    }
    
    public int roll() {
        return random.nextInt(faces) + 1;
    }
    
    public int getFaces() {
        return faces;
    }
}