package ru.vyatsu.animals;

public class Newt extends Amphibian{
    public Newt(String name) {
        super(name);
    }

    @Override
    public void run(int distance) {
        System.out.println(name + " не может бегать.");
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " проплыл(а) " + distance + " м.");
    }

    @Override
    public void uniqueness() {
        System.out.println(name + " может восстанавливать утраченные конечности.");
    }
}
