package ru.vyatsu.participants;
import ru.vyatsu.interfaces.Jumpable;
import ru.vyatsu.interfaces.Runnable;

public class Cat implements Runnable, Jumpable {
    private String name;
    private int maxRunDistance;
    private int maxJumpHeight;
    private static int superJumpUsesLeft = 2;

    public Cat(String name, int maxRunDistance, int maxJumpHeight) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public boolean run(int distance) {
        if (distance <= maxRunDistance) {
            System.out.println(name + " успешно пробежал " + distance + " м.");
            return true;
        } else {
            System.out.println(name + " не смог пробежать " + distance + " м.");
            return false;
        }
    }

    @Override
    public boolean jump(int height) {
        if (height <= maxJumpHeight) {
            System.out.println(name + " успешно прыгнул на высоту " + height + " м.");
            return true;
        } else if (superJumpUsesLeft > 0) {
            superJumpUsesLeft--;
            System.out.println(name + " использовал superJump и преодолел высоту " + height + " м! Осталось суперпрыжков: " + superJumpUsesLeft);
            return true;
        } else {
            System.out.println(name + " не смог прыгнуть на высоту " + height + " м. SuperJump больше недоступен.");
            return false;
        }
    }
}

