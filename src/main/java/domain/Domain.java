package domain;

import entity.Address;
import entity.Employee;
import entity.Project;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.AddressRepository;
import repository.EmployeeRepository;
import repository.ProjectRepository;

import java.util.Calendar;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Domain {
    public static void main(String[] args) {

        // конфигурация спринг через наш XML
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // создание репозиториев
        // вытягиваем из контекста
        AddressRepository addressRepository = context.getBean(AddressRepository.class);
        EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
        ProjectRepository projectRepository = context.getBean(ProjectRepository.class);

        // создание адреса
        Address address = new Address();
        address.setId(1L);
        address.setCountry("DC");
        address.setCity("Gotham City");
        address.setStreet("Arkham street 1");
        address.setPostCode("0987");

        // создание сотрудника
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("James");
        employee.setLastName("Gordon");

        Calendar calendar = Calendar.getInstance();
        calendar.set(1939, Calendar.MAY, 1);

        employee.setBirthday(new Date(calendar.getTime().getTime()));
        employee.setAddress(address);

        // создание проекта
        Project project = new Project();
        project.setId(1L);
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
        addressRepository.save(address);
        employeeRepository.save(employee);
        projectRepository.save(project);

        System.out.println(employeeRepository.findByFirstNameAndLastName("James", "Gordon"));
    }
}
