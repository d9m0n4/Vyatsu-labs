package ru.vyatsu.obstacles;

import ru.vyatsu.interfaces.Jumpable;

public class Wall {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    public boolean attempt(Jumpable participant) {
        return participant.jump(height);
    }
}

