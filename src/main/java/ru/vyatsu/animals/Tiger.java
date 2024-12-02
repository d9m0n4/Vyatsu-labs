package ru.vyatsu.animals;

public class Tiger extends Animal{
    public static int tigerCount = 0;

    public Tiger(String name) {
        super(name);
        tigerCount++;
    }

    @Override
    public void run(int distance) {
        if (distance < 600) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не смог пробежать " + distance + " м.");

        }
    }

    @Override
    public void swim(int distance) {
        if (distance <= 7) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не смог проплыть " + distance + " м.");
        }
    }

    public static int getTigerCount() {
        return tigerCount;
    }
}
