package bl;

import org.hibernate.Session;
import org.hibernate.Transaction;

// получаем сессию и транзакцию
public class SessionUtil {

    private Session session;
    private Transaction transaction;

    // геттер
    public Session getSession() {
        return session;
    }

    // геттер
    public Transaction getTransaction(){
        return transaction;
    }

    // открываем сессию из hibernate
    public Session openSession(){
        return HibernateUtil.getSessionFactory().openSession();
    }

    // открытие сессии с транзакцией
    public Session openTransactionSession(){
        session = openSession();
        transaction = session.beginTransaction();
        return session;
    }

    // закрытие сессии
    public void closeSession(){
        session.close();
    }

    // закрытие сессии и подтверждение транзакции
    public void closeTransactionSession(){
        transaction.commit();
        closeSession();
    }
}
