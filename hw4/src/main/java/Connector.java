import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Connector {
    private SessionFactory sessionFactory;

    public Connector() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public void save(Course course, Session session) {
        session.beginTransaction();
        session.save(course);
        session.getTransaction().commit();
    }

    public Course getCourse(int id, Session session) {
            return session.get(Course.class, id);
    }

    public void update(Course course, Session session) {
            session.beginTransaction();
            session.update(course);
            session.getTransaction().commit();
    }

    public void delete(Course course, Session session) {
            session.beginTransaction();
            session.delete(course);
            session.getTransaction().commit();
    }
}
