package ru.vyatsu;

public abstract class Animal {
    private static int totalCount = 0;
    protected String name;

    public Animal(String name) {
        this.name = name;
        totalCount++;
    }

    public abstract void run(int distance);

    public abstract void swim(int distance);

    public static int getTotalCount() {
        return totalCount;
    }
}
