package ru.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Converter {
    private final String BIN = ".bin";
    private final String JSON = ".json";
    private final String XML = ".xml";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final XmlMapper xmlMapper = new XmlMapper();
    private Student student;

    public Converter(Student student) {
        this.student = student;
    }

    public void toBIN(String filename) {
        String f = filename + BIN;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
            oos.writeObject(student);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void toJSON(String filename) {
        String f = filename + JSON;
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            objectMapper.writeValue(new File(f), student);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toXML(String filename) {
        String f = filename + XML;
        try {
            xmlMapper.writeValue(new File(f), student);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Student fromBIN(String filename) {
        String f = filename + BIN;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (Student) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Student fromJSON(String filename) {
        String f = filename + JSON;
        try {
            return objectMapper.readValue(new File(f), Student.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Student fromXML(String filename) {
        String f = filename + XML;
        try {
            return xmlMapper.readValue(new File(f), Student.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
