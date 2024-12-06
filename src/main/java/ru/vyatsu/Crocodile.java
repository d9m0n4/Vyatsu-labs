package ru.vyatsu;

public class Crocodile extends Predator {
    public Crocodile(String name) {
        super(name);
    }

    @Override
    public void eat(Animal animal) {
        System.out.println(this.name + " съел " + animal.name);
    }

    @Override
    public void run(int distance) {
        System.out.println(name + " медленно пробежал " + distance + " м");
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " проплыл " + distance + " м");
    }
}

