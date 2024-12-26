import java.sql.*;
import java.util.ArrayList;

public class DB {
    private static final String URL = "jdbc:postgresql://localhost:5433/SchoolDB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "example";
    private static StringBuilder sb = new StringBuilder();

    public static void createTable() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = con.createStatement();

            // создание новой БД
            statement.execute("DROP SCHEMA IF EXISTS school CASCADE");
            statement.execute("CREATE SCHEMA school");

            // создание таблицы
            sb.append("CREATE TABLE school.courses (\n");
            sb.append("id SERIAL PRIMARY KEY,\n");
            sb.append("title VARCHAR(255),\n");
            sb.append("duration INT NULL\n");
            sb.append(");");
            statement.execute(sb.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Course> selectAll() {
        ArrayList<Course> courses = null;
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            courses = new ArrayList<>();

            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM school.courses ORDER BY \"id\";");
            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getInt(1));
                course.setTitle(resultSet.getString(2));
                course.setDuration(resultSet.getInt(3));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}
