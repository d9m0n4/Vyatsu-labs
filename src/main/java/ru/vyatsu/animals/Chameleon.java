package ru.vyatsu.animals;

public class Chameleon extends Amphibian{
    public Chameleon(String name) {
        super(name);
    }

    @Override
    public void run(int distance) {
        System.out.println(name + " медленно пробежал(а) " + distance + " м.");
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать.");
    }

    @Override
    public void uniqueness() {
        System.out.println(name + " может менять цвет кожи.");
    }


}
