package entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PROJECT")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TITLE")
    private String title;

    // один емплой может иметь много проектов, один проект может иметь много емплойев
    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees;


    public Project() {}


    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
