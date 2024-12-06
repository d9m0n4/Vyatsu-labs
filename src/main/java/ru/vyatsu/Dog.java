package ru.vyatsu;

public class Dog extends Animal {
    private static int count = 0;

    public Dog(String name) {
        super(name);
        count++;
    }

    @Override
    public void run(int distance) {
        if (distance > 500) {
            System.out.println(name + " не может пробежать " + distance + " м");
        } else {
            System.out.println(name + " пробежал " + distance + " м");
        }
    }

    @Override
    public void swim(int distance) {
        if (distance > 10) {
            System.out.println(name + " не может проплыть " + distance + " м");
        } else {
            System.out.println(name + " проплыл " + distance + " м");
        }
    }

    public static int getCount() {
        return count;
    }
}

