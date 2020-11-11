package entity;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Set;

@Entity
@Table(name = "PROJECT")
public class Project implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
