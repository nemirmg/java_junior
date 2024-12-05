package even_numbers;

import java.util.Arrays;
import java.util.Random;

public class Main {
    private static int[] numbers;

    private static void setNumbers(int count, int bound) {
        Random random = new Random();
        numbers = random.ints(0, bound + 1).limit(count).toArray();
    }

    private static int[] getEvenNumber() {
        return Arrays.stream(numbers).filter(num -> num % 2 == 0).toArray();
    }

    private static double avgOfEvenNumbers() {
        return Arrays.stream(getEvenNumber()).average().orElse(Double.NaN);
    }

    private static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        setNumbers(10, 20);
        print(numbers);
        print(getEvenNumber());
        System.out.println(avgOfEvenNumbers());
    }
}
