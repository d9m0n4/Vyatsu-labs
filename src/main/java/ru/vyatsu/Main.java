package ru.vyatsu;

import ru.vyatsu.participants.Human;
import ru.vyatsu.participants.Robot;
import ru.vyatsu.participants.Cat;
import ru.vyatsu.obstacles.*;
import ru.vyatsu.interfaces.Runnable;
import ru.vyatsu.interfaces.Jumpable;

public class Main {
    public static void main(String[] args) {
        Runnable[] participants = {
                new Human("Человек 1", 1000, 2),
                new Cat("Кот 1", 500, 3),
                new Cat("Кот 2", 400, 2),
                new Cat("Кот 3", 400, 2),
                new Cat("Кот 4", 400, 2),
                new Robot("Робот 1", 2000, 1)
        };

        Object[] obstacles = {
                new Track(300),
                new Wall(2),
                new Track(400),
                new Wall(3)
        };

        for (Runnable participant : participants) {
            boolean passedAll = true;
            for (Object obstacle : obstacles) {
                if (obstacle instanceof Track) {
                    if (!((Track) obstacle).attempt(participant)) {
                        passedAll = false;
                        break;
                    }
                } else if (obstacle instanceof Wall) {
                    if (!((Wall) obstacle).attempt((Jumpable) participant)) {
                        passedAll = false;
                        break;
                    }
                }
            }

            if (passedAll) {
                System.out.println(participant + " успешно прошел все препятствия!");
            } else {
                System.out.println(participant + " не смог пройти все препятствия.");
            }
        }
    }
}