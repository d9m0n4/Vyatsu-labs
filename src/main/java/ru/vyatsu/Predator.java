package ru.vyatsu;

public abstract class Predator extends Animal {
    private static int count = 0;

    public Predator(String name) {
        super(name);
        count++;
    }

    public static int getCount() {
        return count;
    }

    public abstract void eat(Animal animal);
}

