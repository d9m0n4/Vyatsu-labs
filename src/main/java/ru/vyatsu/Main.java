package ru.vyatsu;

import ru.vyatsu.animals.*;

public class Main {
    public static void main(String[] args) {
        Animal[] animals = {
                new Dog("Бобик"),
                new Cat("Мурзик"),
                new Tiger("Тигрр"),
                new Frog("Ква"),
                new Newt("Тритон"),
                new Chameleon("Хамелеон")
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
        System.out.println("Всего котов: " + Tiger.getTigerCount());
    }
}