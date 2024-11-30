package ru.vyatsu.obstacles;


import ru.vyatsu.interfaces.Runnable;

public class Track {
    private int length;

    public Track(int length) {
        this.length = length;
    }

    public boolean attempt(Runnable participant) {
        return participant.run(length);
    }
}

