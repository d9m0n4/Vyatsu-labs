package ru.vyatsu;

public class Main {
    public static void main(String[] args) {
        Animal[] animals = {
                new Cat("Мурзик"),
                new Dog("Бобик"),
                new Tiger("Шерхан"),
                new Wolf("Серый"),
                new Lion("Симба"),
                new Crocodile("Гена")
        };

        for (Animal animal : animals) {
            animal.run(150);
            animal.swim(5);
        }

        System.out.println("Всего котов: " + Cat.getCount());
        System.out.println("Всего собак: " + Dog.getCount());
        System.out.println("Всего тигров: " + Tiger.getCount());
        System.out.println("Всего животных: " + Animal.getTotalCount());

        Predator[] predators = {
                new Wolf("Серый"),
                new Lion("Симба"),
                new Crocodile("Гена")
        };

        for (Predator predator : predators) {
            for (Animal prey : animals) {
                predator.eat(prey);
            }
        }

        System.out.println("Всего хищников: " + Predator.getCount());
    }
}
