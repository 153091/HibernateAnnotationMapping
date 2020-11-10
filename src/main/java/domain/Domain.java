package domain;

import bl.HibernateUtil;
import entity.Address;
import entity.Employee;
import entity.Project;

import service.AddressService;
import service.EmployeeService;
import service.ProjectService;

import java.sql.SQLException;
import java.util.Calendar;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Domain {
    public static void main(String[] args) throws SQLException {

        // создаем экземпляры наших Service классов
        AddressService addressService = new AddressService();
        EmployeeService employeeService = new EmployeeService();
        ProjectService projectService = new ProjectService();

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

        // для проверки
        // сохраняем данные
        addressService.add(address);
        employeeService.add(employee);
        projectService.add(project);


        // закрытие сессии
        HibernateUtil.shutDown();
    }
}
