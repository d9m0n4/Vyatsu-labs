package ru.vyatsu.animals;

public class Chameleon extends Amphibian{
    public static int chameleonCount = 0;
    public Chameleon(String name, String uniqueness) {
        super(name, uniqueness);
        chameleonCount++;
    }

    @Override
    public void run(int distance) {
        System.out.println(name + " медленно пробежал(а) " + distance + " м.");
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать.");
    }

    public static int getChameleonCount() {
        return chameleonCount;
    }


}
