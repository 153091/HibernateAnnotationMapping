package bl;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// требуется для создания сессии
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // обычный геттер
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Закрытие сессии
    //ОЧЕНЬ важный метод
    public static void shutDown() {
        getSessionFactory().close();
    }
}
