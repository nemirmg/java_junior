package animals;

public abstract class Animal {
    private String name;
    private int age;
    
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract void makeSound();

    @Override
    public String toString() {
        return "Животное [имя=" + name + ", возраст=" + age + "]";
    }
}