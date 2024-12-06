package ru.vyatsu;

public class Tiger extends Animal {
    private static int count = 0;

    public Tiger(String name) {
        super(name);
        count++;
    }

    @Override
    public void run(int distance) {
        System.out.println(name + " пробежал " + distance + " м");
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " проплыл " + distance + " м");
    }

    public static int getCount() {
        return count;
    }
}

