package ru.example;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("Вася", 23, 4.2);
        Converter c = new Converter(student);

        // сериализация
        System.out.println("Перед сериализацией:\n" + student);
        c.toBIN("student");
        c.toJSON("student");
        c.toXML("student");
        
        // десериализация
        student = c.fromBIN("student");
        System.out.println("После десериализации (bin):\n" + student);
        student = c.fromJSON("student");
        System.out.println("После десериализации (json):\n" + student);
        student = c.fromXML("student");
        System.out.println("После десериализации (xml):\n" + student);
    }
}