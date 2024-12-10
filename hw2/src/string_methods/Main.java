package string_methods;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Method[] methods = String.class.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Метод: " + method.getName());
        }
    }
}
