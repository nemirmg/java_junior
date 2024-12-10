package animals;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Animal> animals = new ArrayList<>();
        
        animals.add(createCat("Мурзик", 3, "Сиамская"));
        animals.add(createDog("Шарик", 4, "Ездовая"));

        System.out.println(animals);

        animals.stream().forEach(Animal::makeSound);
    }

    public static Cat createCat(String name, int age, String breed) throws Exception {
        Class<?> clazz = Class.forName("animals.Cat");
        return (Cat) clazz.getConstructors()[0].newInstance(name, age, breed);
    }

    public static Dog createDog(String name, int age, String job) throws Exception {
        Class<?> clazz = Class.forName("animals.Dog");
        return (Dog) clazz.getConstructors()[0].newInstance(name, age, job);
    }
}
