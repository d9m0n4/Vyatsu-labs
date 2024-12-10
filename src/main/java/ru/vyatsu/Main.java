package ru.vyatsu;

import ru.vyatsu.animals.*;

public class Main {
    public static void main(String[] args) {
        Animal[] animals = {
                new Dog("Бобик"),
                new Cat("Мурзик"),
                new Tiger("Тигрр"),
                new Frog("Ква", "может прыгать на большие расстояния"),
                new Newt("Тритон", "может восстанавливать утраченные конечности."),
                new Chameleon("Хамелеон", "может менять цыет кожи"),
                new Chameleon("Хамелеон 2", "может менять цыет кожи")
        };

        for (Animal animal : animals) {
            animal.run(100);
            animal.swim(5);
            if (animal instanceof Amphibian) {
                ((Amphibian) animal).uniqueness();
            }
        }

        System.out.println("Всего животных: " + Animal.getAnimalCount());

        System.out.println("Всего собак: " + Dog.getDogCount());
        System.out.println("Всего котов: " + Cat.getCatCount());
        System.out.println("Всего тигров: " + Tiger.getTigerCount());

        System.out.println("Всего легушек: " + Frog.getFrogCount());
        System.out.println("Всего хамелеонов: " + Chameleon.getChameleonCount());
        System.out.println("Всего тритонов: " + Newt.getNewtCount());
    }
}