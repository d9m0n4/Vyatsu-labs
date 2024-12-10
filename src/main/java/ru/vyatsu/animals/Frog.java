package ru.vyatsu.animals;

public class Frog extends Amphibian{
    public static int frogCount = 0;
    public Frog(String name, String uniqueness) {
        super(name, uniqueness);
        frogCount++;
    }
    @Override
    public void run(int distance) {
        System.out.println(name + " прыгнул(а) " + distance + " м.");
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " проплыл(а) " + distance + " м.");
    }


    public static int getFrogCount() {
        return frogCount;
    }
}
