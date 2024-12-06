package ru.vyatsu;

public class Cat extends Animal {
    private static int count = 0;

    public Cat(String name) {
        super(name);
        count++;
    }

    @Override
    public void run(int distance) {
        if (distance > 200) {
            System.out.println(name + " не может пробежать " + distance + " м");
        } else {
            System.out.println(name + " пробежал " + distance + " м");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать");
    }

    public static int getCount() {
        return count;
    }
}
