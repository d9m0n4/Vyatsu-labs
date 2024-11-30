package ru.vyatsu.animals;

public class Frog extends Amphibian{
    public Frog(String name) {
        super(name);
    }
    @Override
    public void run(int distance) {
        System.out.println(name + " прыгнул(а) " + distance + " м.");
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " проплыл(а) " + distance + " м.");
    }

    @Override
    public void uniqueness() {
        System.out.println(name + " умеет прыгать на большие расстояния.");
    }
}
