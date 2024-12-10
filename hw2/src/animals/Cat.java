package animals;

public class Cat extends Animal {
    private final String SOUND = "Мяу!";
    // порода
    private String breed;
        
    public Cat(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Кошка: " + SOUND);
    }

    @Override
    public String toString() {
        return "Кошка [имя=" + super.getName() + ", возраст=" + super.getAge() + ", порода=" + breed + "]";
    }

        
}
