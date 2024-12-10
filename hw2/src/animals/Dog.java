package animals;

public class Dog extends Animal {
    private final String SOUND = "Гав-гав!";
    // сфера применения: охотничья, служебная, ездовая и т.п.
    private String job;

    public Dog(String name, int age, String job) {
        super(name, age);
        this.job = job;
    }

    public String getJob() {
        return job;
    }

    @Override
    public void makeSound() {
        System.out.println("Собака: " + SOUND);
    }

    @Override
    public String toString() {
        return "Собака [имя=" + super.getName() + ", возраст=" + super.getAge() + ", вид=" + job + "]";
    }
}
