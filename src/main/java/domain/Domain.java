package domain;

import bl.HibernateUtil;
import entity.Address;
import entity.Employee;
import entity.Project;
import org.hibernate.Session;

import java.util.Calendar;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Domain {
    public static void main(String[] args) {

        // 1. первым делом нужно открыть сессию!
        Session session = HibernateUtil.getSessionFactory().openSession();

        // 2. Создать транзакцию в которой ммы будем сохранять
        //     или извлекать данные
        session.beginTransaction();

        // создание адреса
        Address address = new Address();
        address.setCountry("DC");
        address.setCity("Gotham City");
        address.setStreet("Arkham street 1");
        address.setPostCode("0987");

        // создание сотрудника
        Employee employee = new Employee();
        employee.setFirstName("James");
        employee.setLastName("Gordon");

        Calendar calendar = Calendar.getInstance();
        calendar.set(1939, Calendar.MAY, 1);

        employee.setBirthday(new Date(calendar.getTime().getTime()));
        employee.setAddress(address);

        // создание проекта
        Project project = new Project();
        project.setTitle("5678");

        /** Many-to-Many */
        // создаем ОБА сета
        Set<Employee> employees  = new HashSet<>();
        employees.add(employee);
        project.setEmployees(employees);

        Set<Project> projects = new HashSet<Project>();
        projects.add(project);
        employee.setProjects(projects);


        // сохраняем данные
        session.save(address);
        session.save(employee);
        session.save(project);

        // подтверждение транзакции
        session.getTransaction().commit();
        // закрытие сессии
        HibernateUtil.shutDown();
    }
}
