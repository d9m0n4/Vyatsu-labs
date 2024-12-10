package ru.vyatsu.animals;

public class Newt extends Amphibian{
    private static int newtCount = 0;

    public Newt(String name, String uniqueness) {
        super(name, uniqueness);
        newtCount++;
    }

    @Override
    public void run(int distance) {
        System.out.println(name + " не может бегать.");
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " проплыл(а) " + distance + " м.");
    }


    public static int getNewtCount() {
        return newtCount;
    }
}
