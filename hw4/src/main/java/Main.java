import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Создание таблицы с помощью JDBC
        DB.createTable();

        // Создание списка курсов
        List<Course> courses = new ArrayList<Course>(Arrays.asList(
                new Course("Python-разработчик", 10),
                new Course("Инженер по тестированию", 10),
                new Course("Java-разработчик", 22),
                new Course("1С-программист", 8),
                new Course("Веб-дизайнер", 12)
        ));
        System.out.println("Начальные данные:");
        courses.stream().forEach(System.out::println);

        // Подключение к БД с помощью Hibernate
        Connector con = new Connector();
        try (Session session = con.getSession()) {
            // Сохранение списка курсов в БД
            for (Course course : courses) {
                con.save(course, session);
            }
            System.out.println("\nДанные после загрузки в БД:");
            DB.selectAll().stream().forEach(System.out::println);

             // Чтение объекта из БД
            int id = 2;
            System.out.println("\nПолучение из БД объекта с id=" + id + ':');
            Course courseFromDB = con.getCourse(id, session);
            System.out.println(courseFromDB);

            // Обновление объекта
            int months = 18;
            System.out.println("\nУстановим курсу с id=" + id + " длительность=" + months + ':');
            courses.get(id - 1).setDuration(months);
            con.update(courses.getFirst(), session);
            courseFromDB = con.getCourse(id, session);
            System.out.println(courseFromDB);

            // Удаление объекта
            System.out.println("\nУдалим курс \"" + courses.getLast().getTitle() + '"');
            con.delete(courses.getLast(), session);

            // Итоговая таблица
            System.out.println("\nИтоговая таблица в БД:");
            DB.selectAll().stream().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
