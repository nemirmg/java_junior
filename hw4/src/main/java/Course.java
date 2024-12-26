import jakarta.persistence.*;

@Entity
@Table(name = "courses", schema = "school")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "duration")
    private int duration;

    public Course() {
    }

    public Course(String title, int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Курс{" +
                "id=" + id +
                ", название='" + title + '\'' +
                ", длительность=" + duration +
                '}';
    }
}
