package service;

import bl.SessionUtil;
import dao.AddressDAO;
import entity.Address;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class AddressService extends SessionUtil implements AddressDAO {
    @Override
    public void add(Address address) throws SQLException {
        // open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(address);

        // close session with a transaction
        closeTransactionSession();
    }

    @Override
    public List<Address> getAll() throws SQLException {
        // open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM ADDRESS";

        Session session = getSession();
        // Создаем Query
        // Native - используем обычный sql
        Query query = session.createNativeQuery(sql).addEntity(Address.class);
        List<Address> addressList = query.list();


        // close session with a transaction
        closeTransactionSession();

        return addressList;
    }

    @Override
    public Address getById(long id) throws SQLException {
        // open session with a transaction
        openTransactionSession();

        // новый способ указывать параметры
        // после двоеточия
        String sql = "SELECT * FROM ADDRESS WHERE ID = :id";

        Session session = getSession();
        // Создаем Query
        // Native - используем обычный sql
        Query query = session.createNativeQuery(sql).addEntity(Address.class);
        query.setParameter("id", id);

        // возвращаем результат (единичный)
        Address address = (Address) query.getSingleResult();

        // close session with a transaction
        closeTransactionSession();

        return address;
    }

    @Override
    public void update(Address address) throws SQLException {
        // open session with a transaction
        openTransactionSession();

        //используем обычные методы из сессии
        Session session = getSession();
        session.update(address);

        // close session with a transaction
        closeTransactionSession();
    }

    @Override
    public void remove(Address address) throws SQLException {
        // open session with a transaction
        openTransactionSession();

        //используем обычные методы из сессии
        Session session = getSession();
        session.remove(address);

        // close session with a transaction
        closeTransactionSession();
    }
}
