package ru.vyatsu.animals;

public abstract class Amphibian extends Animal {
    protected String uniqueness;

    public Amphibian(String name, String uniqueness) {
        super(name);
        this.uniqueness = uniqueness;
    }

    public void uniqueness(){
        System.out.println(name + ": " + uniqueness);
    }
}
