package ru.vyatsu;

public class Wolf extends Predator {
    public Wolf(String name) {
        super(name);
    }

    @Override
    public void run(int distance) {
        System.out.println(name + " пробежал " + distance + " м");
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " проплыл " + distance + " м");
    }

    @Override
    public void eat(Animal animal) {
        if (animal instanceof Crocodile) {
            System.out.println(this.name + " не смог съесть " + animal.name);
        } else {
            System.out.println(this.name + " съел " + animal.name);

        }
    }
}

